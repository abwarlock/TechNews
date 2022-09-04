package com.example.technews.networks

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkManger {

    companion object {
        var service: Api? = null

        fun getInstance(): Api {
            if (service == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://inshortsapi.vercel.app")
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()

                service = retrofit.create(Api::class.java)
            }
            return service as Api
        }

    }


}