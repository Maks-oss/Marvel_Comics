package com.example.marvelcomics.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.marvelcomics.databinding.FavoritesFragmentBinding
import com.example.marvelcomics.lists.adapters.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var favoritesFragmentBinding: FavoritesFragmentBinding
    @Inject
    lateinit var favoritesAdapter: RecyclerAdapter
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesFragmentBinding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return favoritesFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.submitList(it)
        })
        favoritesFragmentBinding.favoritesList.adapter=favoritesAdapter
    }
}