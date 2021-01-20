package ng.mathemandy.remote.mapper

import ng.mathemandy.data.mapper.base.EntityMapper
import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.remote.mapper.base.RemoteModelMapper
import ng.mathemandy.remote.model.SubjectRemoteModel
import javax.inject.Inject

class SubjectRemoteMapper @Inject constructor(private val chapterRemoteMapper: ChapterRemoteMapper) :
    RemoteModelMapper<SubjectRemoteModel, SubjectEntity> {


    override fun mapFromModel(model: SubjectRemoteModel): SubjectEntity {
        return SubjectEntity(
            model.id,
            model.name,
            model.icon,
            chapterRemoteMapper.mapModelList(model.chapters)

        )
    }



}
