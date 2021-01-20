package ng.mathemandy.model.mapper;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LessonAndSubjectModelMapper_Factory implements Factory<LessonAndSubjectModelMapper> {
  private final Provider<SubjectModelMapper> arg0Provider;

  private final Provider<LessonModelMapper> arg1Provider;

  public LessonAndSubjectModelMapper_Factory(Provider<SubjectModelMapper> arg0Provider,
      Provider<LessonModelMapper> arg1Provider) {
    this.arg0Provider = arg0Provider;
    this.arg1Provider = arg1Provider;
  }

  @Override
  public LessonAndSubjectModelMapper get() {
    return newInstance(arg0Provider.get(), arg1Provider.get());
  }

  public static LessonAndSubjectModelMapper_Factory create(
      Provider<SubjectModelMapper> arg0Provider, Provider<LessonModelMapper> arg1Provider) {
    return new LessonAndSubjectModelMapper_Factory(arg0Provider, arg1Provider);
  }

  public static LessonAndSubjectModelMapper newInstance(SubjectModelMapper arg0,
      LessonModelMapper arg1) {
    return new LessonAndSubjectModelMapper(arg0, arg1);
  }
}
