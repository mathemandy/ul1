package ng.mathemandy.ulesson.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import ng.mathemandy.model.LessonModel
import ng.mathemandy.model.SubjectModel
import ng.mathemandy.ulesson.R
import javax.inject.Inject
import javax.inject.Provider

class NavigationDispatcherImpl @Inject constructor(
    private val navController: Provider<NavController>
) : NavigationDispatcher {
    override fun openChapters(model: SubjectModel) {
        navController.get().navigate(
            R.id.lessonFragment,
            bundleOf(SUBJECT_ARG to model)
        )
    }

    override fun watchLesson(model: LessonModel) {
        navController.get().navigate(
            R.id.lessonDetailFragment,
            bundleOf(LESSON_ARG to model)
        )
    }

    override fun goBack() {
        navController.get().navigateUp()
    }

    companion object {
        const val SUBJECT_ARG: String = "subject"
        const val LESSON_ARG: String = "lesson"
    }
}
