package ng.mathemandy.data.model

data class SubjectEntity(
    val id: Int,
    val name: String,
    val icon: String,
    val chapters: List<ChapterEntity>,
)
