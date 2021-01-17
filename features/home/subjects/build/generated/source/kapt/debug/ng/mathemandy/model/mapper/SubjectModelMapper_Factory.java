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
  private final Provider<ChapterModelMapper> chapterModelMapperProvider;

  public SubjectModelMapper_Factory(Provider<ChapterModelMapper> chapterModelMapperProvider) {
    this.chapterModelMapperProvider = chapterModelMapperProvider;
  }

  @Override
  public SubjectModelMapper get() {
    return newInstance(chapterModelMapperProvider.get());
  }

  public static SubjectModelMapper_Factory create(
      Provider<ChapterModelMapper> chapterModelMapperProvider) {
    return new SubjectModelMapper_Factory(chapterModelMapperProvider);
  }

  public static SubjectModelMapper newInstance(ChapterModelMapper chapterModelMapper) {
    return new SubjectModelMapper(chapterModelMapper);
  }
}
