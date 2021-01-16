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
import ng.mathemandy.home.R
import ng.mathemandy.ulesson.base.Subjects
import java.util.*
import javax.inject.Inject


typealias SubjectClickListener = (Subjects) -> Unit


class SubjectsAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    ListAdapter<Subjects, SubjectsAdapter.SubjectsViewHolder>(
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


    fun swapData(data: List<Subjects>, layoutId: Int?) {
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


        fun bind(imageLoader: ImageLoader, clickListener: SubjectClickListener?, item: Subjects) =
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
        fun itemClicked(item: Subjects)
    }

    private class SubjectsDC : DiffUtil.ItemCallback<Subjects>() {

        override fun areContentsTheSame(oldItem: Subjects, newItem: Subjects): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: Subjects, newItem: Subjects): Boolean =
            oldItem.id == newItem.id

    }
}