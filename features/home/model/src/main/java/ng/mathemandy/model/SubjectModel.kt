package ng.mathemandy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SubjectModel(
    val id: Int,
    val name : String,
    val icon: String,
    val chapters: List<ChapterModel>,
): Parcelable
