package ng.mathemandy.domain.usecase

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.domain.exception.NoParamsException
import ng.mathemandy.domain.executor.PostExecutionThread
import ng.mathemandy.domain.model.LessonAndSubject
import ng.mathemandy.domain.repository.RecentlyWatchedLessonRepository
import ng.mathemandy.domain.usecase.base.FlowUseCase
import javax.inject.Inject

class FetchRecentlyWatchedLessons  @Inject constructor(
    private val recentlyWatchedLessonsRepository: RecentlyWatchedLessonRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Int, List<LessonAndSubject>> (postExecutionThread) {

    override fun execute(params: Int?): Flow<List<LessonAndSubject>> {
        return params?.let { recentlyWatchedLessonsRepository.fetchRecentlyWatchedLessons(it) }  ?: throw  NoParamsException("limit is null")
    }
}