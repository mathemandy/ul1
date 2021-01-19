package ng.mathemandy.home.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u001c\u001dB\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0011\u001a\u00020\t2\n\u0010\u0012\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u001c\u0010\u0014\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J#\u0010\u0018\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0002\u0010\u001bR.\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0004\u0018\u0001`\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lng/mathemandy/home/ui/RecentVideosAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lng/mathemandy/model/LessonModel;", "Lng/mathemandy/home/ui/RecentVideosAdapter$RecentVideosViewHolder;", "imageLoader", "Lng/mathemandy/core/imageLoader/ImageLoader;", "(Lng/mathemandy/core/imageLoader/ImageLoader;)V", "clickListener", "Lkotlin/Function1;", "", "Lng/mathemandy/home/ui/RecentVideoClickListener;", "getClickListener", "()Lkotlin/jvm/functions/Function1;", "setClickListener", "(Lkotlin/jvm/functions/Function1;)V", "layoutId", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "swapData", "data", "", "(Ljava/util/List;Ljava/lang/Integer;)V", "RecentVideosDC", "RecentVideosViewHolder", "subjects_debug"})
public final class RecentVideosAdapter extends androidx.recyclerview.widget.ListAdapter<ng.mathemandy.model.LessonModel, ng.mathemandy.home.ui.RecentVideosAdapter.RecentVideosViewHolder> {
    private int layoutId = ng.mathemandy.home.R.layout.recent_videos;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super ng.mathemandy.model.LessonModel, kotlin.Unit> clickListener;
    private final ng.mathemandy.core.imageLoader.ImageLoader imageLoader = null;
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<ng.mathemandy.model.LessonModel, kotlin.Unit> getClickListener() {
        return null;
    }
    
    public final void setClickListener(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super ng.mathemandy.model.LessonModel, kotlin.Unit> p0) {
    }
    
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
    java.util.List<ng.mathemandy.model.LessonModel> data, @org.jetbrains.annotations.Nullable()
    java.lang.Integer layoutId) {
    }
    
    @javax.inject.Inject()
    public RecentVideosAdapter(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.core.imageLoader.ImageLoader imageLoader) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J2\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u001a\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000fj\u0004\u0018\u0001`\u00112\u0006\u0010\u0012\u001a\u00020\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0013"}, d2 = {"Lng/mathemandy/home/ui/RecentVideosAdapter$RecentVideosViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lng/mathemandy/home/ui/RecentVideosAdapter;Landroid/view/View;)V", "cardColor", "", "", "getCardColor", "()Ljava/util/List;", "bind", "", "imageLoader", "Lng/mathemandy/core/imageLoader/ImageLoader;", "clickListener", "Lkotlin/Function1;", "Lng/mathemandy/model/LessonModel;", "Lng/mathemandy/home/ui/RecentVideoClickListener;", "item", "subjects_debug"})
    public final class RecentVideosViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Integer> cardColor = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> getCardColor() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.core.imageLoader.ImageLoader imageLoader, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super ng.mathemandy.model.LessonModel, kotlin.Unit> clickListener, @org.jetbrains.annotations.NotNull()
        ng.mathemandy.model.LessonModel item) {
        }
        
        public RecentVideosViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lng/mathemandy/home/ui/RecentVideosAdapter$RecentVideosDC;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lng/mathemandy/model/LessonModel;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "subjects_debug"})
    static final class RecentVideosDC extends androidx.recyclerview.widget.DiffUtil.ItemCallback<ng.mathemandy.model.LessonModel> {
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.model.LessonModel oldItem, @org.jetbrains.annotations.NotNull()
        ng.mathemandy.model.LessonModel newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.model.LessonModel oldItem, @org.jetbrains.annotations.NotNull()
        ng.mathemandy.model.LessonModel newItem) {
            return false;
        }
        
        public RecentVideosDC() {
            super();
        }
    }
}