package com.example.marvelcomics.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.marvelcomics.databinding.MainFragmentBinding
import com.example.marvelcomics.lists.adapters.PagingAdapter
import com.example.marvelcomics.message.ShowMessage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), ShowMessage {

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerAdapter: PagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        callFlow()
        mainFragmentBinding.textInputLayout.setEndIconOnClickListener {
            viewModel.applyFlow(mainFragmentBinding.name.text.toString())
            callFlow()
        }
    }

    private fun setupList() {
        recyclerAdapter = PagingAdapter().also {
            it.attachView(this)
        }
        recyclerAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading ||
                it.append is LoadState.Loading
            )
                mainFragmentBinding.progressBar2.visibility = View.VISIBLE
            else {
                mainFragmentBinding.progressBar2.visibility = View.INVISIBLE
                val errorState = when {
                    it.append is LoadState.Error -> it.append as LoadState.Error
                    it.prepend is LoadState.Error -> it.prepend as LoadState.Error
                    it.refresh is LoadState.Error -> it.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    showMessage("Nothing was found by your request")
                }

            }
        }
        mainFragmentBinding.recyclerView.adapter = recyclerAdapter

    }


    private fun callFlow() {
        lifecycleScope.launch {
            viewModel.comicsPagingFlow?.collect {
                recyclerAdapter.submitData(it)
            }
        }
    }

    override fun showMessage(text: String) {
        Snackbar.make(
            mainFragmentBinding.root,
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }


}