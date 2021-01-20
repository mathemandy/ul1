package ng.mathemandy.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import ng.mathemandy.data.contract.local.LessonAndSubjectLocal
import ng.mathemandy.data.mapper.LessonAndSubjectEntityMapper
import ng.mathemandy.data.mapper.LessonEntityMapper
import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.model.LessonAndSubject
import ng.mathemandy.domain.repository.RecentlyWatchedLessonRepository
import javax.inject.Inject

class RecentlyWatchedLessonRepositoryImpl @Inject constructor(
    private val lessonAndSubjectLocal: LessonAndSubjectLocal,
    private val lessonEntityMapper: LessonEntityMapper,
    private val lessonAndSubjectEntityMapper: LessonAndSubjectEntityMapper
) : RecentlyWatchedLessonRepository {

    override fun fetchRecentlyWatchedLessons(limit: Int): Flow<List<LessonAndSubject>> {
        return lessonAndSubjectLocal.getRecentlyWatchedLessons(limit).flatMapConcat {
            flow {
                emit(lessonAndSubjectEntityMapper.mapFromEntityList(it))
            }
        }
    }

    override fun saveRecentlyWatchedLesson(lesson: Lesson): Flow<Unit> {
        return flow {
            emit(
                lessonAndSubjectLocal.saveRecentlyWatched(lessonEntityMapper.mapToEntity(lesson))
            )
        }
    }
}
