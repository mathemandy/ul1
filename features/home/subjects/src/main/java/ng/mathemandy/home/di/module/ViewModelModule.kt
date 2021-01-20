package ng.mathemandy.home.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import ng.mathemandy.core.di.mapkeys.ViewModelKey
import ng.mathemandy.home.presentation.SubjectsViewModel

@DisableInstallInCheck
@Module
interface ViewModelModule {

    @get:[Binds IntoMap ViewModelKey(SubjectsViewModel::class)]
    val SubjectsViewModel.subjectsViewModel: ViewModel
}
