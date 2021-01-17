package ng.mathemandy.home.ui;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import javax.annotation.Generated;
import javax.inject.Provider;
import ng.mathemandy.ulesson.navigation.NavigationDispatcher;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SubjectsFragment_MembersInjector implements MembersInjector<SubjectsFragment> {
  private final Provider<SubjectsAdapter> subjectAdapterProvider;

  private final Provider<NavigationDispatcher> navigatorProvider;

  private final Provider<ViewModelProvider.Factory> factoryProvider;

  public SubjectsFragment_MembersInjector(Provider<SubjectsAdapter> subjectAdapterProvider,
      Provider<NavigationDispatcher> navigatorProvider,
      Provider<ViewModelProvider.Factory> factoryProvider) {
    this.subjectAdapterProvider = subjectAdapterProvider;
    this.navigatorProvider = navigatorProvider;
    this.factoryProvider = factoryProvider;
  }

  public static MembersInjector<SubjectsFragment> create(
      Provider<SubjectsAdapter> subjectAdapterProvider,
      Provider<NavigationDispatcher> navigatorProvider,
      Provider<ViewModelProvider.Factory> factoryProvider) {
    return new SubjectsFragment_MembersInjector(subjectAdapterProvider, navigatorProvider, factoryProvider);}

  @Override
  public void injectMembers(SubjectsFragment instance) {
    injectSubjectAdapter(instance, subjectAdapterProvider.get());
    injectNavigator(instance, navigatorProvider);
    injectFactory(instance, factoryProvider.get());
  }

  @InjectedFieldSignature("ng.mathemandy.home.ui.SubjectsFragment.subjectAdapter")
  public static void injectSubjectAdapter(SubjectsFragment instance,
      SubjectsAdapter subjectAdapter) {
    instance.subjectAdapter = subjectAdapter;
  }

  @InjectedFieldSignature("ng.mathemandy.home.ui.SubjectsFragment.navigator")
  public static void injectNavigator(SubjectsFragment instance,
      Provider<NavigationDispatcher> navigator) {
    instance.navigator = navigator;
  }

  @InjectedFieldSignature("ng.mathemandy.home.ui.SubjectsFragment.factory")
  public static void injectFactory(SubjectsFragment instance, ViewModelProvider.Factory factory) {
    instance.factory = factory;
  }
}
