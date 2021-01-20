package ng.mathemandy.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import ng.mathemandy.data.fake.DummyData
import ng.mathemandy.data.fake.FakeErrorSubjectRemote
import ng.mathemandy.data.fake.FakeSubjectLocalImpl
import ng.mathemandy.data.fake.FakeSubjectRemoteImpl
import ng.mathemandy.data.mapper.ChapterEntityMapper
import ng.mathemandy.data.mapper.LessonEntityMapper
import ng.mathemandy.data.mapper.SubjectEntityMapper
import ng.mathemandy.domain.resource.Status
import org.junit.Test

class SubjectRepositoryImplTest {

    private val subjectMapper =
        SubjectEntityMapper(ChapterEntityMapper(LessonEntityMapper()))

    private  val fakeCache  = FakeSubjectLocalImpl()
    private val subjectRepository =
        SubjectRepositoryImpl(FakeSubjectRemoteImpl(), fakeCache, subjectMapper)

    @Test
    fun `check that fetchSubjects fetches data from remote, saves to DB and returns data from Local always`() = runBlockingTest {
        subjectRepository.fetchSubjects().test {
            assertThat(expectItem().status).isEqualTo(Status.LOADING)
            assertThat(expectItem().status).isEqualTo(Status.LOADING)
            assertThat(expectItem().data).isNotEmpty()
            expectComplete()
        }
    }

    @Test
    fun `check that fetchSubjects fetches always returns data from DB `() = runBlockingTest {
        val subjectEntity = DummyData.subjectEntity
        fakeCache.saveSubject(subjectEntity)
        subjectRepository.fetchSubjects().test {
            assertThat(expectItem().status).isEqualTo(Status.LOADING)
            val  resource = expectItem()
            val status = resource.status
            val localData = resource.data
            assertThat(status).isEqualTo(Status.LOADING)
            assertThat(localData?.first()).isEqualTo(subjectMapper.mapFromEntity(subjectEntity))
            assertThat(expectItem().data).isNotEmpty()
            expectComplete()
        }
    }

    @Test
    fun `check that fetchSubjects fetches always returns data from DB First when network Fails `() = runBlockingTest {
         val subjectRepository =
            SubjectRepositoryImpl(FakeErrorSubjectRemote(), fakeCache, subjectMapper)

        val subjectEntity = DummyData.subjectEntity
        fakeCache.saveSubject(subjectEntity)
        subjectRepository.fetchSubjects().test {
            assertThat(expectItem().status).isEqualTo(Status.LOADING)
            val  resource = expectItem()
            val status = resource.status
            val localData = resource.data
            assertThat(status).isEqualTo(Status.LOADING)
            assertThat(localData?.first()).isEqualTo(subjectMapper.mapFromEntity(subjectEntity))

            val  remoteResource = expectItem()
            val remoteStatus = remoteResource.status
            val remoteData = remoteResource.data
            val throwable  = remoteResource.cause

            assertThat(remoteStatus).isEqualTo(Status.ERROR)
            assertThat(throwable).hasMessageThat().isEqualTo(FakeErrorSubjectRemote.ERROR)
            assertThat(remoteData).isNotEmpty()
            expectComplete()
        }
    }


}