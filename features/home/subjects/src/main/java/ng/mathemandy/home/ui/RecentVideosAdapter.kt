package ng.mathemandy.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ng.mathemandy.core.imageLoader.ImageLoader
import ng.mathemandy.home.R
import ng.mathemandy.model.LessonAndSubjectModel
import ng.mathemandy.model.LessonModel
import ng.mathemandy.ulesson.ui.getSubjectColor
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




        fun bind(imageLoader: ImageLoader, clickListener: RecentVideoClickListener?, item: LessonAndSubjectModel) = with(itemView) {
            val lesson = item.lesson
            val subject = item.subject

            val currentColor: Int = getSubjectColor(subject.id)

            val imageView  = itemView.findViewById<ImageView>(R.id.advert_track_image)
            val bg  = itemView.findViewById<ConstraintLayout>(R.id.imageView_bg)
            val playBtn  = itemView.findViewById<ImageButton>(R.id.play_btn)
            playBtn.setColorFilter(currentColor)
            bg.setBackgroundColor(currentColor)


            val lessonTitle  = itemView.findViewById<TextView>(R.id.lesson_title)
            val subjectTitle  = itemView.findViewById<TextView>(R.id.subject_title)
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