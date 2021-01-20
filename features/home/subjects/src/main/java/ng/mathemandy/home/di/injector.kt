package ng.mathemandy.home.di

import dagger.hilt.android.EntryPointAccessors
import ng.mathemandy.core.di.components.CoreComponent
import ng.mathemandy.home.di.components.DaggerSubjectsComponent
import ng.mathemandy.home.ui.SubjectsFragment
import ng.mathemandy.ulesson.di.AppComponent

internal fun inject(fragment: SubjectsFragment) {
    DaggerSubjectsComponent
        .factory()
        .create(coreComponent(fragment), appComponent(fragment))
        .inject(fragment)
}

private fun coreComponent(fragment: SubjectsFragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        CoreComponent::class.java
    )

private fun appComponent(fragment: SubjectsFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )
