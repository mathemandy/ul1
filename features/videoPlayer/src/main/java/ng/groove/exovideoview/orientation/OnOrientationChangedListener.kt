package ng.groove.exovideoview.orientation

import androidx.annotation.IntDef

interface OnOrientationChangedListener {

    @IntDef(SENSOR_UNKNOWN, SENSOR_PORTRAIT, SENSOR_LANDSCAPE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class SensorOrientationType


    fun onChanged(@SensorOrientationType orientation: Int)

    companion object {
        const val SENSOR_UNKNOWN = -1
        const val SENSOR_PORTRAIT = SENSOR_UNKNOWN + 1
        const val SENSOR_LANDSCAPE = SENSOR_PORTRAIT + 1
    }
}