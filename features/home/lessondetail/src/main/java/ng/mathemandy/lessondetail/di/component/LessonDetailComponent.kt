package ng.mathemandy.lessondetail.di.component

import dagger.Component
import ng.mathemandy.core.di.components.CoreComponent
import ng.mathemandy.core.di.module.FactoryModule
import ng.mathemandy.core.di.scope.FeatureScope
import ng.mathemandy.lessondetail.di.module.ViewModelModule
import ng.mathemandy.lessondetail.ui.LessonDetailFragment
import ng.mathemandy.ulesson.di.AppComponent

@FeatureScope
@Component(
    dependencies = [CoreComponent::class, AppComponent::class],
    modules = [FactoryModule::class, ViewModelModule::class]
)
interface LessonDetailComponent {

    fun inject(fragment: LessonDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            appComponent: AppComponent): LessonDetailComponent
    }
}
