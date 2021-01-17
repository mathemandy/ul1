package ng.mathemandy.data.mapper

import ng.mathemandy.data.mapper.base.EntityMapper
import ng.mathemandy.data.model.ChapterEntity
import ng.mathemandy.domain.model.Chapter
import javax.inject.Inject

class ChapterEntityMapper @Inject constructor(private val lessonEntityMapper: LessonEntityMapper) :
    EntityMapper<ChapterEntity, Chapter> {

    override fun mapFromEntity(entity: ChapterEntity): Chapter {
        return Chapter(
            entity.id,
            entity.name,
            lessonEntityMapper.mapFromEntityList(entity.lessons)
        )
    }

    override fun mapToEntity(domain: Chapter): ChapterEntity {
        return ChapterEntity(
            domain.id,
            domain.name,
            lessonEntityMapper.mapFromDomainList(domain.lessons)
        )
    }
}
