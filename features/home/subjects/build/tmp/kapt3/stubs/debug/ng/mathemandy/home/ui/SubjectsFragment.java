package ng.mathemandy.home.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010$\u001a\u00020\u0014H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006%"}, d2 = {"Lng/mathemandy/home/ui/SubjectsFragment;", "Landroidx/fragment/app/Fragment;", "Lng/mathemandy/home/ui/SubjectsAdapter$Interaction;", "Lng/mathemandy/home/ui/RecentVideosAdapter$Interaction;", "()V", "binding", "Lng/mathemandy/home/databinding/FragmentSubjectsBinding;", "recentVideosAdapter", "Lng/mathemandy/home/ui/RecentVideosAdapter;", "getRecentVideosAdapter", "()Lng/mathemandy/home/ui/RecentVideosAdapter;", "recentVideosAdapter$delegate", "Lkotlin/Lazy;", "subjectAdapter", "Lng/mathemandy/home/ui/SubjectsAdapter;", "getSubjectAdapter", "()Lng/mathemandy/home/ui/SubjectsAdapter;", "setSubjectAdapter", "(Lng/mathemandy/home/ui/SubjectsAdapter;)V", "itemClicked", "", "item", "Lng/mathemandy/ulesson/base/Lessons;", "Lng/mathemandy/ulesson/base/Subjects;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setUpRecyclerview", "subjects_debug"})
public final class SubjectsFragment extends androidx.fragment.app.Fragment implements ng.mathemandy.home.ui.SubjectsAdapter.Interaction, ng.mathemandy.home.ui.RecentVideosAdapter.Interaction {
    private ng.mathemandy.home.databinding.FragmentSubjectsBinding binding;
    @javax.inject.Inject()
    public ng.mathemandy.home.ui.SubjectsAdapter subjectAdapter;
    private final kotlin.Lazy recentVideosAdapter$delegate = null;
    
    @org.jetbrains.annotations.NotNull()
    public final ng.mathemandy.home.ui.SubjectsAdapter getSubjectAdapter() {
        return null;
    }
    
    public final void setSubjectAdapter(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.home.ui.SubjectsAdapter p0) {
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
    
    private final void setUpRecyclerview() {
    }
    
    @java.lang.Override()
    public void itemClicked(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.ulesson.base.Subjects item) {
    }
    
    @java.lang.Override()
    public void itemClicked(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.ulesson.base.Lessons item) {
    }
    
    public SubjectsFragment() {
        super();
    }
}