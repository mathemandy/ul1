package ng.mathemandy.home.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lng/mathemandy/home/di/module/ViewModelModule;", "", "subjectsViewModel", "Landroidx/lifecycle/ViewModel;", "Lng/mathemandy/home/presentation/SubjectsViewModel;", "getSubjectsViewModel", "(Lng/mathemandy/home/presentation/SubjectsViewModel;)Landroidx/lifecycle/ViewModel;", "subjects_debug"})
@dagger.Module()
@dagger.hilt.migration.DisableInstallInCheck()
public abstract interface ViewModelModule {
    
    @org.jetbrains.annotations.NotNull()
    @ng.mathemandy.core.di.mapkeys.ViewModelKey(value = ng.mathemandy.home.presentation.SubjectsViewModel.class)
    @dagger.multibindings.IntoMap()
    @dagger.Binds()
    public abstract androidx.lifecycle.ViewModel getSubjectsViewModel(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.home.presentation.SubjectsViewModel $this$subjectsViewModel);
}