package com.example.marvelcomics.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.marvelcomics.databinding.MainFragmentBinding
import com.example.marvelcomics.paging.RecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerAdapter: RecyclerAdapter

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
        recyclerAdapter = RecyclerAdapter()
        recyclerAdapter.addLoadStateListener {
            mainFragmentBinding.progressBar2.isVisible = it.refresh is LoadState.Loading
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


}