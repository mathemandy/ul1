package ng.mathemandy.remote.impl

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import ng.mathemandy.data.contract.remote.SubjectsRemote
import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.remote.mapper.ChapterRemoteMapper
import ng.mathemandy.remote.mapper.LessonRemoteMapper
import ng.mathemandy.remote.mapper.SubjectRemoteMapper
import ng.mathemandy.remote.utils.REQUEST_PATH
import ng.mathemandy.remote.utils.RequestDispatcher
import ng.mathemandy.remote.utils.makeTestApiService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class SubjectRemoteTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var subjectRemote: SubjectsRemote
    private val subjectRemoteMapper = SubjectRemoteMapper(ChapterRemoteMapper(LessonRemoteMapper()))

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        subjectRemote = SubjectRemoteImpl(makeTestApiService(mockWebServer), subjectRemoteMapper)
    }

    @Test
    fun `check that calling fetchSubjects returns subjects list`() = runBlocking {
        val subjects: List<SubjectEntity> = subjectRemote.fetchSubjects()
        assertThat(subjects).isNotEmpty()
    }

    @Test
    fun `check that calling fetchSubjects returns correct data`() = runBlocking {
        val subjects: List<SubjectEntity> = subjectRemote.fetchSubjects()
        assertThat(subjects.first().name).isEqualTo("Mathematics")
    }

    @Test
    fun `check that calling fetchRecipes makes request to given path`() = runBlocking {
        subjectRemote.fetchSubjects()
        assertThat(REQUEST_PATH).isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling fetchRecipes makes a GET request`() = runBlocking {
        subjectRemote.fetchSubjects()
        assertThat("GET $REQUEST_PATH HTTP/1.1").isEqualTo(mockWebServer.takeRequest().requestLine)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
