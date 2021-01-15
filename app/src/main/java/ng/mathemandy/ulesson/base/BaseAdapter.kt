package ng.mathemandy.ulesson.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseObject(
    val status: String?,
    val message : String?,
    val subjects : List<Subjects>? = emptyList()
) : Parcelable


@Parcelize
data class Subjects(
    val id: Int,
    val name : String,
    val icon: String,
    val chapters: List<Chapters>,
) : Parcelable


@Parcelize
data class Chapters(
    val id: Int,
    val name : String,
    val lessons: List<Lessons>,
) : Parcelable

@Parcelize
data class Lessons(
    val id: Int,
    val name : String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int,
) : Parcelable