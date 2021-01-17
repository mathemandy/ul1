package ng.mathemandy.core.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ng.mathemandy.data.repository.SubjectRepositoryImpl
import ng.mathemandy.domain.repository.SubjectRepository
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface DataModule {

    @get:[Binds Singleton]
    val SubjectRepositoryImpl.subjectRepository: SubjectRepository
}
