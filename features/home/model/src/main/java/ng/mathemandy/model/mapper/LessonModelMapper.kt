package ng.mathemandy.model.mapper

import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.model.LessonModel
import ng.mathemandy.model.base.ModelMapper
import javax.inject.Inject

class LessonModelMapper @Inject constructor() : ModelMapper<LessonModel, Lesson>{
    override fun mapToModel(domain: Lesson): LessonModel {
        return LessonModel(
            domain.id,
            domain.name,
            domain.icon,
            domain.media_url,
            domain.subject_id,
            domain.chapter_id
        )
    }

    override fun mapToDomain(model: LessonModel): Lesson {
        return Lesson(
            model.id,
            model.name,
            model.icon,
            model.media_url,
            model.subject_id,
            model.chapter_id
        )
    }
}