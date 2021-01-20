package ng.groove.exovideoview.media

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.util.Util

class MediaSourceCreator @JvmOverloads constructor(private val context: Context, private val userAgent: String = "exo_video_view") {

    private val mediaDataSourceFactory: DataSource.Factory

    private val mainHandler: Handler
    private val eventLogger: EventLogger
    private val BANDWIDTH_METER = DefaultBandwidthMeter()

    val trackSelector: DefaultTrackSelector

    init {

        mediaDataSourceFactory = buildDataSourceFactory(true)
        mainHandler = Handler()

        val adaptiveTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        trackSelector = DefaultTrackSelector(adaptiveTrackSelectionFactory)
        eventLogger = EventLogger(trackSelector)
    }

    fun getEventLogger(): EventLogger {
        return eventLogger
    }

    fun buildMediaSource(uri: Uri, overrideExtension: String?): MediaSource {
        @C.ContentType val type = if (TextUtils.isEmpty(overrideExtension))
            Util.inferContentType(uri)
        else
            Util.inferContentType(".$overrideExtension")
        return when (type) {
            C.TYPE_SS -> SsMediaSource(
                uri,
                buildDataSourceFactory(false),
                DefaultSsChunkSource.Factory(mediaDataSourceFactory),
                mainHandler,
                eventLogger
            )
            C.TYPE_DASH -> DashMediaSource(
                uri,
                buildDataSourceFactory(false),
                DefaultDashChunkSource.Factory(mediaDataSourceFactory),
                mainHandler,
                eventLogger
            )
            C.TYPE_HLS ->
                HlsMediaSource.Factory(mediaDataSourceFactory)
                    .createMediaSource(uri)
            C.TYPE_OTHER -> {
                val pMediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
                pMediaSource.createMediaSource(uri)
            }
            else -> {
                throw IllegalStateException("Unsupported type: $type")
            }
        }
    }

    private fun buildDataSourceFactory(useBandwidthMeter: Boolean): DataSource.Factory {
        return buildDataSourceFactory(if (useBandwidthMeter) BANDWIDTH_METER else null)
    }

    private fun buildDataSourceFactory(bandwidthMeter: DefaultBandwidthMeter?): DataSource.Factory {
        return DefaultDataSourceFactory(
            context,
            bandwidthMeter,
            buildHttpDataSourceFactory(bandwidthMeter)
        )
    }

    private fun buildHttpDataSourceFactory(bandwidthMeter: DefaultBandwidthMeter?): HttpDataSource.Factory {
        return DefaultHttpDataSourceFactory(userAgent, bandwidthMeter)
    }
}
