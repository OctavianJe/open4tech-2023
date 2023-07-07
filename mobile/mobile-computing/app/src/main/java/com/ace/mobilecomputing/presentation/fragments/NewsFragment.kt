package com.ace.mobilecomputing.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ace.mobilecomputing.NavigationHostActivity
import com.ace.mobilecomputing.data.models.NewsDataModel
import com.ace.mobilecomputing.databinding.FragmentHomeBinding
import com.ace.mobilecomputing.databinding.FragmentNewsBinding
import com.ace.mobilecomputing.presentation.adapters.NewsListViewAdapter
import com.ace.mobilecomputing.presentation.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class NewsFragment : BaseFragment() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var binding: FragmentNewsBinding

    private val args : NewsFragmentArgs by navArgs()
    private var id_received = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        id_received = args.idNews

        onLoad()
        return binding.root
    }

    fun onLoad() {
        var news_from_dataset: NewsDataModel?
        runBlocking {
            news_from_dataset = viewModel.getNews(id_received)
        }

        if (news_from_dataset != null) {
            binding.newsTitle.text = news_from_dataset!!.title
            binding.fullDescriptionNews.text = news_from_dataset!!.description
            binding.summarisedDescriptionNews.text = viewModel.getSummary(news_from_dataset!!.description)
        }

    }

    private fun goBack() {
        navigateSafe(NewsFragmentDirections.actionLocationFragmentToHomeFragment())
    }

    override fun setOnBackPressed(onBackAlternative: (() -> Unit)?) {
        (activity as NavigationHostActivity).onBackPressAlternative = {
            goBack()
        }
    }
}