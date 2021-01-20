package ng.mathemandy.remote

import ng.mathemandy.remote.model.ChapterRemoteModel
import ng.mathemandy.remote.model.LessonRemoteModel
import ng.mathemandy.remote.model.SubjectRemoteModel

internal object DummyData {

    val characterRemoteModel = SubjectRemoteModel(
        85,
        "English",
        "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/subject_icons\\/icons\\/defaults\\/thumb\\/subject.png",
        listOf(chapterRemoteModel),

    )

    val chapterRemoteModel: ChapterRemoteModel
        get() = ChapterRemoteModel(
            id = 359,
            name = "Prose",
            lessons = listOf(lessonRemoteModel)
        )

    val lessonRemoteModel: LessonRemoteModel
        get() = LessonRemoteModel(
            id = 1168,
            name = "dsfjdfkj",
            icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/lesson_icons\\/icons\\/defaults\\/thumb\\/lesson.png",
            media_url = "https:\\/\\/d2zjjckqo1cait.cloudfront.net\\/free_videos\\/1\\/original\\/stapler-FuXgvD.mp4",
            subject_id = 2,
            chapter_id = 151
        )
}
