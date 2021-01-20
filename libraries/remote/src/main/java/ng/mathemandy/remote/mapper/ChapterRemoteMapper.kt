package ng.mathemandy.remote.mapper

import ng.mathemandy.data.model.ChapterEntity
import ng.mathemandy.remote.mapper.base.RemoteModelMapper
import ng.mathemandy.remote.model.ChapterRemoteModel
import javax.inject.Inject

class ChapterRemoteMapper @Inject constructor(private val lessonRemoteMapper: LessonRemoteMapper) :
    RemoteModelMapper<ChapterRemoteModel, ChapterEntity> {

    override fun mapFromModel(model: ChapterRemoteModel): ChapterEntity {
        return ChapterEntity(
            model.id,
            model.name,
            lessonRemoteMapper.mapModelList(model.lessons)
        )
    }
}
