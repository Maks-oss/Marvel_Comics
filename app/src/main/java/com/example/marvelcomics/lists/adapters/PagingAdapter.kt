package com.example.marvelcomics.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marvelcomics.data.datahelper.Result
import com.example.marvelcomics.databinding.ComicsListItemBinding
import com.example.marvelcomics.lists.viewholders.ComicsViewHolder
import com.example.marvelcomics.ui.search.SearchViewModel

class PagingAdapter(private val searchViewModel: SearchViewModel, private val message:(String)->Unit)  : PagingDataAdapter<Result, ComicsViewHolder>(PagingUtil()) {
    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int)  {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
        ComicsViewHolder(
            ComicsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),searchViewModel,message
        )

    companion object{
        private class PagingUtil : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }
}

