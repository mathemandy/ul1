package ng.mathemandy.lessons.ui.adapteritems

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem
import ng.mathemandy.lessons.R
import ng.mathemandy.lessons.databinding.ItemSectionCustomRowLayoutBinding
import ng.mathemandy.model.ChapterModel


class LessonItemViewHolder(
    private val chapter: ChapterModel,
    private val sectionAdapter: GroupAdapter<GroupieViewHolder>
) : BindableItem<ItemSectionCustomRowLayoutBinding>() {
    override fun getLayout(): Int = R.layout.item_section_custom_row_layout
    override fun bind(viewBinding: ItemSectionCustomRowLayoutBinding, position: Int) {
        viewBinding.sectionRecyclerview.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = sectionAdapter
        }

        viewBinding.sectionLabel.apply {
            text = chapter.name
        }
    }


    override fun getId(): Long {
        return chapter.id.toLong()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LessonItemViewHolder) return false

        if (chapter.id != other.chapter.id) return false
        if (sectionAdapter.itemCount != other.sectionAdapter.itemCount) return false
        return true
    }

    override fun hashCode(): Int {
        var result = chapter.id.hashCode()
        result *= 31 * sectionAdapter.hashCode()
        return result
    }


}

