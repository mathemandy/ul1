package ng.mathemandy.remote.model

data class LessonRemoteModel(
    val id: Int,
    val name: String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int,
)
