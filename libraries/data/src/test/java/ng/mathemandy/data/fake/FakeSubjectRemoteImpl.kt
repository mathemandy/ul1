package ng.mathemandy.data.fake

import ng.mathemandy.data.contract.remote.SubjectsRemote
import ng.mathemandy.data.model.SubjectEntity
import java.net.UnknownHostException

internal class FakeSubjectRemoteImpl : SubjectsRemote {

    override suspend fun fetchSubjects(): List<SubjectEntity> {
        return listOf(DummyData.subjectEntity)
    }
}

internal class FakeErrorSubjectRemote : SubjectsRemote {

    override suspend fun fetchSubjects(): List<SubjectEntity> {
        throw UnknownHostException(ERROR)
    }

    companion object {
        const val ERROR: String = "jackiechanbruteforce.ulesson.com"
    }
}
