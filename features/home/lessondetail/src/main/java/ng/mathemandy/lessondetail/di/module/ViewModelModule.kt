package ng.mathemandy.lessondetail.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import ng.mathemandy.core.di.mapkeys.ViewModelKey
import ng.mathemandy.lessondetail.ui.LessonDetailViewModel

@DisableInstallInCheck
@Module
interface ViewModelModule {

    @get:[Binds IntoMap ViewModelKey(LessonDetailViewModel::class)]
    val LessonDetailViewModel.subjectsViewModel: ViewModel
}
