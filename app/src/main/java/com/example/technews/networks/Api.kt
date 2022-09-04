package com.example.technews.networks

import com.example.technews.pojo.TechNewsResp
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface Api {

    @GET("/news?category=technology")
    fun fetchTechNews(): Call<TechNewsResp>
}