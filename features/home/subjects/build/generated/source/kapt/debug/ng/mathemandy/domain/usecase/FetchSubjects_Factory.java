package ng.mathemandy.domain.usecase;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import ng.mathemandy.domain.executor.PostExecutionThread;
import ng.mathemandy.domain.repository.SubjectRepository;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FetchSubjects_Factory implements Factory<FetchSubjects> {
  private final Provider<SubjectRepository> subjectRepositoryProvider;

  private final Provider<PostExecutionThread> postExecutionThreadProvider;

  public FetchSubjects_Factory(Provider<SubjectRepository> subjectRepositoryProvider,
      Provider<PostExecutionThread> postExecutionThreadProvider) {
    this.subjectRepositoryProvider = subjectRepositoryProvider;
    this.postExecutionThreadProvider = postExecutionThreadProvider;
  }

  @Override
  public FetchSubjects get() {
    return newInstance(subjectRepositoryProvider.get(), postExecutionThreadProvider.get());
  }

  public static FetchSubjects_Factory create(Provider<SubjectRepository> subjectRepositoryProvider,
      Provider<PostExecutionThread> postExecutionThreadProvider) {
    return new FetchSubjects_Factory(subjectRepositoryProvider, postExecutionThreadProvider);
  }

  public static FetchSubjects newInstance(SubjectRepository subjectRepository,
      PostExecutionThread postExecutionThread) {
    return new FetchSubjects(subjectRepository, postExecutionThread);
  }
}
