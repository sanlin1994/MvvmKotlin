package com.sanlin.myarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanlin.myarchitecture.model.Article
import com.sanlin.myarchitecture.model.News
import com.sanlin.myarchitecture.repositories.NewsRepository
import com.sanlin.myarchitecture.utils.Resource

class NewsHeadlinesViewModel(private val newsRepository: NewsRepository): ViewModel() {

    val newsHeadlines:LiveData<Resource<News>> by lazy {
        newsRepository.getHeadlines()
    }

}