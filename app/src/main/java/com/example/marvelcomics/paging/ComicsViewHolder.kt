package com.example.marvelcomics.paging

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcomics.data.Result
import com.example.marvelcomics.databinding.ComicsListItemBinding

class ComicsViewHolder(private val comicsListItemBinding: ComicsListItemBinding) :
    RecyclerView.ViewHolder(
        comicsListItemBinding.root
    ) {

    fun bind(item: Result) {
        comicsListItemBinding.apply {
            Glide.with(root)
                .load(item.images.first().path.plus(".jpg"))
                .into(comicsImage)
            comicsTitle.text = item.title
            comicsDate.append(item.dates.find { it.type == "onsaleDate" }?.date?.substringBefore("T"))
        }

    }
}