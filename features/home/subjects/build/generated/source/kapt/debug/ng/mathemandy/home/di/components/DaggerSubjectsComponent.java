package ng.mathemandy.home.di.components;

import dagger.internal.Preconditions;
import javax.annotation.Generated;
import ng.mathemandy.core.di.components.CoreComponent;
import ng.mathemandy.home.ui.SubjectsAdapter;
import ng.mathemandy.home.ui.SubjectsFragment;
import ng.mathemandy.home.ui.SubjectsFragment_MembersInjector;
import ng.mathemandy.ulesson.di.AppComponent;

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

  private DaggerSubjectsComponent(CoreComponent coreComponentParam, AppComponent appComponent) {
    this.coreComponent = coreComponentParam;
  }

  public static SubjectsComponent.Factory factory() {
    return new Factory();
  }

  private SubjectsAdapter getSubjectsAdapter() {
    return new SubjectsAdapter(Preconditions.checkNotNull(coreComponent.getImageLoader(), "Cannot return null from a non-@Nullable component method"));}

  @Override
  public void inject(SubjectsFragment recipeFragment) {
    injectSubjectsFragment(recipeFragment);}

  private SubjectsFragment injectSubjectsFragment(SubjectsFragment instance) {
    SubjectsFragment_MembersInjector.injectSubjectAdapter(instance, getSubjectsAdapter());
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
}
