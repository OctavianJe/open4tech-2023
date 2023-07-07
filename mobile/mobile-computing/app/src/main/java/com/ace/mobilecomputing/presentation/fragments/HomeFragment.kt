package com.ace.mobilecomputing.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ace.mobilecomputing.NavigationHostActivity
import com.ace.mobilecomputing.data.models.NewsDataModel
import com.ace.mobilecomputing.databinding.FragmentHomeBinding
import com.ace.mobilecomputing.presentation.adapters.NewsListViewAdapter
import com.ace.mobilecomputing.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var contentListAdapter: NewsListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerView()
        subscribeUi()

        return binding.root
    }

    override fun setOnBackPressed(onBackAlternative: (() -> Unit)?) {
        (activity as NavigationHostActivity).onBackPressAlternative = {
            utilities.showExitPopup(requireActivity())
        }
    }

    private fun initRecyclerView() {
        contentListAdapter = NewsListViewAdapter(
            viewModel.initRecycleView(),
            object : NewsListViewAdapter.OnItemClickListener {
                override fun onItemClick(content: NewsDataModel) {
                    navigateSafe(
                        HomeFragmentDirections.actionHomeFragmentToLocationFragment(
                            content.localId!!
                        )
                    )
                }
            })
        binding.productRecyclerView.adapter = contentListAdapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun subscribeUi() {
        binding.fetchDataButton.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            binding.fetchDataButton.visibility = View.GONE
            viewModel.checkWiFiModule(requireActivity()) { wiFi ->
                binding.loading.visibility = View.GONE
                binding.fetchDataButton.visibility = View.VISIBLE
                if (!wiFi) {
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                } else {
                    viewModel.refreshContent()
                    initRecyclerView()
                }
            }
        }
    }
}