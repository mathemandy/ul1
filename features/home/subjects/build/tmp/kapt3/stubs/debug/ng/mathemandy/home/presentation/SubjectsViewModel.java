package ng.mathemandy.home.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0019\u001a\u00020!H\u0002J\b\u0010\u001e\u001a\u00020!H\u0002R \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000e0\u00178BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000e0\u001c0\u00178BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001aR#\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000e0\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015\u00a8\u0006\""}, d2 = {"Lng/mathemandy/home/presentation/SubjectsViewModel;", "Landroidx/lifecycle/ViewModel;", "subjectModelMapper", "Lng/mathemandy/model/mapper/SubjectModelMapper;", "fetchSubjectsUseCase", "Lng/mathemandy/domain/usecase/FetchSubjects;", "recentlyWatchedLessonsUseCase", "Lng/mathemandy/domain/usecase/FetchRecentlyWatchedLessons;", "lessonAndSubjectModel", "Lng/mathemandy/model/mapper/LessonAndSubjectModelMapper;", "(Lng/mathemandy/model/mapper/SubjectModelMapper;Lng/mathemandy/domain/usecase/FetchSubjects;Lng/mathemandy/domain/usecase/FetchRecentlyWatchedLessons;Lng/mathemandy/model/mapper/LessonAndSubjectModelMapper;)V", "_recentTopicsLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lng/mathemandy/domain/resource/AppResource;", "", "Lng/mathemandy/model/LessonAndSubjectModel;", "_subjectsLiveData", "Lng/mathemandy/model/SubjectModel;", "recentTopicsLiveData", "Landroidx/lifecycle/LiveData;", "getRecentTopicsLiveData", "()Landroidx/lifecycle/LiveData;", "recentlyWatchedLessons", "Lkotlinx/coroutines/flow/Flow;", "Lng/mathemandy/domain/model/LessonAndSubject;", "getRecentlyWatchedLessons", "()Lkotlinx/coroutines/flow/Flow;", "subjects", "Lng/mathemandy/domain/resource/RepositoryResource;", "Lng/mathemandy/domain/model/Subject;", "getSubjects", "subjectsLiveData", "getSubjectsLiveData", "", "subjects_debug"})
public final class SubjectsViewModel extends androidx.lifecycle.ViewModel {
    private final androidx.lifecycle.MutableLiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> _subjectsLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> subjectsLiveData = null;
    private final androidx.lifecycle.MutableLiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.LessonAndSubjectModel>>> _recentTopicsLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.LessonAndSubjectModel>>> recentTopicsLiveData = null;
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
    
    @javax.inject.Inject()
    public SubjectsViewModel(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.model.mapper.SubjectModelMapper subjectModelMapper, @org.jetbrains.annotations.NotNull()
    ng.mathemandy.domain.usecase.FetchSubjects fetchSubjectsUseCase, @org.jetbrains.annotations.NotNull()
    ng.mathemandy.domain.usecase.FetchRecentlyWatchedLessons recentlyWatchedLessonsUseCase, @org.jetbrains.annotations.NotNull()
    ng.mathemandy.model.mapper.LessonAndSubjectModelMapper lessonAndSubjectModel) {
        super();
    }
}