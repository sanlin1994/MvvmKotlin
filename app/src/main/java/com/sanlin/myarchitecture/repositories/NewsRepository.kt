package com.sanlin.myarchitecture.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanlin.myarchitecture.model.News
import com.sanlin.myarchitecture.network.ApiService
import com.sanlin.myarchitecture.utils.Resource
import com.sanlin.myarchitecture.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val apiService:ApiService) {

    fun getHeadlines():LiveData<Resource<News>>{

        var resource:Resource<News> = Resource(Status.LOADING,null,"")
        val resourceLiveData:MutableLiveData<Resource<News>> = MutableLiveData()
        resourceLiveData.postValue(resource)

        apiService.getTopHeadlines().enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                resource = Resource(Status.SUCCESS,response.body()!!,"")
                resourceLiveData.postValue(resource)
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                resource = Resource(Status.ERROR,null,t.message.toString())
                resourceLiveData.postValue(resource)
            }
        })

        return resourceLiveData

    }



}