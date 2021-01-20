package ng.mathemandy.data.contract.remote

import ng.mathemandy.data.model.SubjectEntity

interface SubjectsRemote {
    suspend fun fetchSubjects(): List<SubjectEntity>
}
