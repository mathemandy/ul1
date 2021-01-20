package ng.mathemandy.lessons.ui.adapteritems

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnAsyncUpdateListener
import com.xwray.groupie.Section
import ng.mathemandy.core.imageLoader.ImageLoader
import ng.mathemandy.model.ChapterModel
import javax.inject.Inject

class SectionAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    GroupAdapter<GroupieViewHolder>() {

    private var sectionItems: List<ChapterModel> = listOf()
    var lessonClickListener: LessonClickListener? = null

    init {
        setHasStableIds(true)
    }

    fun setItems(items: List<ChapterModel>, async: OnAsyncUpdateListener) {
        if (sectionItems == items) {
            return
        } else {
            sectionItems = items.toMutableList()
            val updatingGroup = mutableListOf<Section>()
            items.forEach { section ->
                val sectionBy = Section()
                add(makeLessonItem(section))

                try {
                    if (!section.lessons.isNullOrEmpty())
                        updatingGroup.add(sectionBy)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun makeLessonItem(section: ChapterModel): LessonItemViewHolder {
        val lessons = section.lessons
        val sections = mutableListOf<Section>()
        lessons.forEach {
            sections.add(
                Section().apply {
                    add(
                        LessonCardItem(
                            it,
                            imageLoader,
                            lessonClickListener
                        )
                    )
                }
            )
        }

        val lessonAdapter = GroupAdapter<GroupieViewHolder>()
        lessonAdapter.update(sections)
        return LessonItemViewHolder(section, lessonAdapter)
    }
}
