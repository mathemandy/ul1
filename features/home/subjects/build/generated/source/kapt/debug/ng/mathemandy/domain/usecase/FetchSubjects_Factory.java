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
  private final Provider<SubjectRepository> arg0Provider;

  private final Provider<PostExecutionThread> arg1Provider;

  public FetchSubjects_Factory(Provider<SubjectRepository> arg0Provider,
      Provider<PostExecutionThread> arg1Provider) {
    this.arg0Provider = arg0Provider;
    this.arg1Provider = arg1Provider;
  }

  @Override
  public FetchSubjects get() {
    return newInstance(arg0Provider.get(), arg1Provider.get());
  }

  public static FetchSubjects_Factory create(Provider<SubjectRepository> arg0Provider,
      Provider<PostExecutionThread> arg1Provider) {
    return new FetchSubjects_Factory(arg0Provider, arg1Provider);
  }

  public static FetchSubjects newInstance(SubjectRepository arg0, PostExecutionThread arg1) {
    return new FetchSubjects(arg0, arg1);
  }
}
