// Generated by data binding compiler. Do not edit!
package ng.mathemandy.home.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import java.lang.Deprecated;
import java.lang.Object;
import ng.mathemandy.home.R;

public abstract class FragmentSubjectsBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final CollapsingToolbarLayout collapsingToolbar;

  @NonNull
  public final Guideline guidelineEnd;

  @NonNull
  public final Guideline guidelineEndRecent;

  @NonNull
  public final Guideline guidelineStart;

  @NonNull
  public final Guideline guidelineStartRecent;

  @NonNull
  public final NestedScrollView parentScroll;

  @NonNull
  public final TextView recentHeader;

  @NonNull
  public final RecyclerView recentlyWatchedRv;

  @NonNull
  public final ConstraintLayout recentlyWatchedVideos;

  @NonNull
  public final ConstraintLayout subjectContainer;

  @NonNull
  public final RecyclerView subjectRv;

  @NonNull
  public final Toolbar toolbar;

  protected FragmentSubjectsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppBarLayout appbar, CollapsingToolbarLayout collapsingToolbar, Guideline guidelineEnd,
      Guideline guidelineEndRecent, Guideline guidelineStart, Guideline guidelineStartRecent,
      NestedScrollView parentScroll, TextView recentHeader, RecyclerView recentlyWatchedRv,
      ConstraintLayout recentlyWatchedVideos, ConstraintLayout subjectContainer,
      RecyclerView subjectRv, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appbar = appbar;
    this.collapsingToolbar = collapsingToolbar;
    this.guidelineEnd = guidelineEnd;
    this.guidelineEndRecent = guidelineEndRecent;
    this.guidelineStart = guidelineStart;
    this.guidelineStartRecent = guidelineStartRecent;
    this.parentScroll = parentScroll;
    this.recentHeader = recentHeader;
    this.recentlyWatchedRv = recentlyWatchedRv;
    this.recentlyWatchedVideos = recentlyWatchedVideos;
    this.subjectContainer = subjectContainer;
    this.subjectRv = subjectRv;
    this.toolbar = toolbar;
  }

  @NonNull
  public static FragmentSubjectsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_subjects, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSubjectsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentSubjectsBinding>inflateInternal(inflater, R.layout.fragment_subjects, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentSubjectsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_subjects, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSubjectsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentSubjectsBinding>inflateInternal(inflater, R.layout.fragment_subjects, null, false, component);
  }

  public static FragmentSubjectsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentSubjectsBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentSubjectsBinding)bind(component, view, R.layout.fragment_subjects);
  }
}
