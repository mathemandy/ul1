package ng.mathemandy.data.contract.local

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.data.model.LessonAndSubjectEntity
import ng.mathemandy.data.model.LessonEntity

interface LessonAndSubjectLocal {
    suspend fun saveRecentlyWatched(lesson: LessonEntity)
    fun getRecentlyWatchedLessons(limit: Int = -1): Flow<List<LessonAndSubjectEntity>>
    suspend fun clearRecentlyWatchedLesson()
}
