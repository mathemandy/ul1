package ng.mathemandy.local.mapper

import ng.mathemandy.data.model.LessonEntity
import ng.mathemandy.local.entity.LessonLocalModel
import ng.mathemandy.local.mapper.base.LocalModelMapper
import javax.inject.Inject

class LessonLocalMapper @Inject constructor() : LocalModelMapper<LessonLocalModel, LessonEntity> {

    override fun mapToModel(entity: LessonEntity): LessonLocalModel {
        return LessonLocalModel(
            entity.id,
            entity.name,
            entity.icon,
            entity.media_url,
            entity.subject_id,
            entity.chapter_id
        )
    }

    override fun mapToEntity(model: LessonLocalModel): LessonEntity {
        return LessonEntity(
            model.id,
            model.name,
            model.icon,
            model.media_url,
            model.subject_id,
            model.chapter_id
        )
    }
}
