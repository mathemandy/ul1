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
  private final Provider<LessonModelMapper> lessonModelMapperProvider;

  public ChapterModelMapper_Factory(Provider<LessonModelMapper> lessonModelMapperProvider) {
    this.lessonModelMapperProvider = lessonModelMapperProvider;
  }

  @Override
  public ChapterModelMapper get() {
    return newInstance(lessonModelMapperProvider.get());
  }

  public static ChapterModelMapper_Factory create(
      Provider<LessonModelMapper> lessonModelMapperProvider) {
    return new ChapterModelMapper_Factory(lessonModelMapperProvider);
  }

  public static ChapterModelMapper newInstance(LessonModelMapper lessonModelMapper) {
    return new ChapterModelMapper(lessonModelMapper);
  }
}
