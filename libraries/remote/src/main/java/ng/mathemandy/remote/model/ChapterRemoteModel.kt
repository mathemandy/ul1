package ng.mathemandy.remote.model

data class ChapterRemoteModel(
    val id: Int,
    val name : String,
    val lessons: List<LessonRemoteModel>,
)