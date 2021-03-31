package com.example.marvelcomics.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.marvelcomics.R
import com.example.marvelcomics.addAnimationOnView
import com.example.marvelcomics.databinding.FavoritesFragmentBinding
import com.example.marvelcomics.lists.adapters.ListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var favoritesFragmentBinding: FavoritesFragmentBinding

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private lateinit var favoritesAdapter: ListAdapter
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
        favoritesAdapter=ListAdapter(favoritesViewModel)
        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.submitList(it)
        })
        favoritesFragmentBinding.apply {
            favoritesList.adapter=favoritesAdapter
            favoritesList.addAnimationOnView(favoritesBarLayout)
            favoritesTopBar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.filter ->{
                        showPopupMenu(view)
                        true
                    }
                    else -> false
                }
            }
        }


    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(requireContext(), view.findViewById(R.id.filter))
        popup.menuInflater.inflate(R.menu.sort_menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId){
                R.id.ascending -> {
                    favoritesAdapter.sortInAscending()
                    true
                }
                R.id.descending -> {
                    favoritesAdapter.sortInDescending()
                    true
                }

                else -> false
            }
        }
        popup.setOnDismissListener {
            popup.dismiss()
        }
        popup.show()
    }
}