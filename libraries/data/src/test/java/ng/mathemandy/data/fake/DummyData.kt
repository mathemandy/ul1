package ng.mathemandy.data.fake

import ng.mathemandy.data.model.ChapterEntity
import ng.mathemandy.data.model.LessonEntity
import ng.mathemandy.data.model.SubjectEntity
import ng.mathemandy.domain.model.Chapter
import ng.mathemandy.domain.model.Lesson
import ng.mathemandy.domain.model.Subject

internal object DummyData {
    val subjectEntity = SubjectEntity(
        id = 2,
        name = "Mathematics",
        icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/subject_icons\\/icons\\/2\\/thumb\\/thumb.jpeg",
        chapters = listOf(chapterEntity),
    )

    val subjectEntity2 = SubjectEntity(
        id = 85,
        name = "English",
        icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/subject_icons\\/icons\\/defaults\\/thumb\\/subject.png",
        chapters = listOf(chapterEntity1),
    )

    val chapterEntity: ChapterEntity
        get() = ChapterEntity(
            id = 359,
            name = "Prose",
            lessons = listOf(lessonEntity)
        )

    val chapterEntity1: ChapterEntity
        get() = ChapterEntity(
            id = 151,
            name = "Exam Test",
            lessons = listOf(lessonEntity1)
        )

    val lessonEntity: LessonEntity
        get() = LessonEntity(
            id = 1168,
            name = "dsfjdfkj",
            icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/lesson_icons\\/icons\\/defaults\\/thumb\\/lesson.png",
            media_url = "https:\\/\\/d2zjjckqo1cait.cloudfront.net\\/free_videos\\/1\\/original\\/stapler-FuXgvD.mp4",
            subject_id = 2,
            chapter_id = 151
        )

    val lessonEntity1: LessonEntity
        get() = LessonEntity(
            id = 3118,
            name = "vvvffg",
            icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/lesson_icons\\/icons\\/defaults\\/thumb\\/lesson.png",
            media_url = "https:\\/\\/d2zjjckqo1cait.cloudfront.net\\/free_videos\\/70\\/original\\/stapler-bRXerd.MP4",
            subject_id = 85,
            chapter_id = 359
        )

    val subject = Subject(
        id = 2,
        name = "Mathematics",
        icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/subject_icons\\/icons\\/2\\/thumb\\/thumb.jpeg",
        chapters = listOf(chapter),
    )

    val subject1 = Subject(
        id = 85,
        name = "English",
        icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/subject_icons\\/icons\\/defaults\\/thumb\\/subject.png",
        chapters = listOf(chapter1),

    )

    val chapter: Chapter
        get() = Chapter(
            id = 359,
            name = "Prose",
            lessons = listOf(lesson)
        )
    val chapter1: Chapter
        get() = Chapter(
            id = 151,
            name = "Exam Test",
            lessons = listOf(lesson1)
        )

    val lesson: Lesson
        get() = Lesson(
            id = 1168,
            name = "dsfjdfkj",
            icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/lesson_icons\\/icons\\/defaults\\/thumb\\/lesson.png",
            media_url = "https:\\/\\/d2zjjckqo1cait.cloudfront.net\\/free_videos\\/1\\/original\\/stapler-FuXgvD.mp4",
            subject_id = 2,
            chapter_id = 151
        )
    val lesson1: Lesson
        get() = Lesson(
            id = 3118,
            name = "vvvffg",
            icon = "https:\\/\\/ulesson-staging.s3.eu-west-2.amazonaws.com\\/lesson_icons\\/icons\\/defaults\\/thumb\\/lesson.png",
            media_url = "https:\\/\\/d2zjjckqo1cait.cloudfront.net\\/free_videos\\/70\\/original\\/stapler-bRXerd.MP4",
            subject_id = 85,
            chapter_id = 359
        )
}
