package ng.groove.exovideoview.media

import android.view.Surface
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.audio.AudioRendererEventListener
import com.google.android.exoplayer2.decoder.DecoderCounters
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.metadata.MetadataOutput
import com.google.android.exoplayer2.source.*
import com.google.android.exoplayer2.trackselection.MappingTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.video.VideoRendererEventListener
import java.io.IOException

class EventLogger(trackSelector: MappingTrackSelector) :
    Player.EventListener, MetadataOutput,
    AudioRendererEventListener,
    VideoRendererEventListener,
    AdaptiveMediaSourceEventListener,
    MediaSourceEventListener {


    override fun onLoadingChanged(isLoading: Boolean) {

    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

    }

    override fun onRepeatModeChanged(repeatMode: Int) {

    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

    }


    override fun onPositionDiscontinuity(reason: Int) {

    }


    override fun onSeekProcessed() {

    }


    override fun onAudioSessionId(audioSessionId: Int) {

    }

    override fun onDroppedFrames(count: Int, elapsedMs: Long) {

    }

    override fun onVideoSizeChanged(
        width: Int,
        height: Int,
        unappliedRotationDegrees: Int,
        pixelWidthHeightRatio: Float
    ) {

    }

    override fun onRenderedFirstFrame(surface: Surface?) {

    }


    override fun onMetadata(metadata: Metadata) {

    }
}
