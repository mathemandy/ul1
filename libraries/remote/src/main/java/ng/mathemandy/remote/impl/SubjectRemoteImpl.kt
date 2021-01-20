package ng.mathemandy.remote.impl

import ng.mathemandy.data.contract.remote.SubjectsRemote
import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.remote.ApiService
import ng.mathemandy.remote.mapper.SubjectRemoteMapper
import javax.inject.Inject

class SubjectRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val subjectRemoteMapper: SubjectRemoteMapper
) : SubjectsRemote {

    override suspend fun fetchSubjects(): List<SubjectEntity> {
        val subjects = apiService.fetchHomeData().data?.subjects
        return subjectRemoteMapper.mapModelList(subjects ?: emptyList())
    }
}
