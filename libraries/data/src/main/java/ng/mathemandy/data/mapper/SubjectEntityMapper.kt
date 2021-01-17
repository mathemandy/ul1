package ng.mathemandy.data.mapper

import ng.mathemandy.data.mapper.base.EntityMapper
import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.domain.model.Subject
import javax.inject.Inject

class SubjectEntityMapper @Inject constructor(private val chapterEntityMapper: ChapterEntityMapper) :
    EntityMapper<SubjectEntity, Subject> {

    override fun mapFromEntity(entity: SubjectEntity): Subject {
        return Subject(
            entity.id,
            entity.name,
            entity.icon,
            chapterEntityMapper.mapFromEntityList(entity.chapters)

        )
    }

    override fun mapToEntity(domain: Subject): SubjectEntity {
        return SubjectEntity(
            domain.id,
            domain.name,
            domain.icon,
            chapterEntityMapper.mapFromDomainList(domain.chapters)

        )
    }
}
