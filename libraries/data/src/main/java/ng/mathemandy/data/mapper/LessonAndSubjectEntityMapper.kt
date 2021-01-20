package ng.mathemandy.data.mapper

import ng.mathemandy.data.mapper.base.EntityMapper
import ng.mathemandy.data.model.LessonAndSubjectEntity
import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.model.LessonAndSubject
import javax.inject.Inject

class LessonAndSubjectEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper,
    private val subjectEntityMapper: SubjectEntityMapper
) : EntityMapper<LessonAndSubjectEntity, LessonAndSubject> {

    override fun mapFromEntity(entity: LessonAndSubjectEntity): LessonAndSubject {
        return LessonAndSubject(
            subjectEntityMapper.mapFromEntity(entity.subject),
            lessonEntityMapper.mapFromEntity(entity.lesson)
        )
    }

    override fun mapToEntity(domain: LessonAndSubject): LessonAndSubjectEntity {
        return LessonAndSubjectEntity(
            subjectEntityMapper.mapToEntity(domain.subject),
            lessonEntityMapper.mapToEntity(domain.lesson ?: Lesson())
        )
    }
}