package ng.mathemandy.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ng.mathemandy.local.entity.LessonAndSubject
import ng.mathemandy.local.entity.LessonLocalModel

@Dao
interface LessonAndSubjectDao {

    @Transaction
    @Query("SELECT * FROM Lessons ORDER BY lastUpdated DESC")
    fun getLessonAndOwner(): Flow<List<LessonAndSubject>>

    @Transaction
    @Query("SELECT * FROM Lessons ORDER BY lastUpdated DESC LIMIT :limit")
    fun getLessonAndOwner(limit: Int): Flow<List<LessonAndSubject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lessonLocalModel: LessonLocalModel)
}
