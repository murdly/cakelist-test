package com.akarbowy.cakelisttest.extensions

import android.widget.ImageView
import com.akarbowy.cakelisttest.R
import com.squareup.picasso.Picasso


fun ImageView.loadThumbnail(url: String?) {
    //TODO: set singleton instance for Picasso
    Picasso.get()
        .load(url)
        .resizeDimen(R.dimen.thumbnail_size, R.dimen.thumbnail_size)
        .centerCrop()
        .placeholder(R.drawable.thumbnail_placeholder)
        .into(this)
}

