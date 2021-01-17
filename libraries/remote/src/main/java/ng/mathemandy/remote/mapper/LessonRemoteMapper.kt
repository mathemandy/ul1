package ng.mathemandy.remote.mapper

import ng.mathemandy.data.model.LessonEntity
import ng.mathemandy.remote.mapper.base.RemoteModelMapper
import ng.mathemandy.remote.model.LessonRemoteModel
import javax.inject.Inject

class LessonRemoteMapper @Inject constructor() :
    RemoteModelMapper<LessonRemoteModel, LessonEntity> {

    override fun mapFromModel(model: LessonRemoteModel): LessonEntity {
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
