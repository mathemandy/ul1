package ng.mathemandy.local.mapper

import ng.mathemandy.data.model.LessonAndSubjectEntity
import ng.mathemandy.local.entity.LessonAndSubject
import ng.mathemandy.local.entity.LessonLocalModel
import ng.mathemandy.local.mapper.base.LocalModelMapper
import javax.inject.Inject

class LessonAndSubjectLocalMapper  @Inject constructor(
    private  val lessonLocalMapper: LessonLocalMapper,
    private val subjectLocalMapper: SubjectLocalMapper
) : LocalModelMapper<LessonAndSubject, LessonAndSubjectEntity>
{
    override fun mapToModel(entity: LessonAndSubjectEntity): LessonAndSubject {
        return  LessonAndSubject(
            subjectLocalMapper.mapToModel(entity.subject),
            lessonLocalMapper.mapToModel(entity.lesson)
        )
    }

    override fun mapToEntity(model: LessonAndSubject): LessonAndSubjectEntity {
        return  LessonAndSubjectEntity(
            subjectLocalMapper.mapToEntity(model.subject),
            lessonLocalMapper.mapToEntity(model.lesson ?: LessonLocalModel() )
        )
    }
}