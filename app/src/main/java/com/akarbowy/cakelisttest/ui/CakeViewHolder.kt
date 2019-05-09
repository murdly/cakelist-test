package com.akarbowy.cakelisttest.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.akarbowy.cakelisttest.extensions.loadThumbnail
import kotlinx.android.synthetic.main.viewholder_cake.view.*


internal class CakeViewHolder(
    itemView: View,
    private val listener: OnCakeClickListener
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var cake: CakeUIM

    fun onBind(item: CakeUIM) {
        cake = item
        with(cake) {
            itemView.title.text = title
            itemView.image.loadThumbnail(imageUrl)
            itemView.setOnClickListener { listener.onCakeClicked(cake) }
        }
    }

    interface OnCakeClickListener {
        fun onCakeClicked(cake: CakeUIM)
    }
}