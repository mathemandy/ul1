package ng.mathemandy.remote.model

data class SubjectRemoteModel(
    val id: Int,
    val name: String,
    val icon: String,
    val chapters: List<ChapterRemoteModel>,
)
