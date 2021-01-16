package ng.mathemandy.home.ui;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
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
public final class SubjectsFragment_MembersInjector implements MembersInjector<SubjectsFragment> {
  private final Provider<SubjectsAdapter> subjectAdapterProvider;

  public SubjectsFragment_MembersInjector(Provider<SubjectsAdapter> subjectAdapterProvider) {
    this.subjectAdapterProvider = subjectAdapterProvider;
  }

  public static MembersInjector<SubjectsFragment> create(
      Provider<SubjectsAdapter> subjectAdapterProvider) {
    return new SubjectsFragment_MembersInjector(subjectAdapterProvider);}

  @Override
  public void injectMembers(SubjectsFragment instance) {
    injectSubjectAdapter(instance, subjectAdapterProvider.get());
  }

  @InjectedFieldSignature("ng.mathemandy.home.ui.SubjectsFragment.subjectAdapter")
  public static void injectSubjectAdapter(SubjectsFragment instance,
      SubjectsAdapter subjectAdapter) {
    instance.subjectAdapter = subjectAdapter;
  }
}
