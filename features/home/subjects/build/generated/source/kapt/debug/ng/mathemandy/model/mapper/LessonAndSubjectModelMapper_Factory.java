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
  private final Provider<SubjectModelMapper> subjectModelMapperProvider;

  private final Provider<LessonModelMapper> lessonModelMapperProvider;

  public LessonAndSubjectModelMapper_Factory(
      Provider<SubjectModelMapper> subjectModelMapperProvider,
      Provider<LessonModelMapper> lessonModelMapperProvider) {
    this.subjectModelMapperProvider = subjectModelMapperProvider;
    this.lessonModelMapperProvider = lessonModelMapperProvider;
  }

  @Override
  public LessonAndSubjectModelMapper get() {
    return newInstance(subjectModelMapperProvider.get(), lessonModelMapperProvider.get());
  }

  public static LessonAndSubjectModelMapper_Factory create(
      Provider<SubjectModelMapper> subjectModelMapperProvider,
      Provider<LessonModelMapper> lessonModelMapperProvider) {
    return new LessonAndSubjectModelMapper_Factory(subjectModelMapperProvider, lessonModelMapperProvider);
  }

  public static LessonAndSubjectModelMapper newInstance(SubjectModelMapper subjectModelMapper,
      LessonModelMapper lessonModelMapper) {
    return new LessonAndSubjectModelMapper(subjectModelMapper, lessonModelMapper);
  }
}
