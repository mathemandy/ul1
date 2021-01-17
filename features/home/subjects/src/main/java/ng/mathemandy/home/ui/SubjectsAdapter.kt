package ng.mathemandy.home.ui

import android.graphics.Color
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
import ng.mathemandy.domain.model.Subject
import ng.mathemandy.home.R
import ng.mathemandy.model.SubjectModel
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

        val cardColor = MutableList(10) {
            when (it) {
                0, 5 -> Color.parseColor("#EA7052")
                1, 6 -> Color.parseColor("#F9AD6D")
                2, 7 -> Color.parseColor("#68BC98")
                3, 8 -> Color.parseColor("#7B7FDA")
                4, 9 -> Color.parseColor("#506AAC")
                else -> Color.parseColor("#313848")

            }
        }


        fun bind(imageLoader: ImageLoader, clickListener: SubjectClickListener?, item: SubjectModel) =
            with(itemView) {
                val rnd = Random()
                val currentColor: Int = cardColor[rnd.nextInt(10)]
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

    interface Interaction {
        fun itemClicked(item: Subject)
    }

    private class SubjectsDC : DiffUtil.ItemCallback<SubjectModel>() {

        override fun areContentsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: SubjectModel, newItem: SubjectModel): Boolean =
            oldItem.id == newItem.id

    }
}