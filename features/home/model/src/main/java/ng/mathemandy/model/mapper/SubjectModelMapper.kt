package ng.mathemandy.model.mapper

import ng.mathemandy.domain.model.Subject
import ng.mathemandy.model.SubjectModel
import ng.mathemandy.model.base.ModelMapper
import javax.inject.Inject

class SubjectModelMapper @Inject constructor(private val chapterModelMapper: ChapterModelMapper) : ModelMapper<SubjectModel, Subject>{

    override fun mapToModel(domain: Subject): SubjectModel {
        return SubjectModel(
            domain.id,
            domain.name,
            domain.icon,
            chapterModelMapper.mapToModelList(domain.chapters)

        )
    }

    override fun mapToDomain(model: SubjectModel): Subject {
        return Subject(
            model.id,
            model.name,
            model.icon,
            chapterModelMapper.mapToDomainList(model.chapters)

        )
    }
}