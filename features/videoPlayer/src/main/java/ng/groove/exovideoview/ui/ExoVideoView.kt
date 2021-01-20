package ng.groove.exovideoview.ui

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.metadata.id3.ApicFrame
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.text.Cue
import com.google.android.exoplayer2.text.TextOutput
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerControlView.DEFAULT_SHOW_TIMEOUT_MS
import com.google.android.exoplayer2.ui.SubtitleView
import com.google.android.exoplayer2.util.Assertions
import com.google.android.exoplayer2.util.RepeatModeUtil
import com.google.android.exoplayer2.util.Util
import ng.groove.exovideoview.R
import ng.groove.exovideoview.extension.MultiQualitySelectorAdapter
import ng.groove.exovideoview.media.ExoMediaSource
import ng.groove.exovideoview.media.MediaSourceCreator

/**
 * Created by mo on 16-11-7.
 *
 * @author mo
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
class ExoVideoView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr), ExoVideoPlaybackControlView.VideoViewAccessor {

    private var contentFrame: AspectRatioFrameLayout?
    private var shutterView: View?
    /**
     * Gets the view onto which video is rendered. This is a:
     *
     *  * [SurfaceView] by default, or if the `surface_type` attribute is set to
     * `surface_view`.
     *  * [TextureView] if `surface_type` is `texture_view`.
     *  * `null` if `surface_type` is `none`.
     *
     *
     * @return The [SurfaceView], [TextureView] or `null`.
     */
    var videoSurfaceView: View?
    private var artworkView: ImageView?
    /**
     * Gets the [SubtitleView].
     *
     * @return The [SubtitleView], or `null` if the layout has been customized and the
     * subtitle view is not present.
     */
    var subtitleView: SubtitleView?
    private var controller: ExoVideoPlaybackControlView?
    private var componentListener: ComponentListener?
    /**
     * Gets the overlay [FrameLayout], which can be populated with UI elements to show on top of
     * the player.
     *
     * @return The overlay [FrameLayout], or `null` if the layout has been customized and
     * the overlay is not present.
     */
    var overlayFrameLayout: FrameLayout?

    private var player: SimpleExoPlayer? = null
    private var useController: Boolean = false
    private var useArtwork: Boolean = false
    private var defaultArtwork: Bitmap? = null
    private var controllerShowTimeoutMs: Int = 0
    /**
     * Returns whether the playback controls are automatically shown when playback starts, pauses,
     * ends, or fails. If set to false, the playback controls can be manually operated with [ ][.showController] and [.hideController].
     */
    /**
     * Sets whether the playback controls are automatically shown when playback starts, pauses, ends,
     * or fails. If set to false, the playback controls can be manually operated with [ ][.showController] and [.hideController].
     *
     * @param controllerAutoShow Whether the playback controls are allowed to show automatically.
     */
    var controllerAutoShow: Boolean = false
    private var controllerHideOnTouch: Boolean = false

    private var pausedFromPlayer = false

    private var enableMultiQuality = true

    private var multiQualitySelectorNavigator: MultiQualitySelectorAdapter.MultiQualitySelectorNavigator? = null

    private val audioManager: AudioManager?

    private var lastPlayedPosition = 0L
    private val mHits = LongArray(2)

    private var controllerBackgroundId = 0

    private val isPlayingAd: Boolean
        get() = player != null && player!!.isPlayingAd && player!!.playWhenReady

    var isPortrait: Boolean
        get() = controller != null && controller!!.isPortrait
        set(portrait) {
            if (controller != null) {
                controller!!.isPortrait = portrait
            }
        }

    init {

        audioManager = context.applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager

        if (isInEditMode) {
            contentFrame = null
            shutterView = null
            videoSurfaceView = null
            artworkView = null
            subtitleView = null
            controller = null
            componentListener = null
            overlayFrameLayout = null
            val logo = ImageView(context)
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(resources, logo)
            } else {
                configureEditModeLogo(resources, logo)
            }
            addView(logo)
        }

        var shutterColorSet = false
        var shutterColor = 0
        var playerLayoutId = R.layout.exo_video_view
        var useArtwork = true
        var defaultArtworkId = 0
        var useController = true
        var surfaceType = SURFACE_TYPE_SURFACE_VIEW
        var resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
        var controllerShowTimeoutMs = DEFAULT_SHOW_TIMEOUT_MS
        var controllerHideOnTouch = true
        var controllerAutoShow = true
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ExoVideoView,
                0,
                0
            )
            try {
                shutterColorSet = a.hasValue(R.styleable.ExoVideoView_shutter_background_color)
                shutterColor = a.getColor(
                    R.styleable.ExoVideoView_shutter_background_color,
                    shutterColor
                )
                playerLayoutId = a.getResourceId(
                    R.styleable.ExoVideoView_player_layout_id,
                    playerLayoutId
                )
                useArtwork = a.getBoolean(R.styleable.ExoVideoView_use_artwork, useArtwork)
                defaultArtworkId = a.getResourceId(
                    R.styleable.ExoVideoView_default_artwork,
                    defaultArtworkId
                )
                useController = a.getBoolean(R.styleable.ExoVideoView_use_controller, useController)
                surfaceType = a.getInt(R.styleable.ExoVideoView_surface_type, surfaceType)
                resizeMode = a.getInt(R.styleable.ExoVideoView_resize_mode, resizeMode)
                controllerShowTimeoutMs = a.getInt(
                    R.styleable.ExoVideoView_show_timeout,
                    controllerShowTimeoutMs
                )
                controllerHideOnTouch = a.getBoolean(
                    R.styleable.ExoVideoView_hide_on_touch,
                    controllerHideOnTouch
                )
                controllerAutoShow = a.getBoolean(
                    R.styleable.ExoVideoView_auto_show,
                    controllerAutoShow
                )
                enableMultiQuality = a.getBoolean(R.styleable.ExoVideoView_enable_multi_quality, true)

                controllerBackgroundId = a.getResourceId(R.styleable.ExoVideoView_controller_background, 0)
            } finally {
                a.recycle()
            }
        }

        LayoutInflater.from(context).inflate(playerLayoutId, this)
        componentListener = ComponentListener()
        descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS

        // Content frame.
        contentFrame = findViewById(R.id.exo_player_content_frame)
        if (contentFrame != null) {
            setResizeModeRaw(contentFrame!!, resizeMode)
        }

        // Shutter view.
        shutterView = findViewById(R.id.exo_player_shutter)
        if (shutterView != null && shutterColorSet) {
            shutterView!!.setBackgroundColor(shutterColor)
        }

        // Create a surface view and insert it into the content frame, if there is one.
        if (contentFrame != null && surfaceType != SURFACE_TYPE_NONE) {
            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            videoSurfaceView = if (surfaceType == SURFACE_TYPE_TEXTURE_VIEW)
                TextureView(context)
            else
                SurfaceView(context)
            videoSurfaceView!!.layoutParams = params
            contentFrame!!.addView(videoSurfaceView, 0)
        } else {
            videoSurfaceView = null
        }

        // Overlay frame layout.
        overlayFrameLayout = findViewById(R.id.exo_player_overlay)

        // Artwork view.
        artworkView = findViewById(R.id.exo_player_artwork)
        this.useArtwork = useArtwork && artworkView != null
        if (defaultArtworkId != 0) {
            defaultArtwork = BitmapFactory.decodeResource(context.resources, defaultArtworkId)
            setArtworkFromBitmap(defaultArtwork)
        }

        // Subtitle view.
        subtitleView = findViewById(R.id.exo_player_subtitles)
        if (subtitleView != null) {
            subtitleView!!.setUserDefaultStyle()
            subtitleView!!.setUserDefaultTextSize()
        }

        // Playback control view.
        val customController = findViewById<ExoVideoPlaybackControlView>(R.id.exo_player_controller)
        val controllerPlaceholder = findViewById<View>(R.id.exo_player_controller_placeholder)
        when {
            customController != null -> this.controller = customController
            controllerPlaceholder != null -> {
                // Propagate attrs as playbackAttrs so that PlaybackControlView's custom attributes are
                // transferred, but standard FrameLayout attributes (e.g. background) are not.
                this.controller = ExoVideoPlaybackControlView(context, null, 0, attrs)
                controller?.layoutParams = controllerPlaceholder.layoutParams
                val parent = controllerPlaceholder.parent as ViewGroup
                val controllerIndex = parent.indexOfChild(controllerPlaceholder)
                parent.removeView(controllerPlaceholder)
                parent.addView(controller, controllerIndex)
            }
            else -> this.controller = null
        }
        this.controllerShowTimeoutMs = if (controller != null) controllerShowTimeoutMs else 0
        this.controllerHideOnTouch = controllerHideOnTouch
        this.controllerAutoShow = controllerAutoShow
        this.useController = useController && controller != null

        if (useController && controller != null) {
            controller?.show()
            controller?.setVideoViewAccessor(this)
        }
        keepScreenOn = true
    }

    private val afChangeListener = object : AudioManager.OnAudioFocusChangeListener {
        override fun onAudioFocusChange(focusChange: Int) {
            when (focusChange) {
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> // Pause playback
                    pause()
                AudioManager.AUDIOFOCUS_GAIN -> // Resume playback
                    resume()
                AudioManager.AUDIOFOCUS_LOSS -> {
                    // audioManager.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
                    audioManager!!.abandonAudioFocus(this)
                    // Stop playback
                    stop()
                }
            }
        }
    }

    /**
     * Returns the player currently set on this view, or null if no player is set.
     */
    fun getPlayer(): SimpleExoPlayer? {
        return player
    }

    /**
     * Set the [SimpleExoPlayer] to use.
     *
     *
     * To transition a [SimpleExoPlayer] from targeting one view to another, it's recommended to
     * use [.switchTargetView] rather
     * than this method. If you do wish to use this method directly, be sure to attach the player to
     * the new view *before* calling `setPlayer(null)` to detach it from the old one.
     * This ordering is significantly more efficient and may allow for more seamless transitions.
     *
     * @param player The [SimpleExoPlayer] to use.
     */
    fun setPlayer(player: SimpleExoPlayer?) {
        if (this.player === player) {
            return
        }
        if (this.player != null) {
            componentListener?.let { this.player!!.removeListener(it) }
            componentListener?.let { this.player!!.removeTextOutput(it) }
            componentListener?.let { this.player!!.removeVideoListener(it) }
            if (videoSurfaceView is TextureView) {
                this.player!!.clearVideoTextureView(videoSurfaceView as TextureView?)
            } else if (videoSurfaceView is SurfaceView) {
                this.player!!.clearVideoSurfaceView(videoSurfaceView as SurfaceView?)
            }
        }
        this.player = player
        if (useController) {
            controller!!.player = player
        }
        if (shutterView != null) {
            shutterView!!.visibility = View.VISIBLE
        }
        if (player != null) {
            if (videoSurfaceView is TextureView) {
                player.setVideoTextureView(videoSurfaceView as TextureView?)
            } else if (videoSurfaceView is SurfaceView) {
                player.setVideoSurfaceView(videoSurfaceView as SurfaceView?)
            }
            componentListener?.let { player.addVideoListener(it) }
            componentListener?.let { player.addTextOutput(it) }
            componentListener?.let { player.addListener(it) }
            maybeShowController(false)
            updateForCurrentTrackSelections()
        } else {
            hideController()
            hideArtwork()
        }
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (videoSurfaceView is SurfaceView) {
            // Work around https://github.com/google/ExoPlayer/issues/3160.
            videoSurfaceView!!.visibility = visibility
        }
    }

    /**
     * Sets the resize mode.
     *
     * @param resizeMode The resize mode.
     */
    fun setResizeMode(@AspectRatioFrameLayout.ResizeMode resizeMode: Int) {
        Assertions.checkState(contentFrame != null)
        contentFrame!!.resizeMode = resizeMode
    }

    /**
     * Returns whether artwork is displayed if present in the media.
     */
    fun getUseArtwork(): Boolean {
        return useArtwork
    }

    /**
     * Sets whether artwork is displayed if present in the media.
     *
     * @param useArtwork Whether artwork is displayed.
     */
    fun setUseArtwork(useArtwork: Boolean) {
        Assertions.checkState(!useArtwork || artworkView != null)
        if (this.useArtwork != useArtwork) {
            this.useArtwork = useArtwork
            updateForCurrentTrackSelections()
        }
    }

    /**
     * Returns the default artwork to display.
     */
    fun getDefaultArtwork(): Bitmap? {
        return defaultArtwork
    }

    /**
     * Sets the default artwork to display if `useArtwork` is `true` and no artwork is
     * present in the media.
     *
     * @param defaultArtwork the default artwork to display.
     */
    fun setDefaultArtwork(defaultArtwork: Bitmap) {
        if (this.defaultArtwork != defaultArtwork) {
            this.defaultArtwork = defaultArtwork
            updateForCurrentTrackSelections()
        }
    }

    /**
     * Returns whether the playback controls can be shown.
     */
    fun getUseController(): Boolean {
        return useController
    }

    /**
     * Sets whether the playback controls can be shown. If set to `false` the playback controls
     * are never visible and are disconnected from the player.
     *
     * @param useController Whether the playback controls can be shown.
     */
    fun setUseController(useController: Boolean) {
        Assertions.checkState(!useController || controller != null)
        if (this.useController == useController) {
            return
        }
        this.useController = useController
        if (useController) {
            controller!!.player = player
        } else if (controller != null) {
            controller?.hide()
            controller?.player = null
        }
    }

    /**
     * Sets the background color of the `exo_shutter` view.
     *
     * @param color The background color.
     */
    fun setShutterBackgroundColor(color: Int) {
        if (shutterView != null) {
            shutterView!!.setBackgroundColor(color)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (audioManager != null) {
            requestAudioFocus()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        audioManager?.abandonAudioFocus(afChangeListener)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (player != null && player!!.isPlayingAd) {
            // Focus any overlay UI now, in case it's provided by a WebView whose contents may update
            // dynamically. This is needed to make the "Skip ad" button focused on Android TV when using
            // IMA [Internal: b/62371030].
            overlayFrameLayout!!.requestFocus()
            return super.dispatchKeyEvent(event)
        }
        val isDpadWhenControlHidden = (
            isDpadKey(event.keyCode) && useController &&
                !controller!!.isVisible
            )
        maybeShowController(true)
        return isDpadWhenControlHidden || dispatchMediaKeyEvent(event) || super.dispatchKeyEvent(event)
    }

    /**
     * Called to process media key events. Any [KeyEvent] can be passed but only media key
     * events will be handled. Does nothing if playback controls are disabled.
     *
     * @param event A key event.
     * @return Whether the key event was handled.
     */
    fun dispatchMediaKeyEvent(event: KeyEvent): Boolean {
        return useController && controller!!.dispatchMediaKeyEvent(event)
    }

    /**
     * Shows the playback controls. Does nothing if playback controls are disabled.
     *
     *
     *
     * The playback controls are automatically hidden during playback after
     * {[.getControllerShowTimeoutMs]}. They are shown indefinitely when playback has not
     * started yet, is paused, has ended or failed.
     */
    fun showController() {
        showController(shouldShowControllerIndefinitely())
    }

    /**
     * Hides the playback controls. Does nothing if playback controls are disabled.
     */
    fun hideController() {
        controller?.hide()
    }

    /**
     * Returns the playback controls timeout. The playback controls are automatically hidden after
     * this duration of time has elapsed without user input and with playback or buffering in
     * progress.
     *
     * @return The timeout in milliseconds. A non-positive value will cause the controller to remain
     * visible indefinitely.
     */
    fun getControllerShowTimeoutMs(): Int {
        return controllerShowTimeoutMs
    }

    /**
     * Sets the playback controls timeout. The playback controls are automatically hidden after this
     * duration of time has elapsed without user input and with playback or buffering in progress.
     *
     * @param controllerShowTimeoutMs The timeout in milliseconds. A non-positive value will cause
     * the controller to remain visible indefinitely.
     */
    fun setControllerShowTimeoutMs(controllerShowTimeoutMs: Int) {
        Assertions.checkState(controller != null)
        this.controllerShowTimeoutMs = controllerShowTimeoutMs
    }

    /**
     * Returns whether the playback controls are hidden by touch events.
     */
    fun getControllerHideOnTouch(): Boolean {
        return controllerHideOnTouch
    }

    /**
     * Sets whether the playback controls are hidden by touch events.
     *
     * @param controllerHideOnTouch Whether the playback controls are hidden by touch events.
     */
    fun setControllerHideOnTouch(controllerHideOnTouch: Boolean) {
        Assertions.checkState(controller != null)
        this.controllerHideOnTouch = controllerHideOnTouch
    }

    /**
     * Set the [PlaybackControlView.VisibilityListener].
     *
     * @param listener The listener to be notified about visibility changes.
     */
    fun setControllerVisibilityListener(listener: ExoVideoPlaybackControlView.VisibilityListener) {
        Assertions.checkState(controller != null)
        controller!!.setVisibilityListener(listener)
    }

    /**
     * Sets the [ControlDispatcher].
     *
     * @param controlDispatcher The [ControlDispatcher], or null to use
     * [ExoPlayer.DefaultEventListener].
     */
    fun setControlDispatcher(controlDispatcher: ControlDispatcher?) {
        Assertions.checkState(controller != null)
        controller!!.setControlDispatcher(controlDispatcher)
    }

    /**
     * Sets the rewind increment in milliseconds.
     *
     * @param rewindMs The rewind increment in milliseconds. A non-positive value will cause the
     * rewind button to be disabled.
     */
    fun setRewindIncrementMs(rewindMs: Int) {
        Assertions.checkState(controller != null)
        controller!!.setRewindIncrementMs(rewindMs)
    }

    /**
     * Sets the fast forward increment in milliseconds.
     *
     * @param fastForwardMs The fast forward increment in milliseconds. A non-positive value will
     * cause the fast forward button to be disabled.
     */
    fun setFastForwardIncrementMs(fastForwardMs: Int) {
        Assertions.checkState(controller != null)
        controller!!.setFastForwardIncrementMs(fastForwardMs)
    }

    /**
     * Sets which repeat toggle modes are enabled.
     *
     * @param repeatToggleModes A set of [RepeatModeUtil.RepeatToggleModes]
     */
    fun setRepeatToggleModes(@RepeatModeUtil.RepeatToggleModes repeatToggleModes: Int) {
        Assertions.checkState(controller != null)
        controller!!.setRepeatToggleModes(repeatToggleModes)
    }

    /**
     * Sets whether the shuffle button is shown.
     *
     * @param showShuffleButton Whether the shuffle button is shown.
     */
    fun setShowShuffleButton(showShuffleButton: Boolean) {
        Assertions.checkState(controller != null)
        controller!!.setShowShuffleButton(showShuffleButton)
    }

    /**
     * Sets whether the time bar should show all windows, as opposed to just the current one.
     *
     * @param showMultiWindowTimeBar Whether to show all windows.
     */
    fun setShowMultiWindowTimeBar(showMultiWindowTimeBar: Boolean) {
        Assertions.checkState(controller != null)
        controller!!.setShowMultiWindowTimeBar(showMultiWindowTimeBar)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {

        if (!useController || player == null || ev.actionMasked != MotionEvent.ACTION_DOWN) {
            return false
        }
        if (enableMultiQuality) {
            val v = overlayFrameLayout!!.findViewById<RecyclerView>(R.id.exo_player_quality_container)
            if (v != null && overlayFrameLayout!!.visibility == View.VISIBLE) {
                return true
            }
        }

        if (!controller!!.isVisible) {
            maybeShowController(true)
        } else if (controllerHideOnTouch) {
            controller?.hide()
        }
        return true
    }

    override fun onTrackballEvent(ev: MotionEvent): Boolean {
        if (!useController || player == null) {
            return false
        }
        maybeShowController(true)
        return true
    }

    /**
     * Shows the playback controls, but only if forced or shown indefinitely.
     */
    private fun maybeShowController(isForced: Boolean) {
        if (isPlayingAd) {
            // Never show the controller if an ad is currently playing.
            return
        }
        if (useController) {
            val wasShowingIndefinitely = controller!!.isVisible && controller!!.showTimeoutMs <= 0
            val shouldShowIndefinitely = shouldShowControllerIndefinitely()
            if (isForced || wasShowingIndefinitely || shouldShowIndefinitely) {
                showController(shouldShowIndefinitely)
            }
        }
    }

    private fun shouldShowControllerIndefinitely(): Boolean {
        if (player == null) {
            return true
        }
        val playbackState = player!!.playbackState
        return controllerAutoShow && (
            playbackState == Player.STATE_IDLE ||
                playbackState == Player.STATE_ENDED || !player!!.playWhenReady
            )
    }

    private fun showController(showIndefinitely: Boolean) {
        if (!useController) {
            return
        }
        controller!!.showTimeoutMs = if (showIndefinitely) 0 else controllerShowTimeoutMs
        controller?.show()
    }

    private fun updateForCurrentTrackSelections() {
        if (player == null) {
            return
        }
        val selections = player!!.currentTrackSelections
        for (i in 0 until selections.length) {
            if (player!!.getRendererType(i) == C.TRACK_TYPE_VIDEO && selections.get(i) != null) {
                // Video enabled so artwork must be hidden. If the shutter is closed, it will be opened in
                // onRenderedFirstFrame().
                hideArtwork()
                return
            }
        }
        // Video disabled so the shutter must be closed.
        if (shutterView != null) {
            shutterView!!.visibility = View.VISIBLE
        }
        // Display artwork if enabled and available, else hide it.
        if (useArtwork) {
            for (i in 0 until selections.length) {
                val selection = selections.get(i)
                if (selection != null) {
                    for (j in 0 until selection.length()) {
                        val metadata = selection.getFormat(j).metadata
                        if (metadata != null && setArtworkFromMetadata(metadata)) {
                            return
                        }
                    }
                }
            }
            if (setArtworkFromBitmap(defaultArtwork)) {
                return
            }
        }
        // Artwork disabled or unavailable.
        hideArtwork()
    }

    private fun setArtworkFromMetadata(metadata: Metadata): Boolean {
        for (i in 0 until metadata.length()) {
            val metadataEntry = metadata.get(i)
            if (metadataEntry is ApicFrame) {
                val bitmapData = metadataEntry.pictureData
                val bitmap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size)
                return setArtworkFromBitmap(bitmap)
            }
        }
        return false
    }

    private fun setArtworkFromBitmap(bitmap: Bitmap?): Boolean {
        if (bitmap != null) {
            val bitmapWidth = bitmap.width
            val bitmapHeight = bitmap.height
            if (bitmapWidth > 0 && bitmapHeight > 0) {
                if (contentFrame != null) {
                    contentFrame!!.setAspectRatio(bitmapWidth.toFloat() / bitmapHeight)
                }
                artworkView!!.setImageBitmap(bitmap)
                artworkView!!.visibility = View.VISIBLE
                return true
            }
        }
        return false
    }

    private fun hideArtwork() {
        if (artworkView != null) {
            artworkView!!.setImageResource(android.R.color.transparent) // Clears any bitmap reference.
            artworkView!!.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("InlinedApi")
    private fun isDpadKey(keyCode: Int): Boolean {
        return (
            keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT ||
                keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT ||
                keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT ||
                keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_UP_LEFT ||
                keyCode == KeyEvent.KEYCODE_DPAD_CENTER
            )
    }

    fun play(mediaSource: ExoMediaSource, where: Long) {
        play(mediaSource, true, where)
    }

    @JvmOverloads
    fun play(mediaSource: ExoMediaSource, playWhenReady: Boolean = true, where: Long = C.TIME_UNSET) {
        releasePlayer()
        val creator = MediaSourceCreator(context.applicationContext)
        createExoPlayer(creator)
        controller?.setMediaSource(mediaSource)

        if (enableMultiQuality && mediaSource.qualities().isNotEmpty()) {
            addMultiQualitySelector(mediaSource)
        }

        playInternal(mediaSource, playWhenReady, where, creator)
    }

    fun pause() {
        if (player == null) {
            return
        }

        if (player!!.playWhenReady) {
            lastPlayedPosition = player!!.currentPosition
            player!!.playWhenReady = false
            pausedFromPlayer = false
        } else {
            pausedFromPlayer = true
        }
    }

    fun resume() {
        if (player == null) {
            return
        }

        if (player!!.playWhenReady) {
            return
        }

        if (!pausedFromPlayer) {
            player!!.seekTo(if (lastPlayedPosition - 500 < 0) 0 else lastPlayedPosition - 500)
            player!!.playWhenReady = true
        }
    }

    fun stop() {
        if (player != null) {
            player!!.stop()
        }
    }

    private fun playInternal(mediaSource: ExoMediaSource, playWhenReady: Boolean, where: Long, creator: MediaSourceCreator) {
        val tmp = creator.buildMediaSource(mediaSource.uri(), mediaSource.extension())
        player!!.prepare(tmp)
        if (where == C.TIME_UNSET) {
            player!!.seekTo(0L)
        } else {
            player!!.seekTo(where)
        }

        player!!.playWhenReady = requestAudioFocus() && playWhenReady
    }

    private fun createExoPlayer(creator: MediaSourceCreator) {
        if (player != null) {
            releasePlayer()
        }

        val renderersFactory = DefaultRenderersFactory(context)
            .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF)

        val internalPlayer = ExoPlayerFactory.newSimpleInstance(context, renderersFactory, creator.trackSelector)
        componentListener?.let { internalPlayer.addListener(it) }
        internalPlayer.addListener(creator.getEventLogger())
        internalPlayer.addMetadataOutput(creator.getEventLogger())
        setPlayer(internalPlayer)
    }

    fun releasePlayer() {
        if (player != null) {
            player!!.release()
        }

        player = null
    }

    private fun requestAudioFocus(): Boolean {

        if (audioManager == null) {
            return true
        }
        // Request audio focus for playback
        val result = audioManager.requestAudioFocus(
            afChangeListener,
            // Use the music stream.
            AudioManager.STREAM_MUSIC,
            // Request permanent focus.
            AudioManager.AUDIOFOCUS_GAIN
        )
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    fun setBackListener(backListener: ExoVideoPlaybackControlView.ExoClickListener) {
        controller?.setBackListener(backListener)
    }

    fun setOrientationListener(orientationListener: ExoVideoPlaybackControlView.OrientationListener) {
        controller?.setOrientationListener(orientationListener)
    }

    fun setMultiQualitySelectorNavigator(navigator: MultiQualitySelectorAdapter.MultiQualitySelectorNavigator) {
        this.multiQualitySelectorNavigator = navigator
    }

    fun changeWidgetVisibility(id: Int, visibility: Int) {
        controller?.changeWidgetVisibility(id, visibility)
    }

    fun setControllerDisplayMode(displayMode: Int) {
        controller?.setControllerDisplayMode(displayMode)
    }

    private fun addMultiQualitySelector(mediaSource: ExoMediaSource) {
        for (quality in mediaSource.qualities()) {
            if (TextUtils.equals(quality.uri.toString(), mediaSource.uri().toString())) {
                controller?.updateQualityDes(quality.displayName)
                break
            }
        }

        controller?.setVisibilityCallback(
            object : MultiQualitySelectorAdapter.VisibilityCallback {
                override fun shouldChangeVisibility(visibility: Int) {
                    overlayFrameLayout!!.visibility = visibility
                    if (visibility !== View.VISIBLE) {
                        controller?.show()
                    }
                }
            }
        )

        val adapter = MultiQualitySelectorAdapter(
            mediaSource.qualities(),
            object : MultiQualitySelectorAdapter.MultiQualitySelectorNavigator {

                override fun onQualitySelected(quality: ExoMediaSource.Quality): Boolean {
                    if (player == null) {
                        return false
                    }

                    if (multiQualitySelectorNavigator == null || !multiQualitySelectorNavigator!!.onQualitySelected(quality)) {
                        val current = player!!.currentPosition
                        val playWhenReady = player!!.playWhenReady
                        val creator = MediaSourceCreator(context.applicationContext)
                        val tmp = creator.buildMediaSource(quality.uri, null)
                        player!!.playWhenReady = requestAudioFocus() && playWhenReady
                        player!!.prepare(tmp)
                        player!!.seekTo(current)
                    }

                    if (controller != null) {
                        controller!!.updateQualityDes(quality.displayName)
                    }

                    overlayFrameLayout!!.visibility = View.GONE

                    return false
                }
            }
        )
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return controller?.onKeyDown(keyCode, event) ?: super.onKeyDown(keyCode, event)
    }

    override fun attachVideoView(): View {
        return this
    }

    /**
     * add your view to controller
     *
     * @param customViewType the target view type
     * @param customView     the view you want to add
     * @param removeViews    remove all views in target view before add if true
     */
    @JvmOverloads
    fun addCustomView(@ExoVideoPlaybackControlView.CustomViewType customViewType: Int, customView: View, removeViews: Boolean = false) {
        if (useController && controller != null) {
            controller?.addCustomView(customViewType, customView, removeViews)
        }
    }

    fun setGestureEnabled(enabled: Boolean) {
        controller?.setGestureEnabled(enabled)
    }

    private inner class ComponentListener : TextOutput, Player.EventListener, com.google.android.exoplayer2.video.VideoListener {

        // TextOutput implementation

        override fun onCues(cues: List<Cue>) {
            if (subtitleView != null) {
                subtitleView!!.onCues(cues)
            }
        }

        // SimpleExoPlayer.VideoInfoListener implementation

        override fun onVideoSizeChanged(
            width: Int,
            height: Int,
            unappliedRotationDegrees: Int,
            pixelWidthHeightRatio: Float
        ) {
            if (contentFrame != null) {
                val aspectRatio = if (height == 0) 1F else (width * pixelWidthHeightRatio / height)
                contentFrame!!.setAspectRatio(aspectRatio)
            }
        }

        override fun onRenderedFirstFrame() {
            if (shutterView != null) {
                shutterView!!.visibility = View.INVISIBLE
            }
        }

        override fun onTracksChanged(tracks: TrackGroupArray, selections: TrackSelectionArray) {
            updateForCurrentTrackSelections()
        }

        // Player.EventListener implementation

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            if (isPlayingAd) {
                hideController()
            } else {
                maybeShowController(false)
            }
        }

        override fun onPositionDiscontinuity(@Player.DiscontinuityReason reason: Int) {
            if (isPlayingAd) {
                hideController()
            }
        }
    }

    companion object {

        private val SURFACE_TYPE_NONE = 0
        private val SURFACE_TYPE_SURFACE_VIEW = 1
        private val SURFACE_TYPE_TEXTURE_VIEW = 2

        /**
         * Switches the view targeted by a given [SimpleExoPlayer].
         *
         * @param player        The player whose target view is being switched.
         * @param oldPlayerView The old view to detach from the player.
         * @param newPlayerView The new view to attach to the player.
         */
        fun switchTargetView(
            player: SimpleExoPlayer,
            oldPlayerView: ExoVideoView?,
            newPlayerView: ExoVideoView?
        ) {
            if (oldPlayerView === newPlayerView) {
                return
            }
            // We attach the new view before detaching the old one because this ordering allows the player
            // to swap directly from one surface to another, without transitioning through a state where no
            // surface is attached. This is significantly more efficient and achieves a more seamless
            // transition when using platform provided video decoders.
            newPlayerView?.setPlayer(player)
            oldPlayerView?.setPlayer(null)
        }

        @TargetApi(23)
        private fun configureEditModeLogoV23(resources: Resources, logo: ImageView) {
            logo.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, null))
            logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null))
        }

        private fun configureEditModeLogo(resources: Resources, logo: ImageView) {
            logo.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo))
            logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color))
        }

        private fun setResizeModeRaw(aspectRatioFrame: AspectRatioFrameLayout, resizeMode: Int) {
            aspectRatioFrame.resizeMode = resizeMode
        }
    }
}
