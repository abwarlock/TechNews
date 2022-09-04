package com.example.technews.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.technews.R
import com.example.technews.adpters.PostAdapters
import com.example.technews.viewmodels.NetworkViewModel

class MainActivity : BaseActivity() {

    private val mAdapter = PostAdapters()

    var viewModel = lazy {
        ViewModelProvider(this).get(NetworkViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        viewModel.let {
            mAdapter.setUpObserver(it.value.response)
            it.value.fetchNetWorkResp()
        }
    }

    private fun initData() {
        findViewById<RecyclerView>(R.id.rv)?.let {
            it.adapter = mAdapter
        }
    }
}