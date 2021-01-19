package ng.mathemandy.lessondetail.di

import dagger.hilt.android.EntryPointAccessors
import ng.mathemandy.lessondetail.di.component.DaggerLessonDetailComponent
import ng.mathemandy.lessondetail.ui.LessonDetailFragment
import ng.mathemandy.ulesson.di.AppComponent


fun inject(fragment: LessonDetailFragment) {
    DaggerLessonDetailComponent
        .factory()
        .create(appComponent(fragment))
        .inject(fragment)
}

private fun appComponent(fragment: LessonDetailFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )
