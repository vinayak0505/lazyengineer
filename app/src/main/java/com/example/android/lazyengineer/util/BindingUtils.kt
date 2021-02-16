package com.example.android.lazyengineer.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter


// main fragment adapter
@BindingAdapter("small_image")
fun loadSmallImage(view: ImageView, resource: Int) {
    view.setImageResource(resource)
}

@BindingAdapter("big_image")
fun loadBigImage(view: ImageView, resource: Int) {
    if (resource == 0)
        view.visibility = View.GONE
    else {
        view.visibility = View.VISIBLE
        view.setImageResource(resource)
    }
}

@BindingAdapter("small_image_check")
fun checkSmallImage(view: CardView, resource: Int) {
    if (resource == 0)
        view.visibility = View.GONE
    else view.visibility = View.VISIBLE
}

// News fragment Adapter
@BindingAdapter("news_source_check")
fun checkNewsSource(view:Button,resource: String){
    if (resource == "")
        view.visibility = View.GONE
    else view.visibility = View.VISIBLE
}