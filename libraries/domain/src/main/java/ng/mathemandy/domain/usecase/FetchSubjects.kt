package ng.mathemandy.domain.usecase

import kotlinx.coroutines.flow.Flow
import ng.mathemandy.domain.executor.PostExecutionThread
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.domain.repository.SubjectRepository
import ng.mathemandy.domain.resource.RepositoryResource
import ng.mathemandy.domain.usecase.base.FlowUseCase
import javax.inject.Inject

class FetchSubjects  @Inject constructor(
    private val subjectRepository: SubjectRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, RepositoryResource<List<Subject>>>(postExecutionThread) {

    override fun execute(params: Unit?): Flow<RepositoryResource<List<Subject>>> {
        return subjectRepository.fetchSubjects()
    }
}