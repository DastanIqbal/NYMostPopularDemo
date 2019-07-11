package com.nyt.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dastanapps.dastanlib.glide.GlideUtils
import com.nyt.mostpopular.R
import com.nyt.network.model.MediumB

/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, media: List<MediumB>) {
        if (!media[0].mediaMetadata.isNullOrEmpty()) {
            var imageUrl = media[0].mediaMetadata?.get(0)!!.url
            if (media[0].mediaMetadata!!.size >= 3)
                imageUrl = media[0].mediaMetadata?.get(2)!!.url
            else if (media[0].mediaMetadata!!.size >= 2)
                imageUrl = media[0].mediaMetadata?.get(1)!!.url

            GlideUtils.loadImage(imageView.context, imageUrl!!, imageView, R.mipmap.ic_launcher)
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, media: String) {
        GlideUtils.loadImage(imageView.context, media, imageView, R.mipmap.ic_launcher)
    }
}

