package ng.mathemandy.domain.repository

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.domain.model.Subject

interface SubjectRepository {
    fun fetchSubjects(): Flow<List<Subject>>
}