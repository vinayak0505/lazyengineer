package com.example.android.lazyengineer.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.lazyengineer.R
import kotlinx.android.synthetic.main.news_frament_fragment.*

class NewsFragment : Fragment() {

    private lateinit var factory: NewsViewModelFactory
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_frament_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = NewsApi()
        val repository = NewsRepository(api)

        factory = NewsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this,factory).get(NewsViewModel::class.java)


        viewModel.news.observe(viewLifecycleOwner, Observer{ news->
            recycler_view_news.also{
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = NewsAdapter(requireContext(),news.results)
            }

        })
        viewModel.getNews()
    }

}