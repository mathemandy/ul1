package ng.mathemandy.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ng.mathemandy.local.entity.SubjectLocalModel

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subjectLocalModel: SubjectLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjects(subjectLocalModel: List<SubjectLocalModel>)

    @Query("SELECT * FROM Subjects ")
    fun getSubjects(): Flow<List<SubjectLocalModel>>

    @Query("DELETE FROM Subjects")
    suspend fun clearData()
}
