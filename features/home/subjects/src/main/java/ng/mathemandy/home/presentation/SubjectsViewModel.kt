package ng.mathemandy.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ng.mathemandy.core.ext.errorMessage
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.domain.resource.AppResource
import ng.mathemandy.domain.usecase.FetchSubjects
import ng.mathemandy.model.SubjectModel
import ng.mathemandy.model.mapper.SubjectModelMapper
import javax.inject.Inject

class SubjectsViewModel @Inject constructor(
    private val subjectModelMapper: SubjectModelMapper,
    private val fetchSubjectsUseCase: FetchSubjects
) : ViewModel() {

    private val _subjectsLiveData = MutableLiveData<AppResource<List<SubjectModel>>>()
    val subjectsLiveData = _subjectsLiveData as LiveData<AppResource<List<SubjectModel>>>

    private val subjects: Flow<List<Subject>>
        get() = fetchSubjectsUseCase()


    init {
        getSubjects()
    }

    private fun getSubjects() {
        viewModelScope.launch {
            subjects.onStart {
                _subjectsLiveData.postValue(AppResource.loading())

            }.catch { cause: Throwable ->
                _subjectsLiveData.postValue(AppResource.failed(cause.errorMessage))

            }.collect {
                _subjectsLiveData.postValue(AppResource.success(subjectModelMapper.mapToModelList(it)))

            }

        }


    }
}