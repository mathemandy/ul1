package ng.groove.exovideoview.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ng.groove.exovideoview.R
import ng.groove.exovideoview.media.ExoMediaSource


import java.util.ArrayList


class MultiQualitySelectorAdapter(
        qualities: List<ExoMediaSource.Quality>,
        private val navigator: MultiQualitySelectorNavigator)
    : RecyclerView.Adapter<MultiQualitySelectorAdapter.MultiQualitySelectorViewHolder>() {

    private val qualities = ArrayList<ExoMediaSource.Quality>()

    interface MultiQualitySelectorNavigator {

        /***
         * @return interrupted if true
         */
        fun onQualitySelected(quality: ExoMediaSource.Quality): Boolean
    }


    interface VisibilityCallback {
        fun shouldChangeVisibility(visibility: Int)
    }


    init {
        this.qualities.addAll(qualities)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiQualitySelectorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quality, parent, false)
        return MultiQualitySelectorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MultiQualitySelectorViewHolder, position: Int) {
        holder.rootView.setOnClickListener { navigator.onQualitySelected(qualities[position]) }
        holder.qualityDes.text = qualities[position].displayName
    }

    override fun getItemCount(): Int {
        return qualities.size
    }

    inner class MultiQualitySelectorViewHolder(var rootView: View) : RecyclerView.ViewHolder(rootView) {
        var qualityDes: TextView = rootView.findViewById(R.id.qualityDes)

    }

}