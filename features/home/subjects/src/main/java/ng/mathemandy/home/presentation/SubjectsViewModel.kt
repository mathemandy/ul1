package ng.mathemandy.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ng.mathemandy.core.ext.errorMessage
import ng.mathemandy.domain.model.LessonAndSubject
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.domain.resource.AppResource
import ng.mathemandy.domain.resource.RepositoryResource
import ng.mathemandy.domain.resource.Status
import ng.mathemandy.domain.usecase.FetchRecentlyWatchedLessons
import ng.mathemandy.domain.usecase.FetchSubjects
import ng.mathemandy.model.SubjectModel
import ng.mathemandy.model.LessonAndSubjectModel
import ng.mathemandy.model.mapper.LessonAndSubjectModelMapper
import ng.mathemandy.model.mapper.SubjectModelMapper
import javax.inject.Inject

class SubjectsViewModel @Inject constructor(
    private val subjectModelMapper: SubjectModelMapper,
    private val fetchSubjectsUseCase: FetchSubjects,
    private val recentlyWatchedLessonsUseCase: FetchRecentlyWatchedLessons,
    private val lessonAndSubjectModel : LessonAndSubjectModelMapper
) : ViewModel() {


    private  var DEFAULT_LIMIT =  3

    private val _subjectsLiveData = MutableLiveData<AppResource<List<SubjectModel>>>()
    val subjectsLiveData = _subjectsLiveData as LiveData<AppResource<List<SubjectModel>>>


    private val _recentTopicsLiveData = MutableLiveData<AppResource<List<LessonAndSubjectModel>>>()
    val recentTopicsLiveData =
        _recentTopicsLiveData as LiveData<AppResource<List<LessonAndSubjectModel>>>


    private  val _viewMoreLiveData  = MutableLiveData<Boolean>(false)
    val viewMoreLiveData  =  _viewMoreLiveData as  LiveData<Boolean>



    private val subjects: Flow<RepositoryResource<List<Subject>>>
        get() = fetchSubjectsUseCase()

    private val recentlyWatchedLessons: Flow<List<LessonAndSubject>>
        get() = recentlyWatchedLessonsUseCase(DEFAULT_LIMIT)

    init {
        getSubjects()
        getRecentlyWatchedLessons()

    }

    private fun getSubjects() {
        viewModelScope.launch {
            subjects.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data?.isNotEmpty() == true) {
                            _subjectsLiveData.postValue(
                                AppResource.success(
                                    subjectModelMapper.mapToModelList(
                                        it.data!!
                                    )
                                )
                            )
                        } else {
                            _subjectsLiveData.postValue(AppResource.empty())
                        }
                    }
                    Status.ERROR -> {
                        if (it.data?.isNotEmpty() == true) {
                            _subjectsLiveData.postValue(
                                AppResource.offline(
                                    subjectModelMapper.mapToModelList(
                                        it.data!!
                                    ), it.cause?.errorMessage
                                )
                            )
                        } else {
                            _subjectsLiveData.postValue(AppResource.failed(it.cause?.errorMessage))

                        }

                    }
                    Status.LOADING -> {
                        if (it.data?.isNotEmpty() == true) {
                            _subjectsLiveData.postValue(
                                AppResource.loadingWithInitialData(
                                    subjectModelMapper.mapToModelList(
                                        it.data!!
                                    )
                                )
                            )
                        } else {
                            _subjectsLiveData.postValue(AppResource.loading())
                        }
                    }
                }
            }
        }
    }

    private fun getRecentlyWatchedLessons() {
        viewModelScope.launch {
            recentlyWatchedLessons.onStart {
                _recentTopicsLiveData.postValue(AppResource.loading())

            }.catch { cause: Throwable ->
                _recentTopicsLiveData.postValue(AppResource.failed(cause.errorMessage))

            }.collect {

                if (it.isEmpty()) {
                    _recentTopicsLiveData.postValue(AppResource.empty())
                    _viewMoreLiveData.postValue(false)
                } else {
                    _viewMoreLiveData.postValue(true)
                    _recentTopicsLiveData.postValue(AppResource.success(lessonAndSubjectModel.mapToModelList(it)))
                }
            }
        }
    }

    fun viewMoreClicked(showingViewMore  : Boolean){
        DEFAULT_LIMIT = if(showingViewMore){
            3
        }else {
            -1
        }

        getRecentlyWatchedLessons()
    }
}