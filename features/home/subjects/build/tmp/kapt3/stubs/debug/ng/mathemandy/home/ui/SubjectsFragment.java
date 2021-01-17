package ng.mathemandy.home.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010%\u001a\u00020&H\u0002J\u0010\u0010\'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010\'\u001a\u00020&2\u0006\u0010(\u001a\u00020*H\u0016J\u0012\u0010+\u001a\u00020&2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u000200H\u0016J&\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0016\u00107\u001a\u00020&2\f\u00108\u001a\b\u0012\u0004\u0012\u00020:09H\u0002J\b\u0010;\u001a\u00020&H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010\u0019\u001a\u0004\b\"\u0010#\u00a8\u0006<"}, d2 = {"Lng/mathemandy/home/ui/SubjectsFragment;", "Landroidx/fragment/app/Fragment;", "Lng/mathemandy/home/ui/SubjectsAdapter$Interaction;", "Lng/mathemandy/home/ui/RecentVideosAdapter$Interaction;", "()V", "binding", "Lng/mathemandy/home/databinding/FragmentSubjectsBinding;", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "navigator", "Ljavax/inject/Provider;", "Lng/mathemandy/ulesson/navigation/NavigationDispatcher;", "getNavigator", "()Ljavax/inject/Provider;", "setNavigator", "(Ljavax/inject/Provider;)V", "recentVideosAdapter", "Lng/mathemandy/home/ui/RecentVideosAdapter;", "getRecentVideosAdapter", "()Lng/mathemandy/home/ui/RecentVideosAdapter;", "recentVideosAdapter$delegate", "Lkotlin/Lazy;", "subjectAdapter", "Lng/mathemandy/home/ui/SubjectsAdapter;", "getSubjectAdapter", "()Lng/mathemandy/home/ui/SubjectsAdapter;", "setSubjectAdapter", "(Lng/mathemandy/home/ui/SubjectsAdapter;)V", "viewModel", "Lng/mathemandy/home/presentation/SubjectsViewModel;", "getViewModel", "()Lng/mathemandy/home/presentation/SubjectsViewModel;", "viewModel$delegate", "initDataListener", "", "itemClicked", "item", "Lng/mathemandy/domain/model/Lesson;", "Lng/mathemandy/domain/model/Subject;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "renderSuccessState", "data", "", "Lng/mathemandy/model/SubjectModel;", "setUpRecyclerview", "subjects_debug"})
public final class SubjectsFragment extends androidx.fragment.app.Fragment implements ng.mathemandy.home.ui.SubjectsAdapter.Interaction, ng.mathemandy.home.ui.RecentVideosAdapter.Interaction {
    private ng.mathemandy.home.databinding.FragmentSubjectsBinding binding;
    @javax.inject.Inject()
    public ng.mathemandy.home.ui.SubjectsAdapter subjectAdapter;
    @javax.inject.Inject()
    public javax.inject.Provider<ng.mathemandy.ulesson.navigation.NavigationDispatcher> navigator;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory factory;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy recentVideosAdapter$delegate = null;
    
    @org.jetbrains.annotations.NotNull()
    public final ng.mathemandy.home.ui.SubjectsAdapter getSubjectAdapter() {
        return null;
    }
    
    public final void setSubjectAdapter(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.home.ui.SubjectsAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final javax.inject.Provider<ng.mathemandy.ulesson.navigation.NavigationDispatcher> getNavigator() {
        return null;
    }
    
    public final void setNavigator(@org.jetbrains.annotations.NotNull()
    javax.inject.Provider<ng.mathemandy.ulesson.navigation.NavigationDispatcher> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getFactory() {
        return null;
    }
    
    public final void setFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    private final ng.mathemandy.home.presentation.SubjectsViewModel getViewModel() {
        return null;
    }
    
    private final ng.mathemandy.home.ui.RecentVideosAdapter getRecentVideosAdapter() {
        return null;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initDataListener() {
    }
    
    private final void renderSuccessState(java.util.List<ng.mathemandy.model.SubjectModel> data) {
    }
    
    private final void setUpRecyclerview() {
    }
    
    @java.lang.Override()
    public void itemClicked(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.domain.model.Subject item) {
    }
    
    @java.lang.Override()
    public void itemClicked(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.domain.model.Lesson item) {
    }
    
    public SubjectsFragment() {
        super();
    }
}