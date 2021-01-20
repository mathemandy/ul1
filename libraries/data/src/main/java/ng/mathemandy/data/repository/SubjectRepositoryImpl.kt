package ng.mathemandy.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import ng.mathemandy.data.contract.local.SubjectsLocal
import ng.mathemandy.data.contract.remote.SubjectsRemote
import ng.mathemandy.data.ext.networkBoundResource
import ng.mathemandy.data.mapper.SubjectEntityMapper
import ng.mathemandy.domain.model.LessonAndSubject
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.domain.repository.SubjectRepository
import ng.mathemandy.domain.resource.RepositoryResource
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectRemote: SubjectsRemote,
    private val subjectsLocal: SubjectsLocal,
    private val subjectMapper: SubjectEntityMapper
) : SubjectRepository {

    override fun fetchSubjects(): Flow<RepositoryResource<List<Subject>>> {
        return networkBoundResource(
            query = {
                subjectsLocal.getSubjects().flatMapConcat {
                        flow {
                            emit(subjectMapper.mapFromEntityList(it))
                        }
                }
            },
            saveFetchResult = { items ->
                subjectsLocal.saveSubjects(items)
            },
            fetch = {
                subjectRemote.fetchSubjects()
            },
            clearData = {
                subjectsLocal.clearSubjects()
            },
            shouldClear = { requestType, resultType ->
//                requestType != resultType
                    false

            }
        )

    }

}