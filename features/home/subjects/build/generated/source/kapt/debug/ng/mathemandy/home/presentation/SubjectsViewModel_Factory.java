package ng.mathemandy.home.presentation;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import ng.mathemandy.domain.usecase.FetchSubjects;
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

  public SubjectsViewModel_Factory(Provider<SubjectModelMapper> subjectModelMapperProvider,
      Provider<FetchSubjects> fetchSubjectsUseCaseProvider) {
    this.subjectModelMapperProvider = subjectModelMapperProvider;
    this.fetchSubjectsUseCaseProvider = fetchSubjectsUseCaseProvider;
  }

  @Override
  public SubjectsViewModel get() {
    return newInstance(subjectModelMapperProvider.get(), fetchSubjectsUseCaseProvider.get());
  }

  public static SubjectsViewModel_Factory create(
      Provider<SubjectModelMapper> subjectModelMapperProvider,
      Provider<FetchSubjects> fetchSubjectsUseCaseProvider) {
    return new SubjectsViewModel_Factory(subjectModelMapperProvider, fetchSubjectsUseCaseProvider);
  }

  public static SubjectsViewModel newInstance(SubjectModelMapper subjectModelMapper,
      FetchSubjects fetchSubjectsUseCase) {
    return new SubjectsViewModel(subjectModelMapper, fetchSubjectsUseCase);
  }
}
