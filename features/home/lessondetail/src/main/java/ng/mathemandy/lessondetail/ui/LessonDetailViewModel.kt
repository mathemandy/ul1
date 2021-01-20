package ng.mathemandy.lessondetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ng.mathemandy.domain.usecase.SaveRecentlyWatchedLesson
import ng.mathemandy.model.LessonModel
import ng.mathemandy.model.mapper.LessonModelMapper
import javax.inject.Inject

class LessonDetailViewModel @Inject constructor(
    private val lessonModelMapper: LessonModelMapper,
    private val saveRecentlyWatchedLesson: SaveRecentlyWatchedLesson
) : ViewModel() {

    fun saveLesson(lesson: LessonModel) {
        viewModelScope.launch {
            saveRecentlyWatchedLesson(lessonModelMapper.mapToDomain(lesson)).collect()
        }
    }
}
