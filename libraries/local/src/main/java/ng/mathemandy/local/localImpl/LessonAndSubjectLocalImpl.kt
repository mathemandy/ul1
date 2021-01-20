package ng.mathemandy.local.localImpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import ng.mathemandy.data.contract.local.LessonAndSubjectLocal
import ng.mathemandy.data.model.LessonAndSubjectEntity
import ng.mathemandy.data.model.LessonEntity
import ng.mathemandy.local.entity.LessonLocalModel
import ng.mathemandy.local.mapper.LessonAndSubjectLocalMapper
import ng.mathemandy.local.mapper.LessonLocalMapper
import ng.mathemandy.local.room.LessonAndSubjectDao
import javax.inject.Inject

class LessonAndSubjectLocalImpl @Inject constructor(
    private val lessonAndSubjectDao: LessonAndSubjectDao,
    private val lessonLocalMapper: LessonLocalMapper,
    private val lessonAndSubjectLocalMapper: LessonAndSubjectLocalMapper
) : LessonAndSubjectLocal {
    override suspend fun saveRecentlyWatched(lesson: LessonEntity) {
        val lessonLocalModel: LessonLocalModel = lessonLocalMapper.mapToModel(lesson)
        lessonLocalModel.lastUpdated = System.currentTimeMillis()
        lessonAndSubjectDao.insertLesson(lessonLocalModel)
    }

    override fun getRecentlyWatchedLessons(limit: Int): Flow<List<LessonAndSubjectEntity>> {
        if (limit < 0) {
            return lessonAndSubjectDao.getLessonAndOwner().flatMapConcat {
                flow {
                    emit(lessonAndSubjectLocalMapper.mapToEntityList(it))
                }
            }
        } else {
            return lessonAndSubjectDao.getLessonAndOwner(limit).flatMapConcat {
                flow {
                    emit(lessonAndSubjectLocalMapper.mapToEntityList(it))
                }
            }
        }
    }

    override suspend fun clearRecentlyWatchedLesson() {
        TODO("Not yet implemented")
    }
}
