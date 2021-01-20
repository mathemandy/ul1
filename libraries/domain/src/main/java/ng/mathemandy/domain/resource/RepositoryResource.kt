package ng.mathemandy.domain.resource

data class RepositoryResource<out T>(val status: Status, val data: T?, val cause: Throwable?) {
    companion object {
        fun <T> success(data: T?): RepositoryResource<T> {
            return RepositoryResource(Status.SUCCESS, data, null)
        }

        fun <T> error(cause: Throwable, data: T?): RepositoryResource<T> {
            return RepositoryResource(Status.ERROR, data, cause)
        }

        fun <T> loading(data: T?): RepositoryResource<T> {
            return RepositoryResource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
