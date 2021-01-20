package ng.mathemandy.local.entity


data class ChapterLocalModel(
    val id: Int,
    val name : String,
    val lessons: List<LessonLocalModel>,
)