package ng.mathemandy.domain.usecase;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import ng.mathemandy.domain.executor.PostExecutionThread;
import ng.mathemandy.domain.repository.RecentlyWatchedLessonRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FetchRecentlyWatchedLessons_Factory implements Factory<FetchRecentlyWatchedLessons> {
  private final Provider<RecentlyWatchedLessonRepository> recentlyWatchedLessonsRepositoryProvider;

  private final Provider<PostExecutionThread> postExecutionThreadProvider;

  public FetchRecentlyWatchedLessons_Factory(
      Provider<RecentlyWatchedLessonRepository> recentlyWatchedLessonsRepositoryProvider,
      Provider<PostExecutionThread> postExecutionThreadProvider) {
    this.recentlyWatchedLessonsRepositoryProvider = recentlyWatchedLessonsRepositoryProvider;
    this.postExecutionThreadProvider = postExecutionThreadProvider;
  }

  @Override
  public FetchRecentlyWatchedLessons get() {
    return newInstance(recentlyWatchedLessonsRepositoryProvider.get(), postExecutionThreadProvider.get());
  }

  public static FetchRecentlyWatchedLessons_Factory create(
      Provider<RecentlyWatchedLessonRepository> recentlyWatchedLessonsRepositoryProvider,
      Provider<PostExecutionThread> postExecutionThreadProvider) {
    return new FetchRecentlyWatchedLessons_Factory(recentlyWatchedLessonsRepositoryProvider, postExecutionThreadProvider);
  }

  public static FetchRecentlyWatchedLessons newInstance(
      RecentlyWatchedLessonRepository recentlyWatchedLessonsRepository,
      PostExecutionThread postExecutionThread) {
    return new FetchRecentlyWatchedLessons(recentlyWatchedLessonsRepository, postExecutionThread);
  }
}
