package ng.mathemandy.data.fake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ng.mathemandy.data.contract.local.SubjectsLocal
import ng.mathemandy.data.model.SubjectEntity

class FakeSubjectLocalImpl : SubjectsLocal {

    private var cache = mutableListOf<SubjectEntity>()

    override suspend fun saveSubject(subject: SubjectEntity) {
        cache.add(subject)
    }

    override suspend fun saveSubjects(subject: List<SubjectEntity>) {
        cache.addAll(subject)
    }

    override fun getSubjects(): Flow<List<SubjectEntity>> {
        return flow {
            emit(cache.distinct())
        }
    }

    override suspend fun clearSubjects() {
        cache = mutableListOf()
    }
}
