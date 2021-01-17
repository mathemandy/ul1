package ng.mathemandy.lessons.di.component

import dagger.Component
import ng.mathemandy.core.di.components.CoreComponent
import ng.mathemandy.core.di.scope.FeatureScope
import ng.mathemandy.lessons.ui.LessonsFragment
import ng.mathemandy.ulesson.di.AppComponent

@FeatureScope
@Component(
    dependencies = [CoreComponent::class, AppComponent::class],
    modules = []
)
interface LessonComponent {

    fun inject(lessonsFragment: LessonsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            appComponent: AppComponent
        ): LessonComponent
    }
}
