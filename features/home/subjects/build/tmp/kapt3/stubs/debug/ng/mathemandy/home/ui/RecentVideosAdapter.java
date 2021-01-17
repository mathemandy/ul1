package ng.mathemandy.home.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0003\u0015\u0016\u0017B\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\f\u001a\u00020\bH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J#\u0010\u0011\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lng/mathemandy/home/ui/RecentVideosAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lng/mathemandy/domain/model/Lesson;", "Lng/mathemandy/home/ui/RecentVideosAdapter$RecentVideosViewHolder;", "interaction", "Lng/mathemandy/home/ui/RecentVideosAdapter$Interaction;", "(Lng/mathemandy/home/ui/RecentVideosAdapter$Interaction;)V", "layoutId", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "swapData", "data", "", "(Ljava/util/List;Ljava/lang/Integer;)V", "Interaction", "RecentVideosDC", "RecentVideosViewHolder", "subjects_debug"})
public final class RecentVideosAdapter extends androidx.recyclerview.widget.ListAdapter<ng.mathemandy.domain.model.Lesson, ng.mathemandy.home.ui.RecentVideosAdapter.RecentVideosViewHolder> {
    private int layoutId = ng.mathemandy.home.R.layout.recent_videos;
    private final ng.mathemandy.home.ui.RecentVideosAdapter.Interaction interaction = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public ng.mathemandy.home.ui.RecentVideosAdapter.RecentVideosViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.home.ui.RecentVideosAdapter.RecentVideosViewHolder holder, int position) {
    }
    
    public final void swapData(@org.jetbrains.annotations.NotNull()
    java.util.List<ng.mathemandy.domain.model.Lesson> data, @org.jetbrains.annotations.Nullable()
    java.lang.Integer layoutId) {
    }
    
    public RecentVideosAdapter(@org.jetbrains.annotations.Nullable()
    ng.mathemandy.home.ui.RecentVideosAdapter.Interaction interaction) {
        super(null);
    }
    
    public RecentVideosAdapter() {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0016R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lng/mathemandy/home/ui/RecentVideosAdapter$RecentVideosViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "itemView", "Landroid/view/View;", "interaction", "Lng/mathemandy/home/ui/RecentVideosAdapter$Interaction;", "(Lng/mathemandy/home/ui/RecentVideosAdapter;Landroid/view/View;Lng/mathemandy/home/ui/RecentVideosAdapter$Interaction;)V", "cardColor", "", "", "getCardColor", "()Ljava/util/List;", "bind", "", "item", "Lng/mathemandy/domain/model/Lesson;", "onClick", "v", "subjects_debug"})
    public final class RecentVideosViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Integer> cardColor = null;
        private final ng.mathemandy.home.ui.RecentVideosAdapter.Interaction interaction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> getCardColor() {
            return null;
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.Nullable()
        android.view.View v) {
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.domain.model.Lesson item) {
        }
        
        public RecentVideosViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView, @org.jetbrains.annotations.Nullable()
        ng.mathemandy.home.ui.RecentVideosAdapter.Interaction interaction) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lng/mathemandy/home/ui/RecentVideosAdapter$Interaction;", "", "itemClicked", "", "item", "Lng/mathemandy/domain/model/Lesson;", "subjects_debug"})
    public static abstract interface Interaction {
        
        public abstract void itemClicked(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.domain.model.Lesson item);
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lng/mathemandy/home/ui/RecentVideosAdapter$RecentVideosDC;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lng/mathemandy/domain/model/Lesson;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "subjects_debug"})
    static final class RecentVideosDC extends androidx.recyclerview.widget.DiffUtil.ItemCallback<ng.mathemandy.domain.model.Lesson> {
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.domain.model.Lesson oldItem, @org.jetbrains.annotations.NotNull()
        ng.mathemandy.domain.model.Lesson newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.domain.model.Lesson oldItem, @org.jetbrains.annotations.NotNull()
        ng.mathemandy.domain.model.Lesson newItem) {
            return false;
        }
        
        public RecentVideosDC() {
            super();
        }
    }
}