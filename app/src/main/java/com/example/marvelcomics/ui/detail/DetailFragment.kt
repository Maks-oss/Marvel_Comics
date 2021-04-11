package com.example.marvelcomics.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.marvelcomics.GlideApp
import com.example.marvelcomics.R
import com.example.marvelcomics.databinding.FavoritesDetailBinding
import com.example.marvelcomics.lists.adapters.DetailAdapter
import com.example.marvelcomics.lists.listeners.FavoritesButtonListener
import com.example.marvelcomics.setFavoritesImage
import com.example.marvelcomics.ui.favorites.FavoritesViewModel
import com.example.marvelcomics.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var detailFragmentBinding: FavoritesDetailBinding
    private lateinit var detailAdapter: DetailAdapter
    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailFragmentBinding = FavoritesDetailBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return detailFragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val value = args.favoriteAndCreators
        detailAdapter = DetailAdapter()
        detailViewModel.apply {
            initCreators(value?.creators)
            creatorsData.observe(viewLifecycleOwner, Observer {
                detailAdapter.submitList(it)
            })
        }

        detailFragmentBinding.apply {
            value?.favorite?.apply {
                detailImage.transitionName = image
                detailTitle.text = title
                detailPublicationDate.text = publicationDate
                GlideApp.with(requireContext()).load(image).placeholder(R.drawable.crop_php)
                    .transition(DrawableTransitionOptions.withCrossFade()).into(detailImage)
            }
            detailFavoritesButton.setFavoritesImage(args.state)
            val favoritesButtonListener =
                FavoritesButtonListener(searchViewModel).also {
                    it.isClicked = args.state
                    it.item = value
                }
            detailFavoritesButton.setOnClickListener(favoritesButtonListener)
            creatorsList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = detailAdapter
            }


        }


    }
}