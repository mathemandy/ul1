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
public final class ChapterModelMapper_Factory implements Factory<ChapterModelMapper> {
  private final Provider<LessonModelMapper> arg0Provider;

  public ChapterModelMapper_Factory(Provider<LessonModelMapper> arg0Provider) {
    this.arg0Provider = arg0Provider;
  }

  @Override
  public ChapterModelMapper get() {
    return newInstance(arg0Provider.get());
  }

  public static ChapterModelMapper_Factory create(Provider<LessonModelMapper> arg0Provider) {
    return new ChapterModelMapper_Factory(arg0Provider);
  }

  public static ChapterModelMapper newInstance(LessonModelMapper arg0) {
    return new ChapterModelMapper(arg0);
  }
}
