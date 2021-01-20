package ng.groove.exovideoview.gesture

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.google.android.exoplayer2.Timeline
import ng.groove.exovideoview.gesture.OnVideoGestureChangeListener.Companion.VOLUME_CHANGED_INCREMENT
import ng.groove.exovideoview.gesture.OnVideoGestureChangeListener.Companion.VOLUME_CHANGED_MUTE
import ng.groove.exovideoview.gesture.OnVideoGestureChangeListener.Companion.VOLUME_CHANGED_REDUCTION
import ng.groove.exovideoview.ui.ExoVideoPlaybackControlView
import ng.groove.exovideoview.utils.Permissions

class VideoGesture(
    private val context: Context,
    private val onVideoGestureChangeListener: OnVideoGestureChangeListener?,
    private val playerAccessor: ExoVideoPlaybackControlView.PlayerAccessor
) : View.OnTouchListener {

    private val window: Timeline.Window

    // touch
    private var mTouchAction = TOUCH_NONE
    private var mSurfaceYDisplayRange: Int = 0
    private var mInitTouchY: Float = 0.toFloat()
    private var touchX = -1f
    private var touchY = -1f

    // Volume
    private var mAudioManager: AudioManager? = null
    private var mAudioMax: Int = 0
    private var mVol: Float = 0.toFloat()

    // Brightness
    private var mIsFirstBrightnessGesture = true

    private var enabled = true

    init {
        mAudioManager = context.getSystemService(AUDIO_SERVICE) as AudioManager
        window = Timeline.Window()

        initVol()
    }

    private fun initVol() {

        /* Services and miscellaneous */
        mAudioManager = context.applicationContext.getSystemService(AUDIO_SERVICE) as AudioManager
        mAudioMax = mAudioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return if (!enabled) {
            false
        } else dispatchCenterWrapperTouchEvent(event)
    }

    private fun dispatchCenterWrapperTouchEvent(event: MotionEvent): Boolean {

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val screen = DisplayMetrics()
        wm.defaultDisplay.getMetrics(screen)

        if (mSurfaceYDisplayRange == 0) {
            mSurfaceYDisplayRange = Math.min(screen.widthPixels, screen.heightPixels)
        }

        val x_changed: Float
        val y_changed: Float
        if (touchX != -1f && touchY != -1f) {
            y_changed = event.rawY - touchY
            x_changed = event.rawX - touchX
        } else {
            x_changed = 0f
            y_changed = 0f
        }

        //        Log.e("tag","x_c=" + x_changed + "screen_x =" + screen.xdpi +" screen_rawx" + event.getRawX());
        val coef = Math.abs(y_changed / x_changed)
        val xgesturesize = (event.rawX - touchX) / screen.xdpi * 2.54f // 2.54f

        val delta_y = Math.max(1f, (Math.abs(mInitTouchY - event.rawY) / screen.xdpi + 0.5f) * 2f)

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                mTouchAction = TOUCH_NONE
                touchX = event.rawX
                mVol = mAudioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
                mInitTouchY = event.rawY
                touchY = mInitTouchY
            }

            MotionEvent.ACTION_MOVE ->

                if (mTouchAction != TOUCH_SEEK && coef > 2) {
                    if (Math.abs(y_changed / mSurfaceYDisplayRange) < 0.05) {
                        return false
                    }

                    touchX = event.rawX
                    touchY = event.rawY

                    if (touchX.toInt() > 4 * screen.widthPixels / 7) {
                        doVolumeTouch(y_changed)
                    }
                    // Brightness (Up or Down - Left side)
                    if (touchX.toInt() < 3 * screen.widthPixels / 7) {
                        doBrightnessTouch(y_changed)
                    }
                } else {
                    doSeekTouch(Math.round(delta_y), xgesturesize, false)
                }

            MotionEvent.ACTION_UP -> {
                if (mTouchAction == TOUCH_SEEK) {
                    doSeekTouch(Math.round(delta_y), xgesturesize, true)
                }

                if (mTouchAction != TOUCH_NONE) {
                    hideCenterInfo()
                }

                touchX = -1f
                touchY = -1f
            }
            else -> {
            }
        }

        return mTouchAction != TOUCH_NONE
    }

    private fun doVolumeTouch(y_changed: Float) {
        if (mTouchAction != TOUCH_NONE && mTouchAction != TOUCH_VOLUME) {
            return
        }

        val oldVol = mVol.toInt()
        mTouchAction = TOUCH_VOLUME
        val delta = -(y_changed / mSurfaceYDisplayRange * mAudioMax)
        mVol += delta
        val vol = Math.min(Math.max(mVol, 0f), mAudioMax.toFloat()).toInt()
        if (delta != 0f) {
            setAudioVolume(vol, vol > oldVol)
        }
    }

    private fun setAudioVolume(vol: Int, isUp: Boolean) {
        var vol = vol
        mAudioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0)

        /* Since android 4.3, the safe volume warning dialog is displayed only with the FLAG_SHOW_UI flag.
         * We don't want to always show the default UI volume, so show it only when volume is not set. */
        val newVol = mAudioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)
        if (vol != newVol) {
            mAudioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, vol, AudioManager.FLAG_SHOW_UI)
        }

        mTouchAction = TOUCH_VOLUME
        vol = vol * 100 / mAudioMax
        val type: Int
        if (newVol == 0) {
            type = VOLUME_CHANGED_MUTE
        } else if (isUp) {
            type = VOLUME_CHANGED_INCREMENT
        } else {
            type = VOLUME_CHANGED_REDUCTION
        }

        onVolumeChanged(vol, type)
    }

    private fun onVolumeChanged(range: Int, @OnVideoGestureChangeListener.VolumeChangeType type: Int) {
        if (onVideoGestureChangeListener != null) {
            onVideoGestureChangeListener!!.onVolumeChanged(range, type)
        }
    }

    private fun doBrightnessTouch(y_changed: Float) {
        if (mTouchAction != TOUCH_NONE && mTouchAction != TOUCH_BRIGHTNESS) {
            return
        }

        mTouchAction = TOUCH_BRIGHTNESS
        if (mIsFirstBrightnessGesture) {
            initBrightnessTouch()
        }

        mTouchAction = TOUCH_BRIGHTNESS
        //
        // Set delta : 2f is arbitrary for now, it possibly will change in the future
        val delta = -y_changed / mSurfaceYDisplayRange
        changeBrightness(delta)
    }

    private fun changeBrightness(delta: Float) {
        // Estimate and adjust Brightness
        if (context !is Activity) {
            return
        }
        val activity = context

        val lp = activity.window.attributes
        var brightness = Math.min(Math.max(lp.screenBrightness + delta, 0.01f), 1f)

        lp.screenBrightness = brightness
        // Set Brightness
        activity.window.attributes = lp

        brightness = Math.round(brightness * 100).toFloat()

        onBrightnessChanged(brightness.toInt())
    }

    private fun onBrightnessChanged(brightnessPercent: Int) {
        if (onVideoGestureChangeListener != null) {
            onVideoGestureChangeListener!!.onBrightnessChanged(brightnessPercent)
        }
    }

    private fun doSeekTouch(coef: Int, gesturesize: Float, seek: Boolean) {
        var coef = coef
        if (coef == 0) {
            coef = 1
        }

        // No seek action if coef > 0.5 and gesturesize < 1cm

        if (Math.abs(gesturesize) < 1 || !canSeek()) {
            return
        }

        if (mTouchAction != TOUCH_NONE && mTouchAction != TOUCH_SEEK) {
            return
        }

        mTouchAction = TOUCH_SEEK
        val player = playerAccessor.attachPlayer()
        val length = player?.getDuration()
        val time = player?.getCurrentPosition()

        // Size of the jump, 10 minutes max (600000), with a bi-cubic progression, for a 8cm gesture
        var jump = (Math.signum(gesturesize) * (600000 * Math.pow((gesturesize / 8).toDouble(), 4.0) + 3000) / coef).toInt()

        // Adjust the jump
        if (jump > 0 && time + jump > length) {
            jump = (length - time).toInt()
        }

        if (jump < 0 && time + jump < 0) {
            jump = (-time).toInt()
        }

        // Jump !
        //        if (seek && length > 0) {
        //            jump(time + jump);
        //        }

        if (length > 0) {
            // Show the jump's size
            seekAndShowJump(seek, time + jump, jump > 0)
        }
    }

    private fun seekAndShowJump(seek: Boolean, jumpSize: Long, isFastForward: Boolean) {
        if (onVideoGestureChangeListener != null) {
            onVideoGestureChangeListener!!.onShowSeekSize(jumpSize, isFastForward)
        }
    }

    private fun hideCenterInfo() {
        if (onVideoGestureChangeListener != null) {
            onVideoGestureChangeListener!!.onNoGesture()
        }
    }

    private fun initBrightnessTouch() {
        if (context !is Activity) {
            return
        }
        val activity = context

        val lp = activity.window.attributes
        var brightnesstemp = if (lp.screenBrightness != -1f) lp.screenBrightness else 0.6f
        // Initialize the layoutParams screen brightness
        try {
            if (Settings.System.getInt(activity.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                if (!Permissions.canWriteSettings(activity)) {
                    return
                }
                Settings.System.putInt(
                    activity.contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
                )
                //                restoreAutoBrightness = android.provider.Settings.System.getInt(activity.getContentResolver(),
                //                        android.provider.Settings.System.SCREEN_BRIGHTNESS) / 255.0f;
            } else if (brightnesstemp == 0.6f) {
                brightnesstemp = android.provider.Settings.System.getInt(
                    activity.contentResolver,
                    android.provider.Settings.System.SCREEN_BRIGHTNESS
                ) / 255.0f
            }
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }

        lp.screenBrightness = brightnesstemp
        activity.window.attributes = lp
        mIsFirstBrightnessGesture = false
    }

    private fun canSeek(): Boolean {

        val player = playerAccessor.attachPlayer()

        val timeline = if (player != null) player!!.getCurrentTimeline() else null
        val haveNonEmptyTimeline = timeline != null && !timeline!!.isEmpty()
        var isSeekable = false
        if (haveNonEmptyTimeline) {
            val windowIndex = player!!.getCurrentWindowIndex()
            timeline!!.getWindow(windowIndex, window)
            isSeekable = window.isSeekable
        }

        return isSeekable
    }

    fun enable() {
        enabled = true
    }

    fun disable() {
        enabled = false
    }

    companion object {

        // Touch Events
        private val TOUCH_NONE = 0
        private val TOUCH_VOLUME = 1
        private val TOUCH_BRIGHTNESS = 2
        private val TOUCH_SEEK = 3
    }
}
