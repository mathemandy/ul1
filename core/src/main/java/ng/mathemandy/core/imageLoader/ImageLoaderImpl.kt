package ng.mathemandy.core.imageLoader

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoaderImpl @Inject constructor() : ImageLoader {

    override fun loadImage(view: ImageView, progress: ProgressBar, url: String) {
        Glide.with(view).load(url).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progress.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progress.visibility = View.GONE
                return false
            }
        }).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }

    override fun loadImage(view: ImageView, url: String) {
        Glide.with(view).load(url).
        diskCacheStrategy(DiskCacheStrategy.ALL).into(view)

    }
}