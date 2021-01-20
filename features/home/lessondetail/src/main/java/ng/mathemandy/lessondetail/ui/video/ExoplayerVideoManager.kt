package ng.mathemandy.lessondetail.ui.video

import android.content.Context
import android.net.Uri
import android.os.Build
import android.view.SurfaceView
import com.google.android.exoplayer2.Renderer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import ng.groove.exovideoview.ui.ExoVideoView
import ng.mathemandy.lessondetail.BuildConfig

class ExoplayerVideoManager private constructor() {

    private var player: SimpleExoPlayer? = null
    private var videos: List<String> = listOf()
    private lateinit var mContext: Context
    private var isPlayerPlaying: Boolean? = null
    private var mResumeWindow: Int = 0
    private var mResumePosition: Long = 0
    private var videoView: ExoVideoView? = null

    fun init(context: Context, videoMedia: List<String>, startWindow: Int) {
        mContext = context
        videos = videoMedia
        mResumeWindow = startWindow
    }

    fun prepareExoplayer(playerView: ExoVideoView?) {
        if (mContext == null || playerView == null) {
            return
        }
        videoView = playerView
        if (player == null) {
            // create a new Player if the player is null
            player = SimpleExoPlayer.Builder(mContext).build()

            try {

                val mediaSources = MutableList<MediaSource>(videos.size) {
                    buildMediaSource(Uri.parse(videos[it]))
                }

                // [CREATE A MEDIA SOURCE]
                val mediaSource = if (mediaSources.size == 1)
                    mediaSources[0] // [MAKES A SINGLE VIDEO MEDIA SOURCE]
                else
                    ConcatenatingMediaSource(*mediaSources.toTypedArray()) // [MAKES A PLAYLIST MEDIA SOURCE]

                // [MAKES VIDEO FILL SCREEN WITHOUT STRETCHING]
                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL)
                player?.videoScalingMode = Renderer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING

                mediaSource?.let { player?.prepare(it) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        player?.clearVideoSurface()
        player?.setVideoSurfaceView(playerView.videoSurfaceView as SurfaceView?)
        player?.seekTo(mResumeWindow, player?.currentPosition ?: 0)
        playerView.setPlayer(player)
        isPlayerPlaying = true
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val defaultBandwidthMeter = DefaultBandwidthMeter.Builder(mContext).build()
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(
            mContext,
            Util.getUserAgent(mContext, BuildConfig.APPLICATION_ID),
            defaultBandwidthMeter
        )

//        val dataSourceFactory = RtmpDataSourceFactory()

//        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    fun releaseVideoPlayer() {
        if (player != null) {
            mResumePosition = player?.currentPosition!!
            mResumeWindow = player?.currentWindowIndex!!
            videoView?.releasePlayer()
            player?.release()
        }
    }

    fun onNewIntent() {
        mResumeWindow = 0
        mResumePosition = 0L
        player = null
        videoView?.releasePlayer()
    }

    fun gotoBackground() {
        if (Build.VERSION.SDK_INT <= 23) {
            videoView?.pause()
        }
    }

    fun getPlayer(): SimpleExoPlayer? {
        return videoView?.getPlayer()
    }

    fun gotoForeground() {
        if (Build.VERSION.SDK_INT > 23) {
            videoView?.resume()
        }
    }

    fun onResume() {
        if (Build.VERSION.SDK_INT <= 23) {
            videoView?.resume()
        }
    }

    fun onStop() {
        if (Build.VERSION.SDK_INT > 23) {
            videoView?.pause()
        }
    }

    companion object : SingletonHolder<ExoplayerVideoManager>(::ExoplayerVideoManager)
}
