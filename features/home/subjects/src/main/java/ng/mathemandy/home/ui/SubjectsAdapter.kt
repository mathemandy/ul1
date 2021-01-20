package ng.mathemandy.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ng.mathemandy.core.imageLoader.ImageLoader
import ng.mathemandy.home.R
import ng.mathemandy.model.SubjectModel
import ng.mathemandy.ulesson.ui.getSubjectColor
import java.util.*
import javax.inject.Inject

typealias SubjectClickListener = (SubjectModel) -> Unit

class SubjectsAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    ListAdapter<SubjectModel, SubjectsAdapter.SubjectsViewHolder>(
        SubjectsDC()
    ) {

    private var layoutId: Int = R.layout.subject_item

    var clickListener: SubjectClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubjectsViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
    )

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.bind(imageLoader, clickListener, getItem(position))
    }

    fun swapData(data: List<SubjectModel>, layoutId: Int?) {
        if (layoutId != null) {
            this.layoutId = layoutId
        }
        submitList(data.toMutableList())
    }

    inner class SubjectsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(imageLoader: ImageLoader, clickListener: SubjectClickListener?, item: SubjectModel) =
            with(itemView) {

                val currentColor: Int = getSubjectColor(item.id)
                val imageView = itemView.findViewById<ImageView>(R.id.advert_track_image)
                val progress = itemView.findViewById<ProgressBar>(R.id.progress)
                imageView.setBackgroundColor(currentColor)
                val subject =
                    itemView.findViewById<TextView>(R.id.subject_tv)

                subject.text = item.name
                val uri = item.icon.replaceFirst("\\*$", "")
                imageLoader.loadImage(imageView, progress, uri)

                itemView.setOnClickListener {
                    clickListener?.invoke(item)
                }
            }
    }

    private class SubjectsDC : DiffUtil.ItemCallback<SubjectModel>() {

        override fun areContentsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean =
            oldItem.id == newItem.id
    }
}
