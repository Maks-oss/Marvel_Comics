package com.example.marvelcomics.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.marvelcomics.database.entities.Creator
import com.example.marvelcomics.databinding.CreatorsListItemBinding
import com.example.marvelcomics.lists.viewholders.DetailViewHolder

class DetailAdapter:
    ListAdapter<Creator, DetailViewHolder>(
        AsyncDifferConfig.Builder<Creator>(object : DiffUtil.ItemCallback<Creator>() {
            override fun areItemsTheSame(oldItem: Creator, newItem: Creator): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Creator, newItem: Creator): Boolean {
                return oldItem == newItem
            }
        }).build()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder(
            CreatorsListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        )

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}