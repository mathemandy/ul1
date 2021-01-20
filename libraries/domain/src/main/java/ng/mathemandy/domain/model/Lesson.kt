package ng.mathemandy.domain.model

data class Lesson(
    val id: Int = 0,
    val name: String = "",
    val icon: String = "",
    val media_url: String = "",
    val subject_id: Int = 0,
    val chapter_id: Int = 0,
)
