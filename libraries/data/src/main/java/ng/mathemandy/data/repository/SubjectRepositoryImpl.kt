package ng.mathemandy.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ng.mathemandy.data.contract.SubjectsRemote
import ng.mathemandy.data.mapper.SubjectEntityMapper
import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.domain.repository.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectRemote: SubjectsRemote,
    private val subjectMapper: SubjectEntityMapper
) : SubjectRepository {

    override fun fetchSubjects(): Flow<List<Subject>> {
        return flow {
            val subjects: List<SubjectEntity> = subjectRemote.fetchSubjects()
            emit(subjectMapper.mapFromEntityList(subjects))
        }
    }
}