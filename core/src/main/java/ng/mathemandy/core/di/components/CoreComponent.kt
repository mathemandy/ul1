package ng.mathemandy.core.di.components

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ng.mathemandy.core.imageLoader.ImageLoader


@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreComponent {
    val imageLoader : ImageLoader

}
