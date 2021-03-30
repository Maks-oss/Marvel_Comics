package com.example.marvelcomics.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marvelcomics.data.Result
import com.example.marvelcomics.databinding.ComicsListItemBinding

class RecyclerAdapter : PagingDataAdapter<Result, ComicsViewHolder>(DiffUtil()) {
    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
        ComicsViewHolder(
            ComicsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


}

class DiffUtil : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}