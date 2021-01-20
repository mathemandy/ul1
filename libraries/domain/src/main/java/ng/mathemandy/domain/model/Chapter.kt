package ng.mathemandy.domain.model

data class Chapter(
    val id: Int,
    val name: String,
    val lessons: List<Lesson>,
)
