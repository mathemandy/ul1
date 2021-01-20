package ng.mathemandy.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ng.mathemandy.local.entity.LessonAndSubject
import ng.mathemandy.local.entity.LessonLocalModel
import ng.mathemandy.local.entity.SubjectLocalModel


@Dao
interface LessonAndSubjectDao {

    @Transaction
    @Query("SELECT * FROM Lessons ORDER BY lastUpdated DESC")
    fun getLessonAndOwner(): Flow<List<LessonAndSubject>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lessonLocalModel: LessonLocalModel)


}