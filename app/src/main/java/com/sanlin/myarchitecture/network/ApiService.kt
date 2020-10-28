package com.sanlin.myarchitecture.network

import com.sanlin.myarchitecture.model.News
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines")
    fun getTopHeadlines():Call<News>

}