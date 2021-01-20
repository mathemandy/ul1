package ng.mathemandy.ulesson.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ng.mathemandy.ulesson.navigation.NavigationDispatcher

@EntryPoint
@InstallIn(ActivityComponent::class)
interface AppComponent {
    val navigationDisPatcher: NavigationDispatcher
}
