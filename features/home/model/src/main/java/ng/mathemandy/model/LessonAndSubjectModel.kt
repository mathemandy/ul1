package ng.mathemandy.model


data class LessonAndSubjectModel(
    var subject: SubjectModel,
    var lesson: LessonModel? = null
)