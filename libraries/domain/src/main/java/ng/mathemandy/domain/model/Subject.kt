package ng.mathemandy.domain.model

data class Subject(
    val id: Int,
    val name : String,
    val icon: String,
    val chapters: List<Chapter>,
)
