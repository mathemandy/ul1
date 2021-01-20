package ng.mathemandy.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ng.mathemandy.local.entity.LessonLocalModel

@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lessonLocalModel: LessonLocalModel)

    @Query("SELECT * FROM Subjects WHERE id = :lessonId")
    suspend fun fetchLessonById(lessonId: String): LessonLocalModel?

    @Query("DELETE FROM Lessons")
    suspend fun clearData()
}