package ng.groove.exovideoview.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.SystemClock
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.TypedValue
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.hls.HlsManifest
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist
import com.google.android.exoplayer2.ui.DefaultTimeBar
import com.google.android.exoplayer2.ui.TimeBar
import com.google.android.exoplayer2.util.Assertions
import com.google.android.exoplayer2.util.RepeatModeUtil
import com.google.android.exoplayer2.util.Util
import ng.groove.exovideoview.R
import ng.groove.exovideoview.extension.MultiQualitySelectorAdapter
import ng.groove.exovideoview.gesture.OnVideoGestureChangeListener
import ng.groove.exovideoview.gesture.OnVideoGestureChangeListener.Companion.VOLUME_CHANGED_INCREMENT
import ng.groove.exovideoview.gesture.OnVideoGestureChangeListener.Companion.VOLUME_CHANGED_MUTE
import ng.groove.exovideoview.gesture.VideoGesture
import ng.groove.exovideoview.media.ExoMediaSource
import ng.groove.exovideoview.orientation.OnOrientationChangedListener
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_LANDSCAPE
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_PORTRAIT
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_UNKNOWN
import ng.groove.exovideoview.orientation.SensorOrientation
import java.util.*

/**
 *
 *
 *
 *
 *
 * * <h3>Specifying a custom layout file</h3>
 * Defining your own `exo_video_playback_control_view.xml` is useful to customize the layout of
 * ExoVideoPlaybackControlView throughout your application. It's also possible to customize the layout for a
 * single instance in a layout file. This is achieved by setting the `controller_layout_id`
 * attribute on a ExoVideoPlaybackControlView. This will cause the specified layout to be inflated instead
 * of `exo_video_playback_control_view.xml` for only the instance on which the attribute is set.
 */

