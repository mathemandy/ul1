package ng.mathemandy.core.di.components

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ng.mathemandy.core.imageLoader.ImageLoader
import ng.mathemandy.domain.executor.PostExecutionThread
import ng.mathemandy.domain.repository.RecentlyWatchedLessonRepository
import ng.mathemandy.domain.repository.SubjectRepository


@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreComponent {
    val imageLoader : ImageLoader
    val subjectRepository: SubjectRepository
    val recentlyWatchedLessonRepository: RecentlyWatchedLessonRepository
    val postExecutionThread: PostExecutionThread
}
