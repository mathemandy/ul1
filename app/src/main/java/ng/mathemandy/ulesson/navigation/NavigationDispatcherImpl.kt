package ng.mathemandy.ulesson.navigation

import javax.inject.Inject
import javax.inject.Provider

class NavigationDispatcherImpl  @Inject constructor(
    private val navController : Provider<NavController>
)