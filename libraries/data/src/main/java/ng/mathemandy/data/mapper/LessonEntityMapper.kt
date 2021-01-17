package ng.mathemandy.data.mapper

import ng.mathemandy.data.mapper.base.EntityMapper
import ng.mathemandy.data.model.LessonEntity
import ng.mathemandy.domain.model.Lesson
import javax.inject.Inject

class LessonEntityMapper @Inject constructor() :
    EntityMapper<LessonEntity, Lesson> {

    override fun mapFromEntity(entity: LessonEntity): Lesson {
        return Lesson(
            entity.id,
            entity.name,
            entity.icon,
            entity.media_url,
            entity.subject_id,
            entity.chapter_id
        )
    }

    override fun mapToEntity(domain: Lesson): LessonEntity {
        return LessonEntity(
            domain.id,
            domain.name,
            domain.icon,
            domain.media_url,
            domain.subject_id,
            domain.chapter_id
        )
    }
}
