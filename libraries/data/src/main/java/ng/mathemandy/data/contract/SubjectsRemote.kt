package ng.mathemandy.data.contract

import ng.mathemandy.data.model.SubjectEntity

interface SubjectsRemote {
    suspend fun fetchSubjects(): List<SubjectEntity>
}
