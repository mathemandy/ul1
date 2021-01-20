package ng.mathemandy.core.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ng.mathemandy.core.imageLoader.ImageLoader
import ng.mathemandy.core.imageLoader.ImageLoaderImpl
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface ImageLoaderModule {

    @get:[Binds Singleton]
    val ImageLoaderImpl.imageLoader: ImageLoader
}
