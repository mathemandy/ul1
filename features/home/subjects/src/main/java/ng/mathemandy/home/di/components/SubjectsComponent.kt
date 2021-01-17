package ng.mathemandy.home.di.components

import dagger.Component
import ng.mathemandy.core.di.components.CoreComponent
import ng.mathemandy.core.di.module.DataModule
import ng.mathemandy.core.di.module.FactoryModule
import ng.mathemandy.core.di.module.RemoteModule
import ng.mathemandy.core.di.scope.FeatureScope
import ng.mathemandy.home.di.module.ViewModelModule
import ng.mathemandy.home.ui.SubjectsFragment
import ng.mathemandy.ulesson.di.AppComponent

@FeatureScope
@Component(
    dependencies = [CoreComponent::class, AppComponent::class],
    modules = [FactoryModule::class,
        ViewModelModule::class]
)
interface SubjectsComponent {

    fun inject(subjectFragment: SubjectsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            appComponent: AppComponent
        ): SubjectsComponent
    }
}
