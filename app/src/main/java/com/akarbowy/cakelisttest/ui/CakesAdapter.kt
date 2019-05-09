package com.akarbowy.cakelisttest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.akarbowy.cakelisttest.R


internal class CakesAdapter(
    private val listener: CakeViewHolder.OnCakeClickListener
) : ListAdapter<CakeUIM, CakeViewHolder>(CakeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_cake, parent, false)
        return CakeViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}