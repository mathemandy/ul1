package ng.groove.exovideoview.utils

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object Permissions {

    val PERMISSION_STORAGE_TAG = 255
    val PERMISSION_SETTINGS_TAG = 254

    val PERMISSION_SYSTEM_RINGTONE = 42
    val PERMISSION_SYSTEM_BRIGHTNESS = 43
    val PERMISSION_SYSTEM_DRAW_OVRLAYS = 44

    /*
     * Marshmallow permission system management
     */

    @TargetApi(Build.VERSION_CODES.M)
    fun canDrawOverlays(context: Context): Boolean {
        return !AndroidUtil.isMarshMallowOrLater() || Settings.canDrawOverlays(context)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun canWriteSettings(context: Context): Boolean {
        return !AndroidUtil.isMarshMallowOrLater() || Settings.System.canWrite(context)
    }

    fun canReadStorage(context: Context): Boolean {
        return !AndroidUtil.isMarshMallowOrLater() || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestStoragePermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISSION_STORAGE_TAG
        )
    }
}