class ExoVideoPlaybackControlView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    playbackAttrs: AttributeSet? = attrs
) : FrameLayout(context, attrs, defStyleAttr) {

    private val componentListener: ComponentListener
    private val previousButton: View?
    private val nextButton: View?
    private val playButton: View?
    private val pauseButton: View?
    private val fastForwardButton: View?
    private val rewindButton: View?
    private val repeatToggleButton: ImageView?
    private val shuffleButton: View?
    private val durationView: TextView?
    private val positionView: TextView?
    private val positionViewLandscape: TextView?

    private var isAttachedToWindow: Boolean? = false

    private val timeBar: TimeBar?
    private val formatBuilder: StringBuilder
    private val formatter: Formatter
    private val period: Timeline.Period
    private val window: Timeline.Window

    private val repeatOffButtonDrawable: Drawable
    private val repeatOneButtonDrawable: Drawable
    private val repeatAllButtonDrawable: Drawable
    private val repeatOffButtonContentDescription: String
    private val repeatOneButtonContentDescription: String
    private val repeatAllButtonContentDescription: String

    /**
     * Returns the [Player] currently being controlled by this view, or null if no player is
     * set.
     */
    /**
     * Sets the [Player] to control.
     *
     * @param player The [Player] to control.
     */
    var player: Player? = null
        set(player) {
            if (this.player === player) {
                return
            }
            if (this.player != null) {
                this.player!!.removeListener(componentListener)
            }
            field = player
            player?.addListener(componentListener)
            updateAll()
        }
    private var controlDispatcher: com.google.android.exoplayer2.ControlDispatcher? = null
    private var visibilityListener: VisibilityListener? = null

    private var showMultiWindowTimeBar: Boolean = false
    private var multiWindowTimeBar: Boolean = false
    private var scrubbing: Boolean = false
    private var rewindMs: Int = 0
    private var fastForwardMs: Int = 0
    /**
     * Returns the playback controls timeout. The playback controls are automatically hidden after
     * this duration of time has elapsed without user input.
     *
     * @return The duration in milliseconds. A non-positive value indicates that the controls will
     * remain visible indefinitely.
     */
    /**
     * Sets the playback controls timeout. The playback controls are automatically hidden after this
     * duration of time has elapsed without user input.
     *
     * @param showTimeoutMs The duration in milliseconds. A non-positive value will cause the controls
     * to remain visible indefinitely.
     */
    var showTimeoutMs: Int = 0
    @RepeatModeUtil.RepeatToggleModes
    private var repeatToggleModes: Int = 0
    private var showShuffleButton: Boolean = false
    private var hideAtMs: Long = 0
    private var adGroupTimesMs: LongArray? = null
    private var playedAdGroups: BooleanArray? = null
    private var extraAdGroupTimesMs: LongArray? = null
    private var extraPlayedAdGroups: BooleanArray? = null

    private val updateProgressAction = Runnable { this.updateProgress() }

    private val hideAction = Runnable { this.hide() }

    private val timeBarLandscape: TimeBar?
    private val playButtonLandScape: View?
    private val pauseButtonLandScape: View?
    private val durationViewLandscape: TextView?
    private val enterFullscreen: View?
    private val exitFullscreen: View?

    private val exoPlayerControllerTop: View?
    private val exoPlayerControllerTopLandscape: View?
    private val exoPlayerControllerBottom: View?
    private val exoPlayerControllerBottomLandscape: View?
    private val fastForwardButtonLandscape: View?
    private val rewindButtonLandscape: View?
    private val previousButtonLandscape: View?
    private val nextButtonLandscape: View?

    private val centerInfoWrapper: View?
    private val centerInfo: TextView?

    private val exoPlayerVideoName: TextView?
    private val exoPlayerVideoNameLandscape: TextView?

    private val exoPlayerCurrentQualityLandscape: TextView?

    private val topCustomView: ViewGroup?
    private val topCustomViewLandscape: ViewGroup?
    private val bottomCustomViewLandscape: ViewGroup?

    private val centerError: TextView?
    private val loadingBar: ProgressBar?

    private val back: View?
    private val backLandscape: View?

    var isPortrait = true
        set(portrait) {
            field = portrait
            showControllerByDisplayMode()
        }

    private val sensorOrientation: SensorOrientation

    private var orientationListener: OrientationListener? = null
    private var backListener: ExoClickListener? = null

    private var isHls: Boolean = false

    private var displayMode = CONTROLLER_MODE_ALL

    private var qualityVisibilityCallback: MultiQualitySelectorAdapter.VisibilityCallback? = null

    private var videoViewAccessor: VideoViewAccessor? = null
    private var videoGesture: VideoGesture? = null

    /**
     * Returns whether the controller is currently visible.
     */
    val isVisible: Boolean
        get() = visibility == View.VISIBLE

    /**
     * to get  [ExoVideoView]
     */
    interface VideoViewAccessor {

        fun attachVideoView(): View
    }

    /**
     * to get  [Player]
     */
    interface
    PlayerAccessor {

        fun attachPlayer(): Player
    }

    /**
     * Listener to be notified about changes of the visibility of the UI control.
     */
    interface VisibilityListener {

        /**
         * Called when the visibility changes.
         *
         * @param visibility The new visibility. Either [View.VISIBLE] or [View.GONE].
         */
        fun onVisibilityChange(visibility: Int)
    }

    interface ExoClickListener {

        /***
         * called when buttons clicked in controller
         * @param view  null when back pressed.
         * @param isPortrait the controller is portrait  or not
         * @return will interrupt operation in controller if return true
         */
        fun onClick(view: View?, isPortrait: Boolean): Boolean
    }

    interface OrientationListener {
        fun onOrientationChanged(@OnOrientationChangedListener.SensorOrientationType orientation: Int)
    }

    @IntDef(CONTROLLER_MODE_NONE, CONTROLLER_MODE_ALL, CONTROLLER_MODE_TOP, CONTROLLER_MODE_TOP_LANDSCAPE, CONTROLLER_MODE_BOTTOM, CONTROLLER_MODE_BOTTOM_LANDSCAPE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ControllerModeType

    @IntDef(CUSTOM_VIEW_TOP, CUSTOM_VIEW_TOP_LANDSCAPE, CUSTOM_VIEW_BOTTOM_LANDSCAPE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class CustomViewType

    init {
        var controllerLayoutId = R.layout.exo_video_playback_control_view
        rewindMs = DEFAULT_REWIND_MS
        fastForwardMs = DEFAULT_FAST_FORWARD_MS
        showTimeoutMs = DEFAULT_SHOW_TIMEOUT_MS
        repeatToggleModes = DEFAULT_REPEAT_TOGGLE_MODES
        showShuffleButton = false
        var enableGesture = true

        var controllerBackgroundId = 0

        if (playbackAttrs != null) {
            val a = context.theme.obtainStyledAttributes(
                playbackAttrs,
                R.styleable.ExoVideoPlaybackControlView,
                0,
                0
            )
            try {
                rewindMs = a.getInt(R.styleable.ExoVideoPlaybackControlView_rewind_increment, rewindMs)
                fastForwardMs = a.getInt(
                    R.styleable.ExoVideoPlaybackControlView_fastforward_increment,
                    fastForwardMs
                )
                showTimeoutMs = a.getInt(R.styleable.ExoVideoPlaybackControlView_show_timeout, showTimeoutMs)
                controllerLayoutId = a.getResourceId(R.styleable.ExoVideoPlaybackControlView_controller_layout_id, controllerLayoutId)
                repeatToggleModes = getRepeatToggleModes(a, repeatToggleModes)
                showShuffleButton = a.getBoolean(
                    R.styleable.ExoVideoPlaybackControlView_show_shuffle_button,
                    showShuffleButton
                )
                displayMode = a.getInt(R.styleable.ExoVideoPlaybackControlView_controller_display_mode, CONTROLLER_MODE_ALL)

                controllerBackgroundId = a.getResourceId(R.styleable.ExoVideoPlaybackControlView_controller_background, 0)
                enableGesture = a.getBoolean(R.styleable.ExoVideoPlaybackControlView_enableGesture, enableGesture)
            } finally {
                a.recycle()
            }
        }

        period = Timeline.Period()
        window = Timeline.Window()
        formatBuilder = StringBuilder()
        formatter = Formatter(formatBuilder, Locale.getDefault())
        adGroupTimesMs = LongArray(0)
        playedAdGroups = BooleanArray(0)
        extraAdGroupTimesMs = LongArray(0)
        extraPlayedAdGroups = BooleanArray(0)
        componentListener = ComponentListener()
        controlDispatcher = com.google.android.exoplayer2.DefaultControlDispatcher()

        LayoutInflater.from(context).inflate(controllerLayoutId, this)
        descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS

        durationView = findViewById(R.id.exo_player_duration)
        positionView = findViewById(R.id.exo_player_position)
        timeBar = findViewById<DefaultTimeBar>(R.id.exo_player_progress)
        timeBar?.addListener(componentListener)

        playButton = findViewById(R.id.exo_player_play)
        playButton?.setOnClickListener(componentListener)

        pauseButton = findViewById(R.id.exo_player_pause)
        pauseButton?.setOnClickListener(componentListener)

        previousButton = findViewById(R.id.exo_prev)
        previousButton?.setOnClickListener(componentListener)
        nextButton = findViewById(R.id.exo_next)
        nextButton?.setOnClickListener(componentListener)
        rewindButton = findViewById(R.id.exo_rew)
        rewindButton?.setOnClickListener(componentListener)
        fastForwardButton = findViewById(R.id.exo_ffwd)
        fastForwardButton?.setOnClickListener(componentListener)
        repeatToggleButton = findViewById(R.id.exo_repeat_toggle)
        repeatToggleButton?.setOnClickListener(componentListener)
        shuffleButton = findViewById(R.id.exo_shuffle)
        shuffleButton?.setOnClickListener(componentListener)
        val resources = context.resources
        repeatOffButtonDrawable = resources.getDrawable(R.drawable.exo_controls_repeat_off)
        repeatOneButtonDrawable = resources.getDrawable(R.drawable.exo_controls_repeat_one)
        repeatAllButtonDrawable = resources.getDrawable(R.drawable.exo_controls_repeat_all)
        repeatOffButtonContentDescription = resources.getString(
            R.string.exo_controls_repeat_off_description
        )
        repeatOneButtonContentDescription = resources.getString(
            R.string.exo_controls_repeat_one_description
        )
        repeatAllButtonContentDescription = resources.getString(
            R.string.exo_controls_repeat_all_description
        )

        durationViewLandscape = findViewById(R.id.exo_player_duration_landscape)
        positionViewLandscape = findViewById(R.id.exo_player_position_landscape)

        timeBarLandscape = findViewById<DefaultTimeBar>(R.id.exo_player_progress_landscape)
        timeBarLandscape?.addListener(componentListener)

        previousButtonLandscape = findViewById(R.id.exo_player_prev_landscape)
        previousButtonLandscape?.setOnClickListener(componentListener)

        nextButtonLandscape = findViewById(R.id.exo_player_next_landscape)
        nextButtonLandscape?.setOnClickListener(componentListener)

        playButtonLandScape = findViewById(R.id.exo_player_play_landscape)
        playButtonLandScape?.setOnClickListener(componentListener)

        rewindButtonLandscape = findViewById(R.id.exo_player_rewind_landscape)
        rewindButtonLandscape?.setOnClickListener(componentListener)
        fastForwardButtonLandscape = findViewById(R.id.exo_player_ffwd_landscape)
        fastForwardButtonLandscape?.setOnClickListener(componentListener)

        pauseButtonLandScape = findViewById(R.id.exo_player_pause_landscape)
        pauseButtonLandScape?.setOnClickListener(componentListener)

        enterFullscreen = findViewById(R.id.exo_player_enter_fullscreen)
        enterFullscreen?.setOnClickListener(componentListener)

        exitFullscreen = findViewById(R.id.exo_player_exit_fullscreen)
        exitFullscreen?.setOnClickListener(componentListener)

        centerInfoWrapper = findViewById(R.id.exo_player_center_info_wrapper)
        centerInfo = findViewById(R.id.exo_player_center_text)

        exoPlayerControllerTop = findViewById(R.id.exo_player_controller_top)
        if (exoPlayerControllerTop != null && controllerBackgroundId != 0) {
            exoPlayerControllerTop.setBackgroundResource(controllerBackgroundId)
        }

        exoPlayerControllerTopLandscape = findViewById(R.id.exo_player_controller_top_landscape)
        if (exoPlayerControllerTopLandscape != null && controllerBackgroundId != 0) {
            exoPlayerControllerTopLandscape.setBackgroundResource(controllerBackgroundId)
        }

        exoPlayerControllerBottom = findViewById(R.id.exo_player_controller_bottom)
        if (exoPlayerControllerBottom != null && controllerBackgroundId != 0) {
            exoPlayerControllerBottom.setBackgroundResource(controllerBackgroundId)
        }

        exoPlayerControllerBottomLandscape = findViewById(R.id.exo_player_controller_bottom_landscape)
        if (exoPlayerControllerBottomLandscape != null && controllerBackgroundId != 0) {
            exoPlayerControllerBottomLandscape.setBackgroundResource(controllerBackgroundId)
        }

        exoPlayerVideoName = findViewById(R.id.exo_player_video_name)
        exoPlayerVideoName?.setOnClickListener(componentListener)

        exoPlayerVideoNameLandscape = findViewById(R.id.exo_player_video_name_landscape)
        exoPlayerVideoNameLandscape?.setOnClickListener(componentListener)

        back = findViewById(R.id.exo_player_controller_back)
        back?.setOnClickListener(componentListener)

        backLandscape = findViewById(R.id.exo_player_controller_back_landscape)
        backLandscape?.setOnClickListener(componentListener)

        if (centerInfoWrapper != null) {
            setupVideoGesture(enableGesture)
        }

        exoPlayerCurrentQualityLandscape = findViewById(R.id.exo_player_current_quality_landscape)
        exoPlayerCurrentQualityLandscape?.setOnClickListener(componentListener)

        topCustomView = findViewById(R.id.exo_player_controller_top_custom_view)
        topCustomViewLandscape = findViewById(R.id.exo_player_controller_top_custom_view_landscape)
        bottomCustomViewLandscape = findViewById(R.id.exo_player_controller_bottom_custom_view_landscape)

        centerError = findViewById(R.id.exo_player_center_error)
        loadingBar = findViewById(R.id.exo_player_loading)
        sensorOrientation = SensorOrientation(
            getContext(),
            object : OnOrientationChangedListener {
                override fun onChanged(orientation: Int) {
                    changeOrientation(orientation)
                }
            }
        )

        showControllerByDisplayMode()

        showUtilHideCalled()
    }

    private fun setupVideoGesture(enableGesture: Boolean) {
        val onVideoGestureChangeListener = object : OnVideoGestureChangeListener {

            override fun onVolumeChanged(range: Int, type: Int) {
                show()
                val drawableId: Int = when (type) {
                    VOLUME_CHANGED_MUTE -> R.drawable.ic_volume_mute_white_36dp
                    VOLUME_CHANGED_INCREMENT -> R.drawable.ic_volume_up_white_36dp
                    else -> R.drawable.ic_volume_down_white_36dp
                }
                setVolumeOrBrightnessInfo(context.getString(R.string.volume_changing, range), drawableId)
            }

            override fun onBrightnessChanged(brightnessPercent: Int) {
                show()
                val info = context.getString(R.string.brightness_changing, brightnessPercent)
                val drawable = whichBrightnessImageToUse(brightnessPercent)
                setVolumeOrBrightnessInfo(info, drawable)
            }

            override fun onNoGesture() {

                if (centerInfo == null) {
                    return
                }
                centerInfo.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out))
                centerInfo.visibility = View.GONE
            }

            override fun onShowSeekSize(seekSize: Long, fastForward: Boolean) {
                if (isHls) {
                    return
                }

                show()
                seekTo(seekSize)
                if (centerInfo == null) {
                    return
                }

                if (centerError != null && centerError.visibility == View.VISIBLE) {
                    centerError.visibility = View.GONE
                }
                centerInfo.visibility = View.VISIBLE
                centerInfo.text = generateFastForwardOrRewindTxt(seekSize)
                val drawableId = if (fastForward) R.drawable.ic_fast_forward_white_36dp else R.drawable.ic_fast_rewind_white_36dp
                val drawable = ContextCompat.getDrawable(context, drawableId)
                centerInfo.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
            }
        }

        videoGesture = VideoGesture(
            context,
            onVideoGestureChangeListener,
            object : PlayerAccessor {
                override fun attachPlayer(): Player {
                    return player!!
                }
            }
        )

        if (!enableGesture) {
            videoGesture!!.disable()
        }
        centerInfoWrapper!!.setOnClickListener(componentListener)
        centerInfoWrapper.setOnTouchListener(videoGesture)
    }

    private fun generateFastForwardOrRewindTxt(changingTime: Long): CharSequence {

        val duration = if (this.player == null) 0 else this.player!!.duration
        var result = Util.getStringForTime(formatBuilder, formatter, changingTime)
        result = "$result/"
        result += Util.getStringForTime(formatBuilder, formatter, duration)

        val index = result.indexOf("/")

        val spannableString = SpannableString(result)

        val typedValue = TypedValue()
        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
        val color = a.getColor(0, 0)
        a.recycle()
        spannableString.setSpan(ForegroundColorSpan(color), 0, index, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.WHITE), index, result.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        return spannableString
    }

    @DrawableRes
    private fun whichBrightnessImageToUse(brightnessInt: Int): Int {
        return when {
            brightnessInt <= 15 -> R.drawable.ic_brightness_1_white_36dp
            brightnessInt <= 30 -> R.drawable.ic_brightness_2_white_36dp
            brightnessInt <= 45 -> R.drawable.ic_brightness_3_white_36dp
            brightnessInt <= 60 -> R.drawable.ic_brightness_4_white_36dp
            brightnessInt <= 75 -> R.drawable.ic_brightness_5_white_36dp
            brightnessInt <= 90 -> R.drawable.ic_brightness_6_white_36dp
            else -> R.drawable.ic_brightness_7_white_36dp
        }
    }

    private fun setVolumeOrBrightnessInfo(txt: String, @DrawableRes drawableId: Int) {
        if (centerInfo == null) {
            return
        }

        if (centerError != null && centerError.visibility == View.VISIBLE) {
            centerError.visibility = View.GONE
        }

        centerInfo.visibility = View.VISIBLE
        centerInfo.text = txt
        centerInfo.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        centerInfo.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context, drawableId), null, null)
    }

    /**
     * Sets whether the time bar should show all windows, as opposed to just the current one. If the
     * timeline has a period with unknown duration or more than
     * [.MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR] windows the time bar will fall back to showing a
     * single window.
     *
     * @param showMultiWindowTimeBar Whether the time bar should show all windows.
     */
    fun setShowMultiWindowTimeBar(showMultiWindowTimeBar: Boolean) {
        this.showMultiWindowTimeBar = showMultiWindowTimeBar
        updateTimeBarMode()
    }

    /**
     * Sets the millisecond positions of extra ad markers relative to the start of the window (or
     * timeline, if in multi-window mode) and whether each extra ad has been played or not. The
     * markers are shown in addition to any ad markers for ads in the player's timeline.
     *
     * @param extraAdGroupTimesMs The millisecond timestamps of the extra ad markers to show, or
     * `null` to show no extra ad markers.
     * @param extraPlayedAdGroups Whether each ad has been played, or `null` to show no extra ad
     * markers.
     */
    fun setExtraAdGroupMarkers(
        extraAdGroupTimesMs: LongArray?,
        extraPlayedAdGroups: BooleanArray?
    ) {
        if (extraAdGroupTimesMs == null) {
            this.extraAdGroupTimesMs = LongArray(0)
            this.extraPlayedAdGroups = BooleanArray(0)
        } else {
            Assertions.checkArgument(extraAdGroupTimesMs.size == extraPlayedAdGroups!!.size)
            this.extraAdGroupTimesMs = extraAdGroupTimesMs
            this.extraPlayedAdGroups = extraPlayedAdGroups
        }
        updateProgress()
    }

    /**
     * Sets the [VisibilityListener].
     *
     * @param listener The listener to be notified about visibility changes.
     */
    fun setVisibilityListener(listener: VisibilityListener) {
        this.visibilityListener = listener
    }

    /**
     * Sets the [com.google.android.exoplayer2.ControlDispatcher].
     *
     * @param controlDispatcher The [com.google.android.exoplayer2.ControlDispatcher], or null
     * to use [com.google.android.exoplayer2.DefaultControlDispatcher].
     */
    fun setControlDispatcher(
        controlDispatcher: com.google.android.exoplayer2.ControlDispatcher?
    ) {
        this.controlDispatcher = controlDispatcher
            ?: com.google.android.exoplayer2.DefaultControlDispatcher()
    }

    /**
     * Sets the rewind increment in milliseconds.
     *
     * @param rewindMs The rewind increment in milliseconds. A non-positive value will cause the
     * rewind button to be disabled.
     */
    fun setRewindIncrementMs(rewindMs: Int) {
        this.rewindMs = rewindMs
        updateNavigation()
    }

    /**
     * Sets the fast forward increment in milliseconds.
     *
     * @param fastForwardMs The fast forward increment in milliseconds. A non-positive value will
     * cause the fast forward button to be disabled.
     */
    fun setFastForwardIncrementMs(fastForwardMs: Int) {
        this.fastForwardMs = fastForwardMs
        updateNavigation()
    }

    /**
     * Returns which repeat toggle modes are enabled.
     *
     * @return The currently enabled [RepeatModeUtil.RepeatToggleModes].
     */
    @RepeatModeUtil.RepeatToggleModes
    fun getRepeatToggleModes(): Int {
        return repeatToggleModes
    }

    /**
     * Sets which repeat toggle modes are enabled.
     *
     * @param repeatToggleModes A set of [RepeatModeUtil.RepeatToggleModes].
     */
    fun setRepeatToggleModes(@RepeatModeUtil.RepeatToggleModes repeatToggleModes: Int) {
        this.repeatToggleModes = repeatToggleModes
        if (this.player != null) {
            @Player.RepeatMode val currentMode = this.player!!.repeatMode
            if (repeatToggleModes == RepeatModeUtil.REPEAT_TOGGLE_MODE_NONE && currentMode != Player.REPEAT_MODE_OFF) {
                controlDispatcher?.dispatchSetRepeatMode(player!!, Player.REPEAT_MODE_OFF)
            } else if (repeatToggleModes == RepeatModeUtil.REPEAT_TOGGLE_MODE_ONE && currentMode == Player.REPEAT_MODE_ALL) {
                controlDispatcher?.dispatchSetRepeatMode(player!!, Player.REPEAT_MODE_ONE)
            } else if (repeatToggleModes == RepeatModeUtil.REPEAT_TOGGLE_MODE_ALL && currentMode == Player.REPEAT_MODE_ONE) {
                controlDispatcher?.dispatchSetRepeatMode(player!!, Player.REPEAT_MODE_ALL)
            }
        }
    }

    /**
     * Returns whether the shuffle button is shown.
     */
    fun getShowShuffleButton(): Boolean {
        return showShuffleButton
    }

    /**
     * Sets whether the shuffle button is shown.
     *
     * @param showShuffleButton Whether the shuffle button is shown.
     */
    fun setShowShuffleButton(showShuffleButton: Boolean) {
        this.showShuffleButton = showShuffleButton
        updateShuffleButton()
    }

    /**
     * Shows the playback controls. If [.getShowTimeoutMs] is positive then the controls will
     * be automatically hidden after this duration of time has elapsed without user input.
     */

    fun show() {

        if (!isVisible) {
            visibility = View.VISIBLE
            if (visibilityListener != null) {
                visibilityListener!!.onVisibilityChange(visibility)
            }

            changeSystemUiVisibilityPortrait()

            updateAll()
            requestPlayPauseFocus()
        }
        // Call hideAfterTimeout even if already visible to reset the timeout.
        hideAfterTimeout()
    }

    /**
     * Hides the controller.
     */
    fun hide() {
        if (isVisible) {
            visibility = View.GONE
            if (visibilityListener != null) {
                visibilityListener!!.onVisibilityChange(visibility)
            }
            removeCallbacks(updateProgressAction)
            removeCallbacks(hideAction)
            hideAtMs = C.TIME_UNSET

            if (!isPortrait) {
                changeSystemUiVisibilityLandscape()
            }
        }
    }

    private fun hideAfterTimeout() {
        removeCallbacks(hideAction)
        if (showTimeoutMs > 0) {
            hideAtMs = SystemClock.uptimeMillis() + showTimeoutMs
            if (isAttachedToWindow == true) {
                postDelayed(hideAction, showTimeoutMs.toLong())
            }
        } else {
            hideAtMs = C.TIME_UNSET
        }
    }

    private fun updateAll() {
        updatePlayPauseButton()
        updateNavigation()
        updateRepeatModeButton()
        updateShuffleButton()
        updateProgress()
    }

    private fun updateNavigation() {
        if (!isVisible || !isAttachedToWindow!!) {
            return
        }
        val timeline = if (this.player != null) this.player!!.currentTimeline else null
        val haveNonEmptyTimeline = timeline != null && !timeline.isEmpty
        var isSeekable = false
        var enablePrevious = false
        var enableNext = false
        if (haveNonEmptyTimeline && !this.player!!.isPlayingAd) {
            val windowIndex = this.player!!.currentWindowIndex
            timeline!!.getWindow(windowIndex, window)
            isSeekable = window.isSeekable
            enablePrevious = (
                isSeekable || !window.isDynamic ||
                    this.player!!.previousWindowIndex != C.INDEX_UNSET
                )
            enableNext = window.isDynamic || this.player!!.nextWindowIndex != C.INDEX_UNSET
        }
        setButtonEnabled(enablePrevious, previousButton)
        setButtonEnabled(enableNext, nextButton)
        setButtonEnabled(enablePrevious, previousButtonLandscape)
        setButtonEnabled(enableNext, nextButtonLandscape)

        setButtonEnabled(fastForwardMs > 0 && isSeekable, fastForwardButton)
        setButtonEnabled(fastForwardMs > 0 && isSeekable, fastForwardButtonLandscape)
        setButtonEnabled(rewindMs > 0 && isSeekable, rewindButton)
        setButtonEnabled(rewindMs > 0 && isSeekable, rewindButtonLandscape)
        timeBar?.setEnabled(isSeekable && !isHls)
        timeBarLandscape?.setEnabled(isSeekable && !isHls)
    }

    private fun updatePlayPauseButton() {
        if (!isVisible || !isAttachedToWindow!!) {
            return
        }
        var requestPlayPauseFocus = false
        val playing = this.player != null && this.player!!.playWhenReady
        if (playButton != null) {
            requestPlayPauseFocus = requestPlayPauseFocus or (playing && playButton.isFocused)
            playButton.visibility = if (playing) View.GONE else View.VISIBLE
        }
        if (pauseButton != null) {
            requestPlayPauseFocus = requestPlayPauseFocus or (!playing && pauseButton.isFocused)
            pauseButton.visibility = if (!playing) View.GONE else View.VISIBLE
        }

        if (playButtonLandScape != null) {
            requestPlayPauseFocus = requestPlayPauseFocus or (playing && playButtonLandScape.isFocused)
            playButtonLandScape.visibility = if (playing) View.GONE else View.VISIBLE
        }
        if (pauseButtonLandScape != null) {
            requestPlayPauseFocus = requestPlayPauseFocus or (!playing && pauseButtonLandScape.isFocused)
            pauseButtonLandScape.visibility = if (!playing) View.GONE else View.VISIBLE
        }

        if (requestPlayPauseFocus) {
            requestPlayPauseFocus()
        }
    }

    private fun updateRepeatModeButton() {
        if (!isVisible || !isAttachedToWindow!! || repeatToggleButton == null) {
            return
        }
        if (repeatToggleModes == RepeatModeUtil.REPEAT_TOGGLE_MODE_NONE) {
            repeatToggleButton.visibility = View.GONE
            return
        }
        if (this.player == null) {
            setButtonEnabled(false, repeatToggleButton)
            return
        }
        setButtonEnabled(true, repeatToggleButton)
        when (this.player!!.repeatMode) {
            Player.REPEAT_MODE_OFF -> {
                repeatToggleButton.setImageDrawable(repeatOffButtonDrawable)
                repeatToggleButton.contentDescription = repeatOffButtonContentDescription
            }
            Player.REPEAT_MODE_ONE -> {
                repeatToggleButton.setImageDrawable(repeatOneButtonDrawable)
                repeatToggleButton.contentDescription = repeatOneButtonContentDescription
            }
            Player.REPEAT_MODE_ALL -> {
                repeatToggleButton.setImageDrawable(repeatAllButtonDrawable)
                repeatToggleButton.contentDescription = repeatAllButtonContentDescription
            }
        }
        repeatToggleButton.visibility = View.VISIBLE
    }

    private fun updateShuffleButton() {
        if (!isVisible || !isAttachedToWindow!! || shuffleButton == null) {
            return
        }
        if (!showShuffleButton) {
            shuffleButton.visibility = View.GONE
        } else if (this.player == null) {
            setButtonEnabled(false, shuffleButton)
        } else {
            shuffleButton.alpha = if (this.player!!.shuffleModeEnabled) 1f else 0.3f
            shuffleButton.isEnabled = true
            shuffleButton.visibility = View.VISIBLE
        }
    }

    private fun updateTimeBarMode() {
        if (this.player == null) {
            return
        }
        multiWindowTimeBar = showMultiWindowTimeBar && canShowMultiWindowTimeBar(this.player!!.currentTimeline, window)
    }

    private fun updateProgress() {
        if (!isVisible || !isAttachedToWindow!!) {
            return
        }

        var position: Long = 0
        var bufferedPosition: Long = 0
        var duration: Long = 0
        if (this.player != null) {
            var currentWindowTimeBarOffsetUs: Long = 0
            var durationUs: Long = 0
            var adGroupCount = 0
            val timeline = this.player!!.currentTimeline
            if (!timeline.isEmpty) {
                val currentWindowIndex = this.player!!.currentWindowIndex
                val firstWindowIndex = if (multiWindowTimeBar) 0 else currentWindowIndex
                val lastWindowIndex = if (multiWindowTimeBar) timeline.windowCount - 1 else currentWindowIndex
                for (i in firstWindowIndex..lastWindowIndex) {
                    if (i == currentWindowIndex) {
                        currentWindowTimeBarOffsetUs = durationUs
                    }
                    timeline.getWindow(i, window)
                    if (window.durationUs == C.TIME_UNSET) {
                        Assertions.checkState(!multiWindowTimeBar)
                        break
                    }
                    for (j in window.firstPeriodIndex..window.lastPeriodIndex) {
                        timeline.getPeriod(j, period)
                        val periodAdGroupCount = period.adGroupCount
                        for (adGroupIndex in 0 until periodAdGroupCount) {
                            var adGroupTimeInPeriodUs = period.getAdGroupTimeUs(adGroupIndex)
                            if (adGroupTimeInPeriodUs == C.TIME_END_OF_SOURCE) {
                                if (period.durationUs == C.TIME_UNSET) {
                                    // Don't show ad markers for postrolls in periods with unknown duration.
                                    continue
                                }
                                adGroupTimeInPeriodUs = period.durationUs
                            }
                            val adGroupTimeInWindowUs = adGroupTimeInPeriodUs + period.positionInWindowUs
                            if (adGroupTimeInWindowUs >= 0 && adGroupTimeInWindowUs <= window.durationUs) {
                                if (adGroupCount == adGroupTimesMs!!.size) {
                                    val newLength = if (adGroupTimesMs!!.size == 0) 1 else adGroupTimesMs!!.size * 2
                                    adGroupTimesMs = Arrays.copyOf(adGroupTimesMs, newLength)
                                    playedAdGroups = Arrays.copyOf(playedAdGroups, newLength)
                                }
                                adGroupTimesMs!![adGroupCount] = C.usToMs(durationUs + adGroupTimeInWindowUs)
                                playedAdGroups!![adGroupCount] = period.hasPlayedAdGroup(adGroupIndex)
                                adGroupCount++
                            }
                        }
                    }
                    durationUs += window.durationUs
                }
            }
            duration = C.usToMs(durationUs)
            position = C.usToMs(currentWindowTimeBarOffsetUs)
            bufferedPosition = position
            if (this.player!!.isPlayingAd) {
                position += this.player!!.contentPosition
                bufferedPosition = position
            } else {
                position += this.player!!.currentPosition
                bufferedPosition += this.player!!.bufferedPosition
            }
            if (timeBar != null) {
                val extraAdGroupCount = extraAdGroupTimesMs!!.size
                val totalAdGroupCount = adGroupCount + extraAdGroupCount
                if (totalAdGroupCount > adGroupTimesMs!!.size) {
                    adGroupTimesMs = Arrays.copyOf(adGroupTimesMs, totalAdGroupCount)
                    playedAdGroups = Arrays.copyOf(playedAdGroups, totalAdGroupCount)
                }
                System.arraycopy(extraAdGroupTimesMs!!, 0, adGroupTimesMs, adGroupCount, extraAdGroupCount)
                System.arraycopy(extraPlayedAdGroups!!, 0, playedAdGroups, adGroupCount, extraAdGroupCount)
                timeBar.setAdGroupTimesMs(adGroupTimesMs, playedAdGroups, totalAdGroupCount)
            }
        }
        if (durationView != null && !isHls) {
            durationView.text = Util.getStringForTime(formatBuilder, formatter, duration)
        }

        if (durationViewLandscape != null && !isHls) {
            val durationStr = Util.getStringForTime(formatBuilder, formatter, duration)
            durationViewLandscape.text = durationStr
        }

        if (positionViewLandscape != null && !isHls) {
            val positionStr = Util.getStringForTime(formatBuilder, formatter, position)
            positionViewLandscape.text = positionStr
        }

        if (positionView != null && !scrubbing && !isHls) {
            positionView.text = Util.getStringForTime(formatBuilder, formatter, position)
        }

        if (timeBar != null && !isHls) {
            timeBar.setPosition(position)
            timeBar.setBufferedPosition(bufferedPosition)
            timeBar.setDuration(duration)
        }

        if (timeBarLandscape != null && !isHls) {
            timeBarLandscape.setPosition(position)
            timeBarLandscape.setBufferedPosition(bufferedPosition)
            timeBarLandscape.setDuration(duration)
        }

        // Cancel any pending updates and schedule a new one if necessary.
        removeCallbacks(updateProgressAction)
        val playbackState = if (this.player == null) Player.STATE_IDLE else this.player!!.playbackState
        if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
            val delayMs: Long
            if (this.player!!.playWhenReady && playbackState == Player.STATE_READY) {
                val playbackSpeed = this.player!!.playbackParameters.speed
                if (playbackSpeed <= 0.1f) {
                    delayMs = 1000
                } else if (playbackSpeed <= 5f) {
                    val mediaTimeUpdatePeriodMs = (1000 / Math.max(1, Math.round(1 / playbackSpeed))).toLong()
                    var mediaTimeDelayMs = mediaTimeUpdatePeriodMs - position % mediaTimeUpdatePeriodMs
                    if (mediaTimeDelayMs < mediaTimeUpdatePeriodMs / 5) {
                        mediaTimeDelayMs += mediaTimeUpdatePeriodMs
                    }
                    delayMs = if (playbackSpeed == 1f)
                        mediaTimeDelayMs
                    else
                        (mediaTimeDelayMs / playbackSpeed).toLong()
                } else {
                    delayMs = 200
                }
            } else {
                delayMs = 1000
            }
            postDelayed(updateProgressAction, delayMs)
        }
    }

    private fun requestPlayPauseFocus() {
        val playing = this.player != null && this.player!!.playWhenReady
        if (!playing && playButton != null) {
            playButton.requestFocus()
        } else if (playing && pauseButton != null) {
            pauseButton.requestFocus()
        }

        if (!playing && playButtonLandScape != null) {
            playButtonLandScape.requestFocus()
        } else if (playing && pauseButtonLandScape != null) {
            pauseButtonLandScape.requestFocus()
        }
    }

    private fun setButtonEnabled(enabled: Boolean, view: View?) {
        if (view == null) {
            return
        }
        view.isEnabled = enabled
        view.alpha = if (enabled) 1f else 0.3f
        view.visibility = View.VISIBLE
    }

    private fun previous() {
        val timeline = this.player!!.currentTimeline
        if (timeline.isEmpty) {
            return
        }
        val windowIndex = this.player!!.currentWindowIndex
        timeline.getWindow(windowIndex, window)
        val previousWindowIndex = this.player!!.previousWindowIndex
        if (previousWindowIndex != C.INDEX_UNSET && (this.player!!.currentPosition <= MAX_POSITION_FOR_SEEK_TO_PREVIOUS || window.isDynamic && !window.isSeekable)) {
            seekTo(previousWindowIndex, C.TIME_UNSET)
        } else {
            seekTo(0)
        }
    }

    private operator fun next() {
        val timeline = this.player!!.currentTimeline
        if (timeline.isEmpty) {
            return
        }
        val windowIndex = this.player!!.currentWindowIndex
        val nextWindowIndex = this.player!!.nextWindowIndex
        if (nextWindowIndex != C.INDEX_UNSET) {
            seekTo(nextWindowIndex, C.TIME_UNSET)
        } else if (timeline.getWindow(windowIndex, window, false).isDynamic) {
            seekTo(windowIndex, C.TIME_UNSET)
        }
    }

    private fun rewind() {
        if (rewindMs <= 0) {
            return
        }
        seekTo(Math.max(this.player!!.currentPosition - rewindMs, 0))
    }

    private fun fastForward() {
        if (fastForwardMs <= 0) {
            return
        }
        val durationMs = this.player!!.duration
        var seekPositionMs = this.player!!.currentPosition + fastForwardMs
        if (durationMs != C.TIME_UNSET) {
            seekPositionMs = Math.min(seekPositionMs, durationMs)
        }
        seekTo(seekPositionMs)
    }

    private fun seekTo(positionMs: Long) {
        seekTo(this.player!!.currentWindowIndex, positionMs)
    }

    private fun seekTo(windowIndex: Int, positionMs: Long) {
        val dispatched = controlDispatcher!!.dispatchSeekTo(player!!, windowIndex, positionMs)
        if (!dispatched) {
            // The seek wasn't dispatched. If the progress bar was dragged by the user to perform the
            // seek then it'll now be in the wrong position. Trigger a progress update to snap it back.
            updateProgress()
        }
    }

    private fun seekToTimeBarPosition(positionMs: Long) {
        var positionMs = positionMs
        var windowIndex: Int
        val timeline = this.player!!.currentTimeline
        if (multiWindowTimeBar && !timeline.isEmpty) {
            val windowCount = timeline.windowCount
            windowIndex = 0
            while (true) {
                val windowDurationMs = timeline.getWindow(windowIndex, window).durationMs
                if (positionMs < windowDurationMs) {
                    break
                } else if (windowIndex == windowCount - 1) {
                    // Seeking past the end of the last window should seek to the end of the timeline.
                    positionMs = windowDurationMs
                    break
                }
                positionMs -= windowDurationMs
                windowIndex++
            }
        } else {
            windowIndex = this.player!!.currentWindowIndex
        }
        seekTo(windowIndex, positionMs)
    }

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        sensorOrientation.enable()
        isAttachedToWindow = true
        if (hideAtMs != C.TIME_UNSET) {
            val delayMs = hideAtMs - SystemClock.uptimeMillis()
            if (delayMs <= 0) {
                hide()
            } else {
                postDelayed(hideAction, delayMs)
            }
        }
        updateAll()
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        sensorOrientation.disable()
        this.isAttachedToWindow = false
        removeCallbacks(updateProgressAction)
        removeCallbacks(hideAction)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return dispatchMediaKeyEvent(event) || super.dispatchKeyEvent(event)
    }

    /**
     * Called to process media key events. Any [KeyEvent] can be passed but only media key
     * events will be handled.
     *
     * @param event A key event.
     * @return Whether the key event was handled.
     */
    fun dispatchMediaKeyEvent(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        if (this.player == null || !isHandledMediaKey(keyCode)) {
            return false
        }
        if (event.action == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD) {
                fastForward()
            } else if (keyCode == KeyEvent.KEYCODE_MEDIA_REWIND) {
                rewind()
            } else if (event.repeatCount == 0) {
                when (keyCode) {
                    KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE -> controlDispatcher!!.dispatchSetPlayWhenReady(player!!, !this.player!!.playWhenReady)
                    KeyEvent.KEYCODE_MEDIA_PLAY -> controlDispatcher!!.dispatchSetPlayWhenReady(player!!, true)
                    KeyEvent.KEYCODE_MEDIA_PAUSE -> controlDispatcher!!.dispatchSetPlayWhenReady(player!!, false)
                    KeyEvent.KEYCODE_MEDIA_NEXT -> next()
                    KeyEvent.KEYCODE_MEDIA_PREVIOUS -> previous()
                    else -> {
                    }
                }
            }
        }
        return true
    }

    fun setBackListener(backListener: ExoClickListener) {
        this.backListener = backListener
    }

    private fun toggleControllerOrientation() {
        if (orientationListener == null) {
            isPortrait = !isPortrait
        } else {
            changeOrientation(if (isPortrait) SENSOR_LANDSCAPE else SENSOR_PORTRAIT)
        }
    }

    fun setOrientationListener(orientationListener: OrientationListener) {
        this.orientationListener = orientationListener
    }

    fun setMediaSource(exoMediaSource: ExoMediaSource) {
        exoPlayerVideoName?.setText(exoMediaSource.name())

        exoPlayerVideoNameLandscape?.setText(exoMediaSource.name())

        if (centerError != null) {
            centerError.text = null
            centerError.visibility = View.GONE
        }
    }

    fun setControllerDisplayMode(displayMode: Int) {
        this.displayMode = displayMode
        showControllerByDisplayMode()
    }

    private fun showControllerByDisplayMode() {

        if (exoPlayerControllerTop != null) {
            val showByMode = displayMode and CONTROLLER_MODE_TOP == CONTROLLER_MODE_TOP
            if (isPortrait) {
                val visibility = if (showByMode) View.VISIBLE else View.INVISIBLE
                exoPlayerControllerTop.visibility = visibility
            } else {
                exoPlayerControllerTop.visibility = View.INVISIBLE
            }
        }

        if (exoPlayerControllerTopLandscape != null) {
            val showByMode = displayMode and CONTROLLER_MODE_TOP_LANDSCAPE == CONTROLLER_MODE_TOP_LANDSCAPE
            if (isPortrait) {
                exoPlayerControllerTopLandscape.visibility = View.INVISIBLE
            } else {
                val visibility = if (showByMode) View.VISIBLE else View.INVISIBLE
                exoPlayerControllerTopLandscape.visibility = visibility
            }
        }

        if (exoPlayerControllerBottom != null) {
            val showByMode = displayMode and CONTROLLER_MODE_BOTTOM == CONTROLLER_MODE_BOTTOM
            if (isPortrait) {
                val visibility = if (showByMode) View.VISIBLE else View.INVISIBLE
                exoPlayerControllerBottom.visibility = visibility
            } else {

                exoPlayerControllerBottom.visibility = View.INVISIBLE
            }
        }

        if (exoPlayerControllerBottomLandscape != null) {
            val showByMode = displayMode and CONTROLLER_MODE_BOTTOM_LANDSCAPE == CONTROLLER_MODE_BOTTOM_LANDSCAPE
            if (isPortrait) {
                exoPlayerControllerBottomLandscape.visibility = View.INVISIBLE
            } else {
                val visibility = if (showByMode) View.VISIBLE else View.INVISIBLE
                exoPlayerControllerBottomLandscape.visibility = visibility
            }
        }

        if (qualityVisibilityCallback != null) {
            qualityVisibilityCallback!!.shouldChangeVisibility(View.GONE)
        }
    }

    @Synchronized
    private fun changeOrientation(@OnOrientationChangedListener.SensorOrientationType orientation: Int) {
        val context = context
        val activity: Activity
        if (context !is Activity) {
            return
        }

        if (orientationListener == null) {
            return
        }

        activity = context
        when (orientation) {
            SENSOR_PORTRAIT -> {
                isPortrait = true
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                changeSystemUiVisibilityPortrait()
            }
            SENSOR_LANDSCAPE -> {
                isPortrait = false
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                changeSystemUiVisibilityLandscape()
            }
            SENSOR_UNKNOWN -> {
            }
            else -> {
            }
        }

        orientationListener!!.onOrientationChanged(orientation)
    }

    private fun changeSystemUiVisibilityPortrait() {
        videoViewAccessor?.attachVideoView()?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    private fun changeSystemUiVisibilityLandscape() {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            ?: return

        var flag = (
            View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            flag = flag or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        videoViewAccessor?.attachVideoView()?.systemUiVisibility = flag
    }

    fun setVideoViewAccessor(videoViewAccessor: VideoViewAccessor) {
        this.videoViewAccessor = videoViewAccessor
    }

    fun setVisibilityCallback(qualityVisibilityCallback: MultiQualitySelectorAdapter.VisibilityCallback) {
        this.qualityVisibilityCallback = qualityVisibilityCallback
    }

    fun updateQualityDes(qualityDes: CharSequence) {
        if (exoPlayerCurrentQualityLandscape != null) {
            exoPlayerCurrentQualityLandscape.text = qualityDes
        }
    }

    /**
     * add your view to controller
     *
     * @param customViewType the target view type
     * @param customView     the view you want to add
     * @param removeViews    remove all views in target view before add  if true
     */
    @JvmOverloads
    fun addCustomView(@CustomViewType customViewType: Int, customView: View, removeViews: Boolean = false) {
        var viewGroup: ViewGroup? = null
        if (customViewType == CUSTOM_VIEW_TOP && topCustomView != null) {
            viewGroup = topCustomView
        } else if (customViewType == CUSTOM_VIEW_TOP_LANDSCAPE && topCustomView != null) {
            viewGroup = topCustomViewLandscape
        } else if (customViewType == CUSTOM_VIEW_BOTTOM_LANDSCAPE && topCustomView != null) {
            viewGroup = bottomCustomViewLandscape
        }

        if (viewGroup != null) {
            if (removeViews) {
                viewGroup.removeAllViews()
            }
            viewGroup.addView(customView)
        }
    }

    fun changeWidgetVisibility(id: Int, visibility: Int) {
        val view = findViewById<View>(id)
        if (view != null) {
            view.visibility = visibility
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (loadingBar == null) {
            return
        }
        if (isLoading) {
            loadingBar.visibility = View.VISIBLE
        } else {
            loadingBar.visibility = View.GONE
        }
    }

    fun showUtilHideCalled() {
        if (!isVisible) {
            visibility = View.VISIBLE
            if (visibilityListener != null) {
                visibilityListener!!.onVisibilityChange(visibility)
            }
            updateAll()
        }
    }

    fun setGestureEnabled(enabled: Boolean) {
        if (centerInfoWrapper == null) {
            return
        }

        if (videoGesture == null) {
            return
        }

        if (enabled) {
            videoGesture!!.enable()
        } else {
            videoGesture!!.disable()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (backListener != null) {
                if (!backListener!!.onClick(null, isPortrait)) {
                    if (isPortrait) {
                        return super.onKeyDown(keyCode, event)
                    } else {
                        changeOrientation(SENSOR_PORTRAIT)
                        return true
                    }
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private inner class ComponentListener : TimeBar.OnScrubListener, View.OnClickListener, Player.EventListener {

        internal var mHits = LongArray(2)

        override fun onScrubStart(timeBar: TimeBar, position: Long) {
            removeCallbacks(hideAction)
            scrubbing = true
        }

        override fun onScrubMove(timeBar: TimeBar, position: Long) {
            if (positionView != null) {
                positionView.text = Util.getStringForTime(formatBuilder, formatter, position)
            }
        }

        override fun onScrubStop(timeBar: TimeBar, position: Long, canceled: Boolean) {
            scrubbing = false
            if (!canceled && player != null) {
                seekToTimeBarPosition(position)
            }
            hideAfterTimeout()
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

            if (playbackState != Player.STATE_IDLE && centerError != null && centerError.visibility == View.VISIBLE) {
                centerError.visibility = View.GONE
            }

            if (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_BUFFERING) {
                removeCallbacks(hideAction)
                showUtilHideCalled()
                showLoading(true)
            } else if (playbackState == Player.STATE_READY && player!!.playWhenReady || playbackState == Player.STATE_ENDED) {
                showLoading(false)
                hide()
            }

            updatePlayPauseButton()
            updateProgress()
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
            updateRepeatModeButton()
            updateNavigation()
        }

        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
            updateShuffleButton()
            updateNavigation()
        }

        override fun onPositionDiscontinuity(@Player.DiscontinuityReason reason: Int) {
            updateNavigation()
            updateProgress()
        }

        override fun onTimelineChanged(timeline: Timeline, manifest: Any?, reason: Int) {
            if (manifest is HlsManifest) {
                val hlsManifest = manifest as HlsManifest?
                isHls = !hlsManifest!!.mediaPlaylist.hasEndTag && hlsManifest.mediaPlaylist.playlistType == HlsMediaPlaylist.PLAYLIST_TYPE_UNKNOWN
            } else {
                isHls = false
            }

            updateNavigation()
            updateTimeBarMode()
            updateProgress()
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            if (loadingBar != null) {
                loadingBar.visibility = View.GONE
            }
            if (centerError != null) {
                val errorText = resources.getString(R.string.player_error, error.type)
                centerError.text = errorText
                centerError.visibility = View.VISIBLE
            }
        }

        override fun onClick(view: View) {
            if (player != null) {
                if (nextButton === view || nextButtonLandscape === view) {
                    next()
                } else if (previousButton === view || previousButtonLandscape === view) {
                    previous()
                } else if (fastForwardButton === view || fastForwardButtonLandscape === view) {
                    fastForward()
                } else if (rewindButton === view || rewindButtonLandscape === view) {
                    rewind()
                } else if (playButton === view || playButtonLandScape === view) {
                    controlDispatcher!!.dispatchSetPlayWhenReady(player!!, true)
                } else if (pauseButton === view || pauseButtonLandScape === view) {
                    controlDispatcher!!.dispatchSetPlayWhenReady(player!!, false)
                } else if (repeatToggleButton === view) {
                    controlDispatcher!!.dispatchSetRepeatMode(
                        player!!,
                        RepeatModeUtil.getNextRepeatMode(
                            player!!.repeatMode,
                            repeatToggleModes
                        )
                    )
                } else if (shuffleButton === view) {
                    controlDispatcher!!.dispatchSetShuffleModeEnabled(player!!, !player!!.shuffleModeEnabled)
                } else if (enterFullscreen === view) {
                    changeOrientation(SENSOR_LANDSCAPE)
                } else if (exitFullscreen === view) {
                    changeOrientation(SENSOR_PORTRAIT)
                } else if (exoPlayerVideoName === view || back === view) {
                    if (backListener != null) {
                        if (!backListener!!.onClick(view, isPortrait)) {
                            changeOrientation(SENSOR_LANDSCAPE)
                        }
                    }
                } else if (exoPlayerVideoNameLandscape === view || backLandscape === view) {
                    if (backListener != null) {
                        if (!backListener!!.onClick(view, isPortrait)) {
                            changeOrientation(SENSOR_PORTRAIT)
                        }
                    }
                } else if (centerInfoWrapper === view) {
                    playOrPause()
                } else if (exoPlayerCurrentQualityLandscape === view && qualityVisibilityCallback != null) {
                    hide()
                    qualityVisibilityCallback!!.shouldChangeVisibility(View.VISIBLE)
                }
            }
            hideAfterTimeout()
        }

        private fun playOrPause() {

            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()

            if (500 > SystemClock.uptimeMillis() - mHits[0]) {
                player?.let { controlDispatcher?.dispatchSetPlayWhenReady(it, !player!!.playWhenReady) }
            }
        }
    }

    companion object {

        /**
         * The default fast forward increment, in milliseconds.
         */
        val DEFAULT_FAST_FORWARD_MS = 10000
        /**
         * The default rewind increment, in milliseconds.
         */
        val DEFAULT_REWIND_MS = 10000
        /**
         * The default show timeout, in milliseconds.
         */
        val DEFAULT_SHOW_TIMEOUT_MS = 5000
        /**
         * The default repeat toggle modes.
         */
        @RepeatModeUtil.RepeatToggleModes
        val DEFAULT_REPEAT_TOGGLE_MODES = RepeatModeUtil.REPEAT_TOGGLE_MODE_NONE

        /**
         * The maximum number of windows that can be shown in a multi-window time bar.
         */
        val MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100

        private val MAX_POSITION_FOR_SEEK_TO_PREVIOUS: Long = 3000

        //        <!--<enum getDisplayName="all" value="0b1111"/>-->
        //    <!--<enum getDisplayName="top" value="0b1000"/>-->
        //    <!--<enum getDisplayName="top_landscape" value="0b0100"/>-->
        //    <!--<enum getDisplayName="bottom" value="0b0010"/>-->
        //    <!--<enum getDisplayName="bottom_landscape" value="0b0001"/>-->
        const val CONTROLLER_MODE_NONE = 0
        const val CONTROLLER_MODE_ALL = 15
        const val CONTROLLER_MODE_TOP = 8
        const val CONTROLLER_MODE_TOP_LANDSCAPE = 4
        const val CONTROLLER_MODE_BOTTOM = 2
        const val CONTROLLER_MODE_BOTTOM_LANDSCAPE = 1

        const val CUSTOM_VIEW_TOP = 1
        const val CUSTOM_VIEW_TOP_LANDSCAPE = CUSTOM_VIEW_TOP + 1
        const val CUSTOM_VIEW_BOTTOM_LANDSCAPE = CUSTOM_VIEW_TOP_LANDSCAPE + 1

        @RepeatModeUtil.RepeatToggleModes
        private fun getRepeatToggleModes(
            a: TypedArray,
            @RepeatModeUtil.RepeatToggleModes repeatToggleModes: Int
        ): Int {
            return a.getInt(R.styleable.ExoVideoPlaybackControlView_repeat_toggle_modes, repeatToggleModes)
        }

        @SuppressLint("InlinedApi")
        private fun isHandledMediaKey(keyCode: Int): Boolean {
            return (
                keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_REWIND ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_PLAY ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_NEXT ||
                    keyCode == KeyEvent.KEYCODE_MEDIA_PREVIOUS
                )
        }

        /**
         * Returns whether the specified `timeline` can be shown on a multi-window time bar.
         *
         * @param timeline The [Timeline] to check.
         * @param window   A scratch [Timeline.Window] instance.
         * @return Whether the specified timeline can be shown on a multi-window time bar.
         */
        private fun canShowMultiWindowTimeBar(timeline: Timeline, window: Timeline.Window): Boolean {
            if (timeline.windowCount > MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR) {
                return false
            }
            val windowCount = timeline.windowCount
            for (i in 0 until windowCount) {
                if (timeline.getWindow(i, window).durationUs == C.TIME_UNSET) {
                    return false
                }
            }
            return true
        }
    }
}
