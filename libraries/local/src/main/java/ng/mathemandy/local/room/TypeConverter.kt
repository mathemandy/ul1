package ng.mathemandy.local.room

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ng.mathemandy.local.entity.ChapterLocalModel
import ng.mathemandy.local.entity.LessonAndSubject
import ng.mathemandy.local.entity.SubjectLocalModel
import java.lang.reflect.ParameterizedType

class TypeConverter {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val adapter: JsonAdapter<List<String>> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, String::class.java)
        moshi.adapter<List<String>>(type)
    }

    private val chapterModelAdapter: JsonAdapter<List<ChapterLocalModel>> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, ChapterLocalModel::class.java)
        moshi.adapter<List<ChapterLocalModel>>(type)
    }

    private val subjectModelAdapter: JsonAdapter<SubjectLocalModel> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, SubjectLocalModel::class.java)
        moshi.adapter<SubjectLocalModel>(type)
    }

    private val lessonAndSubjectAdapter: JsonAdapter<List<LessonAndSubject>> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, LessonAndSubject::class.java)
        moshi.adapter<List<LessonAndSubject>>(type)
    }

    @TypeConverter
    fun toLessonAndSubjectList(value: String): List<LessonAndSubject>? {
        return lessonAndSubjectAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromLessonAndSubjectList(value: List<LessonAndSubject>): String {
        return lessonAndSubjectAdapter.toJson(value)
    }

    @TypeConverter
    fun toChapterList(value: String): List<ChapterLocalModel>? {
        return chapterModelAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromChapterList(value: List<ChapterLocalModel>): String {
        return chapterModelAdapter.toJson(value)
    }

    @TypeConverter
    fun toSubjectList(value: String): SubjectLocalModel? {
        return subjectModelAdapter.fromJson(value)
    }

    @TypeConverter
    fun fromSubjectList(value: SubjectLocalModel): String {
        return subjectModelAdapter.toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<String>? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(value: List<String>): String {
        return adapter.toJson(value)
    }
}
