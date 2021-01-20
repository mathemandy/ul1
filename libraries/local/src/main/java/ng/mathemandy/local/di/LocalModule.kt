package ng.mathemandy.local.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ng.mathemandy.data.contract.local.LessonAndSubjectLocal
import ng.mathemandy.data.contract.local.SubjectsLocal
import ng.mathemandy.local.localImpl.LessonAndSubjectLocalImpl
import ng.mathemandy.local.localImpl.SubjectLocalImpl
import ng.mathemandy.local.room.LessonAndSubjectDao
import ng.mathemandy.local.room.SubjectDao
import ng.mathemandy.local.room.UlessonDatabase
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface LocalModule {

    @get:[Binds Singleton]
    val SubjectLocalImpl.bindLocal: SubjectsLocal

    @get:[Binds Singleton]
    val LessonAndSubjectLocalImpl.bindLASLocal: LessonAndSubjectLocal

    companion object {
        @[Provides Singleton]
        fun provideDatabase(@ApplicationContext context: Context): UlessonDatabase {
            return UlessonDatabase.build(context)
        }

        @[Provides Singleton]
        fun provideSubjectsDao(uLessonDatabase: UlessonDatabase): SubjectDao {
            return uLessonDatabase.subjectDao
        }

        @[Provides Singleton]
        fun provideRecentLessonDao(uLessonDatabase: UlessonDatabase): LessonAndSubjectDao {
            return uLessonDatabase.lessonAndSubjectDao
        }
    }
}
