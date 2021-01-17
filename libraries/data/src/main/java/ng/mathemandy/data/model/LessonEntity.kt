package ng.mathemandy.data.model


data class LessonEntity(
    val id: Int,
    val name : String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int,
)