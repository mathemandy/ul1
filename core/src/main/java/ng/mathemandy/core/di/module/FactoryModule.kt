package ng.mathemandy.core.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import ng.mathemandy.core.factory.ViewModelFactory

@[Module DisableInstallInCheck]
interface FactoryModule {

    @get:Binds
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory
}
