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
import ng.mathemandy.model.LessonAndSubjectModel
import java.util.*
import javax.inject.Inject


typealias RecentVideoClickListener = (LessonModel) -> Unit


class RecentVideosAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    ListAdapter<LessonAndSubjectModel, RecentVideosAdapter.RecentVideosViewHolder>(
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


    fun swapData(data: List<LessonAndSubjectModel>, layoutId: Int?) {
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


        fun bind(imageLoader: ImageLoader, clickListener: RecentVideoClickListener?, item: LessonAndSubjectModel) = with(itemView) {
            val rnd = Random()
           val currentColor: Int = cardColor[rnd.nextInt(10)]
            val imageView  = itemView.findViewById<ImageView>(R.id.advert_track_image)
            imageView.setBackgroundColor(currentColor)
            val lessonTitle  =
                itemView.findViewById<TextView>(R.id.lesson_title)

            val subjectTitle  =
                itemView.findViewById<TextView>(R.id.subject_title)

            val lesson = item.lesson
            val subject = item.subject

            lessonTitle.text =  lesson?.name
            subjectTitle.text =  subject.name
            subjectTitle.setTextColor(currentColor)

            val uri  = lesson?.icon?.replaceFirst("\\*$", "")
            uri?.let { imageLoader.loadImage(imageView, it) }

            itemView.setOnClickListener {
                lesson?.let { it1 -> clickListener?.invoke(it1) }
            }

        }
    }

    private class RecentVideosDC : DiffUtil.ItemCallback<LessonAndSubjectModel>() {

        override fun areContentsTheSame(oldItem: LessonAndSubjectModel, newItem: LessonAndSubjectModel): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: LessonAndSubjectModel, newItem: LessonAndSubjectModel): Boolean =
            oldItem.lesson?.id == newItem.lesson?.id

    }
}