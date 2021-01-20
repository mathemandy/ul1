package ng.mathemandy.home.presentation;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import ng.mathemandy.domain.usecase.FetchRecentlyWatchedLessons;
import ng.mathemandy.domain.usecase.FetchSubjects;
import ng.mathemandy.model.mapper.LessonAndSubjectModelMapper;
import ng.mathemandy.model.mapper.SubjectModelMapper;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SubjectsViewModel_Factory implements Factory<SubjectsViewModel> {
  private final Provider<SubjectModelMapper> subjectModelMapperProvider;

  private final Provider<FetchSubjects> fetchSubjectsUseCaseProvider;

  private final Provider<FetchRecentlyWatchedLessons> recentlyWatchedLessonsUseCaseProvider;

  private final Provider<LessonAndSubjectModelMapper> lessonAndSubjectModelProvider;

  public SubjectsViewModel_Factory(Provider<SubjectModelMapper> subjectModelMapperProvider,
      Provider<FetchSubjects> fetchSubjectsUseCaseProvider,
      Provider<FetchRecentlyWatchedLessons> recentlyWatchedLessonsUseCaseProvider,
      Provider<LessonAndSubjectModelMapper> lessonAndSubjectModelProvider) {
    this.subjectModelMapperProvider = subjectModelMapperProvider;
    this.fetchSubjectsUseCaseProvider = fetchSubjectsUseCaseProvider;
    this.recentlyWatchedLessonsUseCaseProvider = recentlyWatchedLessonsUseCaseProvider;
    this.lessonAndSubjectModelProvider = lessonAndSubjectModelProvider;
  }

  @Override
  public SubjectsViewModel get() {
    return newInstance(subjectModelMapperProvider.get(), fetchSubjectsUseCaseProvider.get(), recentlyWatchedLessonsUseCaseProvider.get(), lessonAndSubjectModelProvider.get());
  }

  public static SubjectsViewModel_Factory create(
      Provider<SubjectModelMapper> subjectModelMapperProvider,
      Provider<FetchSubjects> fetchSubjectsUseCaseProvider,
      Provider<FetchRecentlyWatchedLessons> recentlyWatchedLessonsUseCaseProvider,
      Provider<LessonAndSubjectModelMapper> lessonAndSubjectModelProvider) {
    return new SubjectsViewModel_Factory(subjectModelMapperProvider, fetchSubjectsUseCaseProvider, recentlyWatchedLessonsUseCaseProvider, lessonAndSubjectModelProvider);
  }

  public static SubjectsViewModel newInstance(SubjectModelMapper subjectModelMapper,
      FetchSubjects fetchSubjectsUseCase, FetchRecentlyWatchedLessons recentlyWatchedLessonsUseCase,
      LessonAndSubjectModelMapper lessonAndSubjectModel) {
    return new SubjectsViewModel(subjectModelMapper, fetchSubjectsUseCase, recentlyWatchedLessonsUseCase, lessonAndSubjectModel);
  }
}
