package ng.mathemandy.remote.model

data class BaseRemoteModel(
    val data: RemoteModel?
)

data class RemoteModel(
    val status: String?,
    val message: String?,
    val subjects: List<SubjectRemoteModel> = emptyList()
)
