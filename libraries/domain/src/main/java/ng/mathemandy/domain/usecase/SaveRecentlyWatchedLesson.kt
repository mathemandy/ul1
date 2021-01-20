package ng.mathemandy.domain.usecase

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.domain.exception.NoParamsException
import ng.mathemandy.domain.executor.PostExecutionThread
import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.repository.RecentlyWatchedLessonRepository
import ng.mathemandy.domain.usecase.base.FlowUseCase
import javax.inject.Inject

class SaveRecentlyWatchedLesson @Inject constructor(
    private val recentlyWatchedLessonsRepository: RecentlyWatchedLessonRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Lesson, Unit>(postExecutionThread) {

    override fun execute(params: Lesson?): Flow<Unit> {
        return params?.let { recentlyWatchedLessonsRepository.saveRecentlyWatchedLesson(it) } ?: throw NoParamsException()
    }
}
