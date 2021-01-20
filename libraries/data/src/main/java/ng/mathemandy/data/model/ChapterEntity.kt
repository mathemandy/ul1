package ng.mathemandy.data.model

data class ChapterEntity(
    val id: Int,
    val name: String,
    val lessons: List<LessonEntity>,
)
