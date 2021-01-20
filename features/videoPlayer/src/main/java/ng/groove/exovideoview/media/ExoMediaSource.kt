package ng.groove.exovideoview.media

import android.net.Uri

interface ExoMediaSource {

    interface Quality {
        var displayName: CharSequence

        var uri: Uri

        var quality: String
    }

    fun uri(): Uri

    fun name(): String

    fun qualities(): List<Quality>

    fun extension(): String
}
