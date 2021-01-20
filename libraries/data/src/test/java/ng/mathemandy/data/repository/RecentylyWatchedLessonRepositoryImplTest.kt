package ng.mathemandy.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import ng.mathemandy.data.fake.DummyData
import ng.mathemandy.data.fake.FakeLocalAndSubjectLocal
import ng.mathemandy.data.mapper.ChapterEntityMapper
import ng.mathemandy.data.mapper.LessonAndSubjectEntityMapper
import ng.mathemandy.data.mapper.LessonEntityMapper
import ng.mathemandy.data.mapper.SubjectEntityMapper
import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.model.LessonAndSubject
import org.junit.Test

class RecentylyWatchedLessonRepositoryImplTest () {

    private  val lessonEntityMapper  = LessonEntityMapper()
    private  val subjectEntityMapper  = SubjectEntityMapper(ChapterEntityMapper(lessonEntityMapper))

    private val recentlyWatchedLessonRepository  = RecentlyWatchedLessonRepositoryImpl(
        FakeLocalAndSubjectLocal() ,lessonEntityMapper,  LessonAndSubjectEntityMapper(lessonEntityMapper, subjectEntityMapper) )


    @Test
    fun saveLesson() = runBlockingTest {
        val lesson: Lesson = DummyData.lesson
        recentlyWatchedLessonRepository.saveRecentlyWatchedLesson(lesson).test {
            expectEvent()
            expectComplete()
        }

        val result: LessonAndSubject = recentlyWatchedLessonRepository.fetchRecentlyWatchedLessons(-1).first().first()
        assertThat(lesson).isEqualTo(result.lesson)
    }

    @Test
    fun `check that fetchRecentlyWatchedLessons returns all Lessons`() = runBlockingTest {
        val lesson: Lesson = DummyData.lesson
        val lesson1: Lesson = DummyData.lesson1

        recentlyWatchedLessonRepository.saveRecentlyWatchedLesson(lesson).test {
            expectEvent()
            expectComplete()
        }

        recentlyWatchedLessonRepository.saveRecentlyWatchedLesson(lesson1).test {
            expectEvent()
            expectComplete()
        }

        val allItems: List<LessonAndSubject> = listOf(LessonAndSubject(DummyData.subject, lesson), LessonAndSubject(DummyData.subject1, lesson1))
        val result: List<LessonAndSubject> = recentlyWatchedLessonRepository.fetchRecentlyWatchedLessons(-1).first()
        assertThat(allItems).containsExactlyElementsIn(result).inOrder()
    }


}