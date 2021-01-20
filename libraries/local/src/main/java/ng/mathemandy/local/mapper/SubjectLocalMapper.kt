package ng.mathemandy.local.mapper

import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.local.entity.SubjectLocalModel
import ng.mathemandy.local.mapper.base.LocalModelMapper
import javax.inject.Inject

class SubjectLocalMapper @Inject constructor(private val chapterLocalMapper: ChapterLocalMapper) :
    LocalModelMapper<SubjectLocalModel, SubjectEntity> {


    override fun mapToModel(entity: SubjectEntity): SubjectLocalModel {
        return SubjectLocalModel(
            entity.id,
            entity.name,
            entity.icon,
            chapterLocalMapper.mapToModelList(entity.chapters)
        )
    }

    override fun mapToEntity(model: SubjectLocalModel): SubjectEntity {
        return SubjectEntity(
            model.id,
            model.name,
            model.icon,
            chapterLocalMapper.mapToEntityList(model.chapters ?: emptyList())
        )
    }
}