package com.smartsatu.android.live.example

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("app:srcCompat")
fun serSrcCompat(imageView: ImageView?, drawable: Drawable?) {
    imageView?.setImageDrawable(drawable)
}

@BindingAdapter("app:srcVector")
fun setSrcVector(view: ImageView?, @DrawableRes drawable: Int?) {
    drawable?.let { view?.setImageResource(drawable) }
}