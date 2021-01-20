package ng.mathemandy.home.databinding;
import ng.mathemandy.home.R;
import ng.mathemandy.home.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class FragmentSubjectsBindingImpl extends FragmentSubjectsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(20);
        sIncludes.setIncludes(1, 
            new String[] {"error_content_layout"},
            new int[] {5},
            new int[] {ng.mathemandy.core.R.layout.error_content_layout});
        sIncludes.setIncludes(2, 
            new String[] {"error_content_layout"},
            new int[] {6},
            new int[] {ng.mathemandy.core.R.layout.error_content_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.empty_content_Layout_home, 3);
        sViewsWithIds.put(R.id.empty_content_Layout_recentlyWatched, 4);
        sViewsWithIds.put(R.id.appbar, 7);
        sViewsWithIds.put(R.id.collapsingToolbar, 8);
        sViewsWithIds.put(R.id.toolbar, 9);
        sViewsWithIds.put(R.id.parent_scroll, 10);
        sViewsWithIds.put(R.id.subjectContainer, 11);
        sViewsWithIds.put(R.id.guideline_start, 12);
        sViewsWithIds.put(R.id.guideline_end, 13);
        sViewsWithIds.put(R.id.subject_rv, 14);
        sViewsWithIds.put(R.id.recentlyWatchedVideos, 15);
        sViewsWithIds.put(R.id.recentHeader, 16);
        sViewsWithIds.put(R.id.guideline_start_recent, 17);
        sViewsWithIds.put(R.id.guideline_end_recent, 18);
        sViewsWithIds.put(R.id.recently_watched_rv, 19);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSubjectsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }
    private FragmentSubjectsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.google.android.material.appbar.AppBarLayout) bindings[7]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[8]
            , (android.view.View) bindings[3]
            , (android.view.View) bindings[4]
            , (ng.mathemandy.core.databinding.ErrorContentLayoutBinding) bindings[5]
            , (ng.mathemandy.core.databinding.ErrorContentLayoutBinding) bindings[6]
            , (androidx.constraintlayout.widget.Guideline) bindings[13]
            , (androidx.constraintlayout.widget.Guideline) bindings[18]
            , (androidx.constraintlayout.widget.Guideline) bindings[12]
            , (androidx.constraintlayout.widget.Guideline) bindings[17]
            , (androidx.core.widget.NestedScrollView) bindings[10]
            , (android.widget.TextView) bindings[16]
            , (android.widget.ViewFlipper) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[19]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[15]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[11]
            , (androidx.recyclerview.widget.RecyclerView) bindings[14]
            , (android.widget.ViewFlipper) bindings[1]
            , (androidx.appcompat.widget.Toolbar) bindings[9]
            );
        setContainedBinding(this.errorContentLayoutHome);
        setContainedBinding(this.errorContentLayoutRecentlyWatched);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recentlyWatchedFlipper.setTag(null);
        this.subjectsFlipper.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        errorContentLayoutHome.invalidateAll();
        errorContentLayoutRecentlyWatched.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (errorContentLayoutHome.hasPendingBindings()) {
            return true;
        }
        if (errorContentLayoutRecentlyWatched.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        errorContentLayoutHome.setLifecycleOwner(lifecycleOwner);
        errorContentLayoutRecentlyWatched.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeErrorContentLayoutRecentlyWatched((ng.mathemandy.core.databinding.ErrorContentLayoutBinding) object, fieldId);
            case 1 :
                return onChangeErrorContentLayoutHome((ng.mathemandy.core.databinding.ErrorContentLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeErrorContentLayoutRecentlyWatched(ng.mathemandy.core.databinding.ErrorContentLayoutBinding ErrorContentLayoutRecentlyWatched, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeErrorContentLayoutHome(ng.mathemandy.core.databinding.ErrorContentLayoutBinding ErrorContentLayoutHome, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(errorContentLayoutHome);
        executeBindingsOn(errorContentLayoutRecentlyWatched);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): errorContentLayoutRecentlyWatched
        flag 1 (0x2L): errorContentLayoutHome
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}