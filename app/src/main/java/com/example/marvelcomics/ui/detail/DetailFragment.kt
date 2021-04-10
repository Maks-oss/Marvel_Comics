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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcomics.databinding.FavoritesDetailBinding
import com.example.marvelcomics.lists.adapters.DetailAdapter
import com.example.marvelcomics.setFavoritesImage

class DetailFragment : Fragment() {
    private lateinit var detailFragmentBinding: FavoritesDetailBinding
    private lateinit var detailAdapter: DetailAdapter
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

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
        detailViewModel.initCreators(value?.creators)
        detailViewModel.creatorsData.observe(viewLifecycleOwner, Observer {
            detailAdapter.submitList(it)
        })
        detailFragmentBinding.apply {
            value?.favorite?.apply {
                detailImage.transitionName = image
                detailTitle.text = title
                detailPublicationDate.text = publicationDate
                val circularProgressDrawable = CircularProgressDrawable(view.context).apply {
                    strokeWidth = 5f
                    centerRadius = 30f
                    start()
                }

                Glide.with(requireContext()).load(image).apply(RequestOptions.centerCropTransform())/*.placeholder(
                    circularProgressDrawable
                )*/.into(detailImage)
            }
            detailFavoritesButton.setFavoritesImage(args.state)
            creatorsList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            creatorsList.adapter=detailAdapter
        }


    }
}