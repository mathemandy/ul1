package ng.mathemandy.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ng.mathemandy.local.BuildConfig
import ng.mathemandy.local.entity.LessonAndSubject
import ng.mathemandy.local.entity.LessonLocalModel
import ng.mathemandy.local.entity.SubjectLocalModel

@Database(
    entities = [SubjectLocalModel::class, LessonLocalModel::class],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class UlessonDatabase : RoomDatabase() {

    abstract val subjectDao: SubjectDao

    abstract val lessonAndSubjectDao: LessonAndSubjectDao

    companion object {
        private const val DATABASE_NAME: String = "ul3sson_db"
        fun build(context: Context): UlessonDatabase = Room.databaseBuilder(
            context.applicationContext,
            UlessonDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}