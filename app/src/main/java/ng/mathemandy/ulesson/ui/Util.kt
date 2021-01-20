package ng.mathemandy.ulesson.ui

import android.graphics.Color

fun getSubjectColor(subjectId: Int): Int {

    return when (subjectId) {
        5 -> {
            parseColor("#4DA47E")
        }
        3 -> {
            parseColor("#7B7FDA")
        }
        4 -> {
            Color.parseColor("#F9AD6D")
        }
        2 -> {
            parseColor("#EA7052")
        }
        85 -> {
            parseColor("#506AAC")
        }
        else -> {
            parseColor("Mathematics")
        }
    }
}

private fun parseColor(colorId: String): Int = Color.parseColor(colorId)
