package ng.mathemandy.domain.usecase.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ng.mathemandy.domain.executor.PostExecutionThread

abstract class FlowUseCase<in Params, out T>(
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Function which builds Flow instance based on given arguments
     * @param params initial use case arguments
     */
    abstract fun execute(params: Params? = null): Flow<T>

    operator fun invoke(params: Params? = null): Flow<T> = execute(params)
        .flowOn(postExecutionThread.io)
}
