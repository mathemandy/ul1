package ng.mathemandy.model.mapper

import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.model.LessonAndSubject
import ng.mathemandy.model.LessonAndSubjectModel
import ng.mathemandy.model.LessonModel
import ng.mathemandy.model.base.ModelMapper
import javax.inject.Inject

class LessonAndSubjectModelMapper @Inject constructor(
    private val subjectModelMapper: SubjectModelMapper,
    private val lessonModelMapper: LessonModelMapper) :
    ModelMapper<LessonAndSubjectModel, LessonAndSubject> {

    override fun mapToModel(domain: LessonAndSubject): LessonAndSubjectModel {
        return LessonAndSubjectModel(
            subjectModelMapper.mapToModel(domain.subject),
            lessonModelMapper.mapToModel(domain.lesson ?: Lesson())
        )
    }

    override fun mapToDomain(model: LessonAndSubjectModel): LessonAndSubject {
        return LessonAndSubject(
            subjectModelMapper.mapToDomain(model.subject),
            lessonModelMapper.mapToDomain(model.lesson ?: LessonModel())
        )
    }
}