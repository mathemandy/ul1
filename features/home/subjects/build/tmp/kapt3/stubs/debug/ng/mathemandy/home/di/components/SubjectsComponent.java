package ng.mathemandy.home.di.components;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lng/mathemandy/home/di/components/SubjectsComponent;", "", "inject", "", "recipeFragment", "Lng/mathemandy/home/ui/SubjectsFragment;", "Factory", "subjects_debug"})
@dagger.Component(dependencies = {ng.mathemandy.core.di.components.CoreComponent.class, ng.mathemandy.ulesson.di.AppComponent.class}, modules = {ng.mathemandy.core.di.module.FactoryModule.class, ng.mathemandy.home.di.module.ViewModelModule.class})
@ng.mathemandy.core.di.scope.FeatureScope()
public abstract interface SubjectsComponent {
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.home.ui.SubjectsFragment recipeFragment);
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lng/mathemandy/home/di/components/SubjectsComponent$Factory;", "", "create", "Lng/mathemandy/home/di/components/SubjectsComponent;", "coreComponent", "Lng/mathemandy/core/di/components/CoreComponent;", "appComponent", "Lng/mathemandy/ulesson/di/AppComponent;", "subjects_debug"})
    @dagger.Component.Factory()
    public static abstract interface Factory {
        
        @org.jetbrains.annotations.NotNull()
        public abstract ng.mathemandy.home.di.components.SubjectsComponent create(@org.jetbrains.annotations.NotNull()
        ng.mathemandy.core.di.components.CoreComponent coreComponent, @org.jetbrains.annotations.NotNull()
        ng.mathemandy.ulesson.di.AppComponent appComponent);
    }
}