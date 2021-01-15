package ng.mathemandy.ulesson.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Provider

class NavigationDispatcherImpl @Inject constructor(
    private val navController: Provider<NavController>
) : NavigationDispatcher {
    override fun openChapters() {
        TODO("Not yet implemented")
    }

    override fun openLessons() {
        TODO("Not yet implemented")
    }

    override fun goBack() {
        navController.get().navigateUp()
    }
}
