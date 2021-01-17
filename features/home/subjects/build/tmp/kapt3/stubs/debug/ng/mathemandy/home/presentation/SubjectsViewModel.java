package ng.mathemandy.home.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0015H\u0002R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\n0\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0016"}, d2 = {"Lng/mathemandy/home/presentation/SubjectsViewModel;", "Landroidx/lifecycle/ViewModel;", "subjectModelMapper", "Lng/mathemandy/model/mapper/SubjectModelMapper;", "fetchSubjectsUseCase", "Lng/mathemandy/domain/usecase/FetchSubjects;", "(Lng/mathemandy/model/mapper/SubjectModelMapper;Lng/mathemandy/domain/usecase/FetchSubjects;)V", "_subjectsLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lng/mathemandy/domain/resource/AppResource;", "", "Lng/mathemandy/model/SubjectModel;", "subjects", "Lkotlinx/coroutines/flow/Flow;", "Lng/mathemandy/domain/model/Subject;", "getSubjects", "()Lkotlinx/coroutines/flow/Flow;", "subjectsLiveData", "Landroidx/lifecycle/LiveData;", "getSubjectsLiveData", "()Landroidx/lifecycle/LiveData;", "", "subjects_debug"})
public final class SubjectsViewModel extends androidx.lifecycle.ViewModel {
    private final androidx.lifecycle.MutableLiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> _subjectsLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> subjectsLiveData = null;
    private final ng.mathemandy.model.mapper.SubjectModelMapper subjectModelMapper = null;
    private final ng.mathemandy.domain.usecase.FetchSubjects fetchSubjectsUseCase = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<ng.mathemandy.domain.resource.AppResource<java.util.List<ng.mathemandy.model.SubjectModel>>> getSubjectsLiveData() {
        return null;
    }
    
    private final kotlinx.coroutines.flow.Flow<java.util.List<ng.mathemandy.domain.model.Subject>> getSubjects() {
        return null;
    }
    
    private final void getSubjects() {
    }
    
    @javax.inject.Inject()
    public SubjectsViewModel(@org.jetbrains.annotations.NotNull()
    ng.mathemandy.model.mapper.SubjectModelMapper subjectModelMapper, @org.jetbrains.annotations.NotNull()
    ng.mathemandy.domain.usecase.FetchSubjects fetchSubjectsUseCase) {
        super();
    }
}