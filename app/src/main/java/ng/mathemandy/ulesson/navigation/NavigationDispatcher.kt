package ng.mathemandy.ulesson.navigation

import ng.mathemandy.model.LessonModel
import ng.mathemandy.model.SubjectModel

interface NavigationDispatcher {

    fun openChapters(model: SubjectModel)
    fun watchLesson(model :LessonModel)
    fun goBack()
}
