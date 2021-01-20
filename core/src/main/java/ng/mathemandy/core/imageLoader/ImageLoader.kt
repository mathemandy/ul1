package ng.mathemandy.core.imageLoader

import android.widget.ImageView
import android.widget.ProgressBar

interface ImageLoader {
    fun loadImage(view: ImageView, url: String)
    fun loadImage(view: ImageView, progress: ProgressBar, url: String)
}
