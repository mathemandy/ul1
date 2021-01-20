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
  private final Provider<RecentlyWatchedLessonRepository> arg0Provider;

  private final Provider<PostExecutionThread> arg1Provider;

  public FetchRecentlyWatchedLessons_Factory(Provider<RecentlyWatchedLessonRepository> arg0Provider,
      Provider<PostExecutionThread> arg1Provider) {
    this.arg0Provider = arg0Provider;
    this.arg1Provider = arg1Provider;
  }

  @Override
  public FetchRecentlyWatchedLessons get() {
    return newInstance(arg0Provider.get(), arg1Provider.get());
  }

  public static FetchRecentlyWatchedLessons_Factory create(
      Provider<RecentlyWatchedLessonRepository> arg0Provider,
      Provider<PostExecutionThread> arg1Provider) {
    return new FetchRecentlyWatchedLessons_Factory(arg0Provider, arg1Provider);
  }

  public static FetchRecentlyWatchedLessons newInstance(RecentlyWatchedLessonRepository arg0,
      PostExecutionThread arg1) {
    return new FetchRecentlyWatchedLessons(arg0, arg1);
  }
}
