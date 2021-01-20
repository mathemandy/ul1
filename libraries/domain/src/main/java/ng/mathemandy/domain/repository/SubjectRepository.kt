package ng.mathemandy.domain.repository

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.domain.resource.RepositoryResource

interface SubjectRepository {
    fun fetchSubjects(): Flow<RepositoryResource<List<Subject>>>
}