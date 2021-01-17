package ng.mathemandy.lessons.di

import dagger.hilt.android.EntryPointAccessors
import ng.mathemandy.core.di.components.CoreComponent
import ng.mathemandy.lessons.di.component.DaggerLessonComponent
import ng.mathemandy.lessons.ui.LessonsFragment
import ng.mathemandy.ulesson.di.AppComponent

internal fun inject(fragment: LessonsFragment) {
    DaggerLessonComponent
        .factory()
        .create(coreComponent(fragment), appComponent(fragment))
        .inject(fragment)
}

private fun coreComponent(fragment: LessonsFragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        CoreComponent::class.java
    )

private fun appComponent(fragment: LessonsFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )