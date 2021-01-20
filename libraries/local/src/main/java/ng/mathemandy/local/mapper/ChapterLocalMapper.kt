package ng.mathemandy.local.mapper

import ng.mathemandy.data.model.ChapterEntity
import ng.mathemandy.local.entity.ChapterLocalModel
import ng.mathemandy.local.mapper.base.LocalModelMapper
import javax.inject.Inject

class ChapterLocalMapper @Inject constructor(private val localMapper: LessonLocalMapper) : LocalModelMapper<ChapterLocalModel, ChapterEntity> {
    override fun mapToModel(entity: ChapterEntity): ChapterLocalModel {
        return ChapterLocalModel(
            entity.id,
            entity.name,
            localMapper.mapToModelList(entity.lessons)
        )
    }

    override fun mapToEntity(model: ChapterLocalModel): ChapterEntity {
        return ChapterEntity(
            model.id,
            model.name,
            localMapper.mapToEntityList(model.lessons)
        )
    }
}
