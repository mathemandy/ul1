package ng.mathemandy.data.contract.local

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.data.model.SubjectEntity

interface SubjectsLocal {
    suspend fun saveSubject(subject: SubjectEntity)
    suspend fun saveSubjects(subject: List<SubjectEntity>)
    fun getSubjects(): Flow<List<SubjectEntity>>
    suspend fun clearSubjects()
}
