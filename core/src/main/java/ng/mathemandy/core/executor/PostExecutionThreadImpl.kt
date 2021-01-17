package ng.mathemandy.core.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ng.mathemandy.domain.executor.PostExecutionThread
import javax.inject.Inject

class PostExecutionThreadImpl @Inject constructor() : PostExecutionThread {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}
