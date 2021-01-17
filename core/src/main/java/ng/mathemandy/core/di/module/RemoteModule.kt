package ng.mathemandy.core.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ng.mathemandy.core.BuildConfig
import ng.mathemandy.data.contract.SubjectsRemote
import ng.mathemandy.remote.ApiService
import ng.mathemandy.remote.ApiServiceFactory
import ng.mathemandy.remote.impl.SubjectRemoteImpl
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface RemoteModule {

    @get:[Binds Singleton]
    val SubjectRemoteImpl.bindRemote: SubjectsRemote

    companion object {
        @get:[Provides Singleton]
        val provideMoshi: Moshi
            get() = Moshi.Builder()
                .add(KotlinJsonAdapterFactory()).build()

        @[Provides Singleton]
        fun provideApiService(moshi: Moshi): ApiService =
            ApiServiceFactory.makeAPiService(BuildConfig.DEBUG, moshi)
    }
}
