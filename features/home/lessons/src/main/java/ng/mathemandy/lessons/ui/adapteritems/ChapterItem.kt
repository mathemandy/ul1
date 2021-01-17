package ng.mathemandy.lessons.ui.adapteritems

import com.xwray.groupie.databinding.BindableItem
import ng.mathemandy.core.imageLoader.ImageLoader
import ng.mathemandy.lessons.R
import ng.mathemandy.lessons.databinding.ItemChapterItemBinding
import ng.mathemandy.model.LessonModel

typealias LessonClickListener = (LessonModel) -> Unit


class LessonCardItem constructor(
    private val lessonModel: LessonModel,
    private val imageLoader: ImageLoader,
    private val lessonClickListener: LessonClickListener? = null
) : BindableItem<ItemChapterItemBinding>() {

    override fun getLayout(): Int = R.layout.item_chapter_item

    override fun bind(viewBinding: ItemChapterItemBinding, position: Int) {
        viewBinding.lessonTitle.text = lessonModel.name
        imageLoader.loadImage(viewBinding.lessonImage, lessonModel.icon)
        viewBinding.root.setOnClickListener {
            lessonClickListener?.invoke(lessonModel)
        }

    }

    override fun getId(): Long {
        return lessonModel.id.hashCode().toLong()
    }

}