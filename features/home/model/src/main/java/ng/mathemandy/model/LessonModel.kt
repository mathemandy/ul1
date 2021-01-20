package ng.mathemandy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LessonModel(
    val id: Int = 0,
    val name: String = "",
    val icon: String = "",
    val media_url: String = "",
    val subject_id: Int = 0,
    val chapter_id: Int = 0,
) : Parcelable
