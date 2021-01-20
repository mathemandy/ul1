package ng.mathemandy.home.di.components;

import androidx.lifecycle.ViewModel;
import dagger.internal.Preconditions;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;
import ng.mathemandy.core.di.components.CoreComponent;
import ng.mathemandy.core.factory.ViewModelFactory;
import ng.mathemandy.domain.usecase.FetchRecentlyWatchedLessons;
import ng.mathemandy.domain.usecase.FetchSubjects;
import ng.mathemandy.home.presentation.SubjectsViewModel;
import ng.mathemandy.home.ui.RecentVideosAdapter;
import ng.mathemandy.home.ui.SubjectsAdapter;
import ng.mathemandy.home.ui.SubjectsFragment;
import ng.mathemandy.home.ui.SubjectsFragment_MembersInjector;
import ng.mathemandy.model.mapper.ChapterModelMapper;
import ng.mathemandy.model.mapper.LessonAndSubjectModelMapper;
import ng.mathemandy.model.mapper.LessonModelMapper;
import ng.mathemandy.model.mapper.SubjectModelMapper;
import ng.mathemandy.ulesson.di.AppComponent;
import ng.mathemandy.ulesson.navigation.NavigationDispatcher;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerSubjectsComponent implements SubjectsComponent {
  private final CoreComponent coreComponent;

  private final AppComponent appComponent;

  private volatile Provider<NavigationDispatcher> getNavigationDisPatcherProvider;

  private volatile Provider<SubjectsViewModel> subjectsViewModelProvider;

  private DaggerSubjectsComponent(CoreComponent coreComponentParam,
      AppComponent appComponentParam) {
    this.coreComponent = coreComponentParam;
    this.appComponent = appComponentParam;
  }

  public static SubjectsComponent.Factory factory() {
    return new Factory();
  }

  private SubjectsAdapter getSubjectsAdapter() {
    return new SubjectsAdapter(Preconditions.checkNotNull(coreComponent.getImageLoader(), "Cannot return null from a non-@Nullable component method"));}

  private Provider<NavigationDispatcher> getNavigationDispatcherProvider() {
    Object local = getNavigationDisPatcherProvider;
    if (local == null) {
      local = new SwitchingProvider<>(0);
      getNavigationDisPatcherProvider = (Provider<NavigationDispatcher>) local;
    }
    return (Provider<NavigationDispatcher>) local;
  }

  private ChapterModelMapper getChapterModelMapper() {
    return new ChapterModelMapper(new LessonModelMapper());}

  private SubjectModelMapper getSubjectModelMapper() {
    return new SubjectModelMapper(getChapterModelMapper());}

  private FetchSubjects getFetchSubjects() {
    return new FetchSubjects(Preconditions.checkNotNull(coreComponent.getSubjectRepository(), "Cannot return null from a non-@Nullable component method"), Preconditions.checkNotNull(coreComponent.getPostExecutionThread(), "Cannot return null from a non-@Nullable component method"));}

  private FetchRecentlyWatchedLessons getFetchRecentlyWatchedLessons() {
    return new FetchRecentlyWatchedLessons(Preconditions.checkNotNull(coreComponent.getRecentlyWatchedLessonRepository(), "Cannot return null from a non-@Nullable component method"), Preconditions.checkNotNull(coreComponent.getPostExecutionThread(), "Cannot return null from a non-@Nullable component method"));}

  private LessonAndSubjectModelMapper getLessonAndSubjectModelMapper() {
    return new LessonAndSubjectModelMapper(getSubjectModelMapper(), new LessonModelMapper());}

  private SubjectsViewModel getSubjectsViewModel() {
    return new SubjectsViewModel(getSubjectModelMapper(), getFetchSubjects(), getFetchRecentlyWatchedLessons(), getLessonAndSubjectModelMapper());}

  private Provider<SubjectsViewModel> getSubjectsViewModelProvider() {
    Object local = subjectsViewModelProvider;
    if (local == null) {
      local = new SwitchingProvider<>(1);
      subjectsViewModelProvider = (Provider<SubjectsViewModel>) local;
    }
    return (Provider<SubjectsViewModel>) local;
  }

  private Map<Class<? extends ViewModel>, Provider<ViewModel>> getMapOfClassOfAndProviderOfViewModel(
      ) {
    return Collections.<Class<? extends ViewModel>, Provider<ViewModel>>singletonMap(SubjectsViewModel.class, (Provider) getSubjectsViewModelProvider());}

  private ViewModelFactory getViewModelFactory() {
    return new ViewModelFactory(getMapOfClassOfAndProviderOfViewModel());}

  private RecentVideosAdapter getRecentVideosAdapter() {
    return new RecentVideosAdapter(Preconditions.checkNotNull(coreComponent.getImageLoader(), "Cannot return null from a non-@Nullable component method"));}

  @Override
  public void inject(SubjectsFragment subjectFragment) {
    injectSubjectsFragment(subjectFragment);}

  private SubjectsFragment injectSubjectsFragment(SubjectsFragment instance) {
    SubjectsFragment_MembersInjector.injectSubjectAdapter(instance, getSubjectsAdapter());
    SubjectsFragment_MembersInjector.injectNavigator(instance, getNavigationDispatcherProvider());
    SubjectsFragment_MembersInjector.injectFactory(instance, getViewModelFactory());
    SubjectsFragment_MembersInjector.injectRecentVideosAdapter(instance, getRecentVideosAdapter());
    return instance;
  }

  private static final class Factory implements SubjectsComponent.Factory {
    @Override
    public SubjectsComponent create(CoreComponent coreComponent, AppComponent appComponent) {
      Preconditions.checkNotNull(coreComponent);
      Preconditions.checkNotNull(appComponent);
      return new DaggerSubjectsComponent(coreComponent, appComponent);
    }
  }

  private final class SwitchingProvider<T> implements Provider<T> {
    private final int id;

    SwitchingProvider(int id) {
      this.id = id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
      switch (id) {
        case 0: // ng.mathemandy.ulesson.navigation.NavigationDispatcher 
        return (T) Preconditions.checkNotNull(DaggerSubjectsComponent.this.appComponent.getNavigationDisPatcher(), "Cannot return null from a non-@Nullable component method");

        case 1: // ng.mathemandy.home.presentation.SubjectsViewModel 
        return (T) DaggerSubjectsComponent.this.getSubjectsViewModel();

        default: throw new AssertionError(id);
      }
    }
  }
}
