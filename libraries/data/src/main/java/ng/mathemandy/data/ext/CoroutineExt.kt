package ng.mathemandy.data.ext

import kotlinx.coroutines.flow.*
import ng.mathemandy.domain.resource.RepositoryResource

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline clearData: suspend () -> Unit,
    noinline onFetchFailed: suspend (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline shouldClear: (RequestType, ResultType) -> Boolean = { requestType: RequestType, resultType: ResultType -> false }
) = flow<RepositoryResource<ResultType>> {
    emit(RepositoryResource.loading(null))
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(RepositoryResource.loading(data))
        try {

            val fetchData = fetch()
            if (shouldClear(fetchData, data)) {
                clearData()
            }
            saveFetchResult(fetchData)
            query().map { RepositoryResource.success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { RepositoryResource.error(throwable, it) }
        }
    } else {
        query().map { RepositoryResource.success(it) }
    }

    emitAll(flow)
}

