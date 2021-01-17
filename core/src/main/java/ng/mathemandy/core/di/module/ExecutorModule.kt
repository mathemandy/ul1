package ng.mathemandy.core.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ng.mathemandy.core.executor.PostExecutionThreadImpl
import ng.mathemandy.domain.executor.PostExecutionThread
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}