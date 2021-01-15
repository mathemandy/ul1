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
import ng.mathemandy.ulesson.base.Subjects
import java.util.*

class SubjectsAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Subjects, SubjectsAdapter.SubjectsViewHolder>(SubjectsDC()) {


    private var layoutId: Int = R.layout.subject_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SubjectsViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false), interaction
    )

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    fun swapData(data: List<Subjects>, layoutId: Int?) {
        if (layoutId != null) {
            this.layoutId = layoutId
        }
        submitList(data.toMutableList())
    }

    inner class SubjectsViewHolder(
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

        fun bind(item: Subjects) = with(itemView) {
            val rnd = Random()
           val currentColor: Int = cardColor[rnd.nextInt(10)]
            val imageView  = itemView.findViewById<ImageView>(R.id.advert_track_image)
            imageView.setBackgroundColor(currentColor)
            val subject  =
                itemView.findViewById<TextView>(R.id.subject_tv)

            subject.text =  item.name
            val uri  = item.icon.replaceFirst("\\*$", "")
//            Glide.with(itemView).load(uri).into(imageView)

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