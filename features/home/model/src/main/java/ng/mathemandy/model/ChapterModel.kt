package ng.mathemandy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChapterModel(
    val id: Int,
    val name: String,
    val lessons: List<LessonModel>,
) : Parcelable
