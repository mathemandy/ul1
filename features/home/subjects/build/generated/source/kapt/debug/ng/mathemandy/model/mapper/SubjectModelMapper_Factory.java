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
public final class SubjectModelMapper_Factory implements Factory<SubjectModelMapper> {
  private final Provider<ChapterModelMapper> arg0Provider;

  public SubjectModelMapper_Factory(Provider<ChapterModelMapper> arg0Provider) {
    this.arg0Provider = arg0Provider;
  }

  @Override
  public SubjectModelMapper get() {
    return newInstance(arg0Provider.get());
  }

  public static SubjectModelMapper_Factory create(Provider<ChapterModelMapper> arg0Provider) {
    return new SubjectModelMapper_Factory(arg0Provider);
  }

  public static SubjectModelMapper newInstance(ChapterModelMapper arg0) {
    return new SubjectModelMapper(arg0);
  }
}
