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
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.appbar, 1);
        sViewsWithIds.put(R.id.collapsingToolbar, 2);
        sViewsWithIds.put(R.id.toolbar, 3);
        sViewsWithIds.put(R.id.parent_scroll, 4);
        sViewsWithIds.put(R.id.subjectContainer, 5);
        sViewsWithIds.put(R.id.guideline_start, 6);
        sViewsWithIds.put(R.id.guideline_end, 7);
        sViewsWithIds.put(R.id.subjectsFlipper, 8);
        sViewsWithIds.put(R.id.subject_rv, 9);
        sViewsWithIds.put(R.id.recentlyWatchedVideos, 10);
        sViewsWithIds.put(R.id.recentHeader, 11);
        sViewsWithIds.put(R.id.guideline_start_recent, 12);
        sViewsWithIds.put(R.id.guideline_end_recent, 13);
        sViewsWithIds.put(R.id.recently_watched_rv, 14);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSubjectsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentSubjectsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[1]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[2]
            , (androidx.constraintlayout.widget.Guideline) bindings[7]
            , (androidx.constraintlayout.widget.Guideline) bindings[13]
            , (androidx.constraintlayout.widget.Guideline) bindings[6]
            , (androidx.constraintlayout.widget.Guideline) bindings[12]
            , (androidx.core.widget.NestedScrollView) bindings[4]
            , (android.widget.TextView) bindings[11]
            , (androidx.recyclerview.widget.RecyclerView) bindings[14]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[10]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.recyclerview.widget.RecyclerView) bindings[9]
            , (android.widget.ViewFlipper) bindings[8]
            , (androidx.appcompat.widget.Toolbar) bindings[3]
            );
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}