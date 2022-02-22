package com.asgatech.commonutils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

/**
 * Image Loading Helpers
 */
class ImageLoaderHelper {
    private val EMPTY_PLACE_HOLDER = -1

    /**
     * Load Image From Url
     */
    fun loadImage(
        activity: Context?,
        imageView: ImageView?,
        url: String?,
        @DrawableRes placeHolder: Int = EMPTY_PLACE_HOLDER
    ) {
        if (url.isNullOrEmpty() || imageView == null) return
        Glide.with(activity!!).load(url)
            .placeholder(getPlaceHolder(placeHolder))
            .into(imageView)
    }

    /**
     * Load Image From Drawable resource and set an optional  place holder for it
     */
    fun loadImage(
        activity: Context?,
        imageView: ImageView?,
        drawable: Drawable?,
        @DrawableRes placeHolder: Int = EMPTY_PLACE_HOLDER
    ) {
        Glide.with(activity!!).load(drawable)
            .placeholder(getPlaceHolder(placeHolder))
            .into(imageView!!)
    }

    /**
     * Load Image From Bitmap
     */
    fun loadImage(
        activity: Context?, imageView: ImageView?, bitmap: Bitmap?,
        @DrawableRes placeHolder: Int = EMPTY_PLACE_HOLDER
    ) {
        Glide.with(activity!!).load(bitmap)
            .placeholder(getPlaceHolder(placeHolder))
            .into(imageView!!)
    }

    /**
     * Return the drawable that should be displayed as a place holder
     */
    private fun getPlaceHolder(placeHolder: Int): Int {
        return if (placeHolder == EMPTY_PLACE_HOLDER) R.drawable.ic_image_placeholder
        else placeHolder
    }
}