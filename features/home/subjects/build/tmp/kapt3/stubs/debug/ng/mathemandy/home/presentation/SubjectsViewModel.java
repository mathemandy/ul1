package ng.mathemandy.home.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u001d\u001a\u00020\'H\u0002J\b\u0010\"\u001a\u00020\'H\u0002J\u000e\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020\u0015R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R \u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00100\u001b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00100 0\u001b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001eR#\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100\u000f0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019\u00a8\u0006*"}, d2 = {"Lng/mathemandy/home/presentation/SubjectsViewModel;", "Landroidx/lifecycle/ViewModel;", "subjectModelMapper", "Lng/mathemandy/model/mapper/SubjectModelMapper;", "fetchSubjectsUseCase", "Lng/mathemandy/domain/usecase/FetchSubjects;", "recentlyWatchedLessonsUseCase", "Lng/mathemandy/domain/usecase/FetchRecentlyWatchedLessons;", "lessonAndSubjectModel", "Lng/mathemandy/model/mapper/LessonAndSubjectModelMapper;", "(Lng/mathemandy/model/mapper/SubjectModelMapper;Lng/mathemandy/domain/usecase/FetchSubjects;Lng/mathemandy/domain/usecase/FetchRecentlyWatchedLessons;Lng/mathemandy/model/mapper/LessonAndSubjectModelMapper;)V", "DEFAULT_LIMIT", "", "_recentTopicsLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lng/mathemandy/domain/resource/AppResource;", "", "Lng/mathemandy/model/LessonAndSubjectModel;", "_subjectsLiveData", "Lng/mathemandy/model/SubjectModel;", "_viewMoreLiveData", "", "recentTopicsLiveData", "Landroidx/lifecycle/LiveData;", "getRecentTopicsLiveData", "()Landroidx/lifecycle/LiveData;", "recentlyWatchedLessons", "Lkotlinx/coroutines/flow/Flow;", "Lng/mathemandy/domain/model/LessonAndSubject;", "getRecentlyWatchedLessons", "()Lkotlinx/coroutines/flow/Flow;", "subjects", "Lng/mathemandy/domain/resource/RepositoryResource;", "Lng/mathemandy/domain/model/Subject;", "getSubjects", "subjectsLiveData", "getSubjectsLiveData", "viewMoreLiveData", "getViewMoreLiveData", "", "viewMoreClicked", "showingViewMore", "subjects_debug"})
public final class SubjectsViewModel extends androidx.lifecycle.ViewModel {
    private int DEFAULT_LIMIT = 3;
    private final androidx.lifecycle.MutableLiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> _subjectsLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> subjectsLiveData = null;
    private final androidx.lifecycle.MutableLiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.LessonAndSubjectModel>>> _recentTopicsLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.LessonAndSubjectModel>>> recentTopicsLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _viewMoreLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> viewMoreLiveData = null;
    private final ng.mathemandy.model.mapper.SubjectModelMapper subjectModelMapper = null;
    private final ng.mathemandy.domain.usecase.FetchSubjects fetchSubjectsUseCase = null;
    private final ng.mathemandy.domain.usecase.FetchRecentlyWatchedLessons recentlyWatchedLessonsUseCase = null;
    private final ng.mathemandy.model.mapper.LessonAndSubjectModelMapper lessonAndSubjectModel = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> getSubjectsLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.LessonAndSubjectModel>>> getRecentTopicsLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getViewMoreLiveData() {
        return null;
    }
    
    private final kotlinx.coroutines.flow.Flow<ng.mathemandy.domain.resource.RepositoryResource<java.util.List<ng.mathemandy.domain.model.Subject>>> getSubjects() {
        return null;
    }
    
    private final kotlinx.coroutines.flow.Flow<java.util.List<ng.mathemandy.domain.model.LessonAndSubject>> getRecentlyWatchedLessons() {
        return null;
    }
    
    private final void getSubjects() {
    }
    
    private final void getRecentlyWatchedLessons() {
    }
    
    public final void viewMoreClicked(boolean showingViewMore) {
    }
    
    @javax.inject.Inject()
    public SubjectsViewModel(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.model.mapper.SubjectModelMapper subjectModelMapper, @org.jetbrains.annotations.NotNull()
    ng.mathemandy.domain.usecase.FetchSubjects fetchSubjectsUseCase, @org.jetbrains.annotations.NotNull()
    ng.mathemandy.domain.usecase.FetchRecentlyWatchedLessons recentlyWatchedLessonsUseCase, @org.jetbrains.annotations.NotNull()
    ng.mathemandy.model.mapper.LessonAndSubjectModelMapper lessonAndSubjectModel) {
        super();
    }
}