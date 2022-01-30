package com.asgatech.commonutils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Image Loading Helpers
 */
class ImageLoaderHelper {
    /**
     * Load Image From Url
     */
    fun loadImage(activity: Context?, imageView: ImageView?, url: String?) {
        if (url == null || url.isEmpty() || imageView == null) return
        Glide.with(activity!!).load(url)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(imageView)
    }

    /**
     * Load Image From Drawable resource
     */
    fun loadImage(activity: Context?, imageView: ImageView?, drawable: Drawable?) {
        Glide.with(activity!!).load(drawable)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(imageView!!)
    }

    /**
     * Load Image From Bitmap
     */
    fun loadImage(activity: Context?, imageView: ImageView?, bitmap: Bitmap?) {
        Glide.with(activity!!).load(bitmap)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(imageView!!)
    }
}