package com.example.marvelcomics.lists.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcomics.R
import com.example.marvelcomics.database.entities.Creator
import com.example.marvelcomics.databinding.CreatorsListItemBinding
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL as size


class DetailViewHolder(private val creatorsListItemBinding: CreatorsListItemBinding) :
    RecyclerView.ViewHolder(creatorsListItemBinding.root) {
    fun bind(item: Creator) = creatorsListItemBinding.apply {
        Glide.with(root).load(item.creatorImage).thumbnail(
            Glide.with(root).load(R.drawable.crop_php).override(size)
        ).into(creatorImage)
        creatorName.text = item.creatorFullName
        creatorRole.text = item.creatorJob
    }
}