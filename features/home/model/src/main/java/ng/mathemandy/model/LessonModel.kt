package ng.mathemandy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LessonModel(
    val id: Int,
    val name : String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int,
):Parcelable