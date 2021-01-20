package ng.mathemandy.data.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ng.mathemandy.data.contract.local.LessonAndSubjectLocal
import ng.mathemandy.data.model.LessonAndSubjectEntity
import ng.mathemandy.data.model.LessonEntity

class FakeLocalAndSubjectLocal : LessonAndSubjectLocal {

    private var subJectcache = listOf(DummyData.subjectEntity, DummyData.subjectEntity2)
    private var lessonCache = mutableListOf<LessonEntity>()


    override suspend fun saveRecentlyWatched(lesson: LessonEntity) {
        lessonCache.add(lesson)

    }

    override fun getRecentlyWatchedLessons(limit: Int): Flow<List<LessonAndSubjectEntity>> {
        return flow {
            val lessonAndSubject = mutableListOf<LessonAndSubjectEntity>()
            lessonCache.forEach { lessonEntity ->
                lessonAndSubject.add(LessonAndSubjectEntity(subJectcache.filter { it.id == lessonEntity.subject_id }
                    .first(), lessonEntity))
            }
            emit(lessonAndSubject)
        }
    }

    override suspend fun clearRecentlyWatchedLesson() {
        lessonCache = mutableListOf()
        subJectcache = mutableListOf()

    }
}