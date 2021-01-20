package ng.mathemandy.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class LessonAndSubject(

    @Relation(entityColumn = "sub_id", parentColumn = "subject_id")
    var subject: SubjectLocalModel,

    @Embedded
    var lesson: LessonLocalModel? = null
)
