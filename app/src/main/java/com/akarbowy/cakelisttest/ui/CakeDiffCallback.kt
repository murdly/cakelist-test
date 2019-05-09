package com.akarbowy.cakelisttest.ui

import androidx.recyclerview.widget.DiffUtil


internal class CakeDiffCallback : DiffUtil.ItemCallback<CakeUIM>() {

    override fun areItemsTheSame(oldItem: CakeUIM, newItem: CakeUIM) = oldItem == newItem

    override fun areContentsTheSame(oldItem: CakeUIM, newItem: CakeUIM) = oldItem == newItem
}