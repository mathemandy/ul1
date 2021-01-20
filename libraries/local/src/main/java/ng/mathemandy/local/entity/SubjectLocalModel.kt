package ng.mathemandy.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Subjects")
data class SubjectLocalModel(
    @ColumnInfo(name = "sub_id")
    @PrimaryKey
    val id: Int = 0,
    val name : String = "",
    val icon: String = "",
    val chapters: List<ChapterLocalModel>? = emptyList(),
)
