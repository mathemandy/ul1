package ng.mathemandy.ulesson.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ng.mathemandy.ulesson.R
import ng.mathemandy.ulesson.navigation.NavigationDispatcher
import ng.mathemandy.ulesson.navigation.NavigationDispatcherImpl

@InstallIn(ActivityComponent::class)
@Module
interface NavigationModule {

    @get:Binds
    val NavigationDispatcherImpl.navigationDispatcher: NavigationDispatcher

    companion object {
        @Provides
        fun provideNavController(activity: Activity): NavController =
            activity.findNavController(R.id.main_host)
    }
}
