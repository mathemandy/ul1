package ng.groove.exovideoview.orientation

import android.content.Context
import android.provider.Settings
import android.util.Log
import android.view.OrientationEventListener
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_LANDSCAPE
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_PORTRAIT
import ng.groove.exovideoview.orientation.OnOrientationChangedListener.Companion.SENSOR_UNKNOWN


/**
 * Created by mo on 18-2-5.
 * 剑气纵横三万里 一剑光寒十九洲
 */

class SensorOrientation(private val context: Context, onOrientationChangedListener: OnOrientationChangedListener?) {

    private var oldScreenOrientation = SENSOR_UNKNOWN
    private val screenOrientationEventListener: OrientationEventListener

    private val isScreenOpenRotate: Boolean
        get() {

            var gravity = 0
            try {

                gravity = Settings.System.getInt(context.contentResolver, Settings.System.ACCELEROMETER_ROTATION)

            } catch (e: Settings.SettingNotFoundException) {
                Log.e(javaClass.simpleName, e.message + "")
            }

            return 1 == gravity

        }

    init {
        screenOrientationEventListener = object : OrientationEventListener(context) {
            override fun onOrientationChanged(orientation: Int) {
                var orientation = orientation

                if (onOrientationChangedListener == null || !isScreenOpenRotate) {
                    return
                }


                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    onOrientationChangedListener.onChanged(SENSOR_UNKNOWN)
                    return   //手机平放时，检测不到有效的角度
                }
                //只检测是否有四个角度的改变
                orientation = if (orientation > 350 || orientation < 10) { //0度
                    0
                } else if (orientation in 81..99) { //90度
                    90
                } else if (orientation in 171..189) { //180度
                    180
                } else if (orientation in 261..279) { //270度
                    270
                } else {
                    return
                }

                if (oldScreenOrientation == orientation) {
                    onOrientationChangedListener.onChanged(SENSOR_UNKNOWN)
                    return
                }


                oldScreenOrientation = orientation

                if (orientation == 0 || orientation == 180) {
                    onOrientationChangedListener.onChanged(SENSOR_PORTRAIT)
                } else {
                    onOrientationChangedListener.onChanged(SENSOR_LANDSCAPE)
                }
            }
        }
    }

    fun enable() {
        screenOrientationEventListener.enable()
    }

    fun disable() {
        screenOrientationEventListener.disable()
    }
}
