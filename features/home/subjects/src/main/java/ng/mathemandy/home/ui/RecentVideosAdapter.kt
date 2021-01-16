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
import ng.mathemandy.home.R
import ng.mathemandy.ulesson.base.Lessons
import java.util.*

class RecentVideosAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Lessons, RecentVideosAdapter.RecentVideosViewHolder>(
        RecentVideosDC()
    ) {


    private var layoutId: Int = R.layout.recent_videos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecentVideosViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false), interaction
    )

    override fun onBindViewHolder(holder: RecentVideosViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    fun swapData(data: List<Lessons>, layoutId: Int?) {
        if (layoutId != null) {
            this.layoutId = layoutId
        }
        submitList(data.toMutableList())
    }

    inner class RecentVideosViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

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

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)

            interaction?.itemClicked(clicked)
        }

        fun bind(item: Lessons) = with(itemView) {
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
//            Glide.with(itemView).load(uri).into(imageView)

        }
    }

    interface Interaction {
        fun itemClicked(item: Lessons)
    }

    private class RecentVideosDC : DiffUtil.ItemCallback<Lessons>() {

        override fun areContentsTheSame(oldItem: Lessons, newItem: Lessons): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: Lessons, newItem: Lessons): Boolean =
            oldItem.id == newItem.id

    }
}