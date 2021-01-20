package ng.mathemandy.local.localImpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import ng.mathemandy.data.contract.local.SubjectsLocal
import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.local.entity.SubjectLocalModel
import ng.mathemandy.local.mapper.SubjectLocalMapper
import ng.mathemandy.local.room.SubjectDao
import javax.inject.Inject

class SubjectLocalImpl @Inject constructor(
    private val subjectDao: SubjectDao,
    private val subjectLocalMapper: SubjectLocalMapper
) : SubjectsLocal {

    override suspend fun saveSubject(subject: SubjectEntity) {
        val subjectModel: SubjectLocalModel = subjectLocalMapper.mapToModel(subject)
        subjectDao.insertSubject(subjectModel)
    }

    override fun getSubjects(): Flow<List<SubjectEntity>> {
        val subjectModels = subjectDao.getSubjects()
        return subjectModels.flatMapConcat {
            flow {
                emit(subjectLocalMapper.mapToEntityList(it))
            }
        }
    }

    override suspend fun saveSubjects(subject: List<SubjectEntity>) {
        val subjectModel: List<SubjectLocalModel> = subjectLocalMapper.mapToModelList(subject)
        subjectDao.insertSubjects(subjectModel)
    }

    override suspend fun clearSubjects() {
        subjectDao.clearData()
    }
}
