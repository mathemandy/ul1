package ng.groove.exovideoview.gesture

import androidx.annotation.IntDef

interface OnVideoGestureChangeListener {

    @IntDef(VOLUME_CHANGED_REDUCTION, VOLUME_CHANGED_MUTE, VOLUME_CHANGED_INCREMENT)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class VolumeChangeType

    fun onVolumeChanged(range: Int, @VolumeChangeType type: Int)

    fun onBrightnessChanged(brightnessPercent: Int)

    fun onNoGesture()

    fun onShowSeekSize(seekSize: Long, fastForward: Boolean)

    companion object {

        const val VOLUME_CHANGED_REDUCTION = -1
        const val VOLUME_CHANGED_MUTE = VOLUME_CHANGED_REDUCTION + 1
        const val VOLUME_CHANGED_INCREMENT = VOLUME_CHANGED_MUTE + 1
    }
}
