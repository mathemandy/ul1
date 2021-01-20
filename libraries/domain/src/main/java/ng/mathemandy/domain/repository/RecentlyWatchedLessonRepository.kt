package ng.mathemandy.domain.repository

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.model.LessonAndSubject

interface RecentlyWatchedLessonRepository {
    fun  fetchRecentlyWatchedLessons(limit: Int): Flow<List<LessonAndSubject>>
    fun saveRecentlyWatchedLesson(lesson : Lesson ) : Flow<Unit>
}