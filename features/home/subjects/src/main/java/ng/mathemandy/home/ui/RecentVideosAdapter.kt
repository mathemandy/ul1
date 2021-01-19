package ng.mathemandy.home.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ng.mathemandy.core.imageLoader.ImageLoader
import ng.mathemandy.home.R
import ng.mathemandy.model.LessonModel
import java.util.*
import javax.inject.Inject


typealias RecentVideoClickListener = (LessonModel) -> Unit


class RecentVideosAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    ListAdapter<LessonModel, RecentVideosAdapter.RecentVideosViewHolder>(
        RecentVideosDC()
    ) {


    private var layoutId: Int = R.layout.recent_videos

    var clickListener: RecentVideoClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecentVideosViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
    )

    override fun onBindViewHolder(holder: RecentVideosViewHolder, position: Int) {
        holder.bind(imageLoader, clickListener, getItem(position))
    }


    fun swapData(data: List<LessonModel>, layoutId: Int?) {
        if (layoutId != null) {
            this.layoutId = layoutId
        }
        submitList(data.toMutableList())
    }

    inner class RecentVideosViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val cardColor = MutableList(10) {
            when (it) {
                0, 5-> Color.parseColor("#2E2E2E")
                1 , 6-> Color.parseColor("#0C5E35")
                2 ,7-> Color.parseColor("#7A0C4C")
                3, 8 -> Color.parseColor("#00326F")
                4, 9 -> Color.parseColor("#7A4F01")
                else -> Color.parseColor("#004883")

            }
        }


        fun bind(imageLoader: ImageLoader, clickListener: RecentVideoClickListener?, item: LessonModel) = with(itemView) {
            val rnd = Random()
           val currentColor: Int = cardColor[rnd.nextInt(10)]
            val imageView  = itemView.findViewById<ImageView>(R.id.advert_track_image)
            imageView.setBackgroundColor(currentColor)
            val lessonTitle  =
                itemView.findViewById<TextView>(R.id.lesson_title)

            val subjectTitle  =
                itemView.findViewById<TextView>(R.id.subject_title)

            lessonTitle.text =  item.name
            subjectTitle.text =  "Mathematics"
            subjectTitle.setTextColor(currentColor)

            val uri  = item.icon.replaceFirst("\\*$", "")
            imageLoader.loadImage(imageView, uri)

            itemView.setOnClickListener {
                clickListener?.invoke(item)
            }

        }
    }

    private class RecentVideosDC : DiffUtil.ItemCallback<LessonModel>() {

        override fun areContentsTheSame(oldItem: LessonModel, newItem: LessonModel): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: LessonModel, newItem: LessonModel): Boolean =
            oldItem.id == newItem.id

    }
}