package com.example.marvelcomics.lists.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.marvelcomics.GlideApp
import com.example.marvelcomics.R
import com.example.marvelcomics.database.entities.Creator
import com.example.marvelcomics.databinding.CreatorsListItemBinding


class DetailViewHolder(private val creatorsListItemBinding: CreatorsListItemBinding) :
    RecyclerView.ViewHolder(creatorsListItemBinding.root) {
    fun bind(item: Creator) = creatorsListItemBinding.apply {
        GlideApp.with(root).load(item.creatorImage).placeholder(
            R.drawable.crop_php
        ).transition(DrawableTransitionOptions.withCrossFade()).into(creatorImage)
        creatorName.text = item.creatorFullName
        creatorRole.text = item.creatorJob
    }
}