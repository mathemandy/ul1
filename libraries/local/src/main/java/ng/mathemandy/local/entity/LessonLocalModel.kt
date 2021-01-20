package ng.mathemandy.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lessons")
data class LessonLocalModel(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val icon: String = "",
    val media_url: String = "",
    val subject_id: Int = 0,
    val chapter_id: Int = 0,
) {
    var lastUpdated: Long = 0L
}
