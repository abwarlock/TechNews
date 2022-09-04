package com.example.technews.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technews.networks.NetworkManger
import com.example.technews.pojo.PostData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NetworkViewModel() : ViewModel() {
    val response = MutableLiveData<List<PostData>>()

    fun fetchNetWorkResp() {
        GlobalScope.launch {
            Log.d("TAG", "")
            val networkResp = NetworkManger.getInstance().fetchTechNews().execute()
            if (networkResp.isSuccessful) {
                val body = networkResp.body()
                if (body?.success == true) {
                    response.postValue(body.data)
                }
            }
            Log.d("TAG", "")
        }
    }
}