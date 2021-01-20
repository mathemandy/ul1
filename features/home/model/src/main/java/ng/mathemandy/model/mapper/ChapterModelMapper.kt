package ng.mathemandy.model.mapper

import ng.mathemandy.domain.model.Chapter
import ng.mathemandy.model.ChapterModel
import ng.mathemandy.model.base.ModelMapper
import javax.inject.Inject

class ChapterModelMapper @Inject constructor(private val lessonModelMapper: LessonModelMapper) :
    ModelMapper<ChapterModel, Chapter> {

    override fun mapToModel(domain: Chapter): ChapterModel {
        return ChapterModel(
            domain.id,
            domain.name,
            lessonModelMapper.mapToModelList(domain.lessons)
        )
    }

    override fun mapToDomain(model: ChapterModel): Chapter {
        return Chapter(
            model.id,
            model.name,
            lessonModelMapper.mapToDomainList(model.lessons)
        )
    }
}
