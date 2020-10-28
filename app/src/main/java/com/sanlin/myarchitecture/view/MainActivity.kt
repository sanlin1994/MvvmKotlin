package com.sanlin.myarchitecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sanlin.myarchitecture.R
import com.sanlin.myarchitecture.network.ApiService
import com.sanlin.myarchitecture.network.RetrofitClient
import com.sanlin.myarchitecture.repositories.NewsRepository
import com.sanlin.myarchitecture.utils.Status
import com.sanlin.myarchitecture.viewmodel.NewsHeadlinesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var newsHeadlinesViewModel:NewsHeadlinesViewModel
    lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService:ApiService = RetrofitClient.getClient()
        newsRepository = NewsRepository(apiService)

        newsHeadlinesViewModel = getViewModel()

        newsHeadlinesViewModel.newsHeadlines.observe(this, Observer {
            when(it.status){
                Status.LOADING -> Log.i("news", "onLoading: loading data.....")
                Status.ERROR -> Log.i("news", "onError: "+it.message)
                Status.SUCCESS -> Log.i("news", "onSuccess: "+it.data?.articles?.get(0)?.description)

            }

        })

    }

    private fun getViewModel(): NewsHeadlinesViewModel{
        return ViewModelProviders.of(this,object: ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NewsHeadlinesViewModel(newsRepository) as T
            }
        })[NewsHeadlinesViewModel::class.java]
    }

}