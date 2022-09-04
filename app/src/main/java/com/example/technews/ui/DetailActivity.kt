package com.example.technews.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.technews.R
import com.example.technews.pojo.PostData
import com.google.gson.Gson


class DetailActivity : BaseActivity() {

    companion object {
        private const val POST_ARG_DATA = "ARG_DATA"
        fun start(context: Context, postData: PostData) {
            ActivityCompat.startActivity(
                context,
                Intent(context, DetailActivity::class.java)
                    .apply {
                        putExtra(POST_ARG_DATA, Gson().toJson(postData))
                    },
                null
            )
        }
    }

    var mPostData: PostData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datail)
        fetchDataIntent()
    }

    private fun fetchDataIntent() {
        mPostData = intent.getStringExtra(POST_ARG_DATA)?.let {
            Gson().fromJson(it, PostData::class.java)
        }
        if (mPostData != null) {
            updateUI()
        } else {
            finish()
        }
    }

    private fun updateUI() {
        findViewById<ImageView>(R.id.detail_image)?.let {
            Glide.with(this)
                .load(mPostData?.imageUrl)
                .into(it)
        }
        findViewById<TextView?>(R.id.title_textView)?.text = mPostData?.title
        findViewById<TextView?>(R.id.desc_textView)?.text = mPostData?.content
        findViewById<TextView?>(R.id.read_more_title_textView)?.text = "Date of article: ${mPostData?.date}"

        findViewById<TextView?>(R.id.read_more_textView)?.apply {
            isClickable = true;
            movementMethod = LinkMovementMethod.getInstance();
            text = Html.fromHtml("<a href='${mPostData?.readMoreUrl}'> Read full article </a>")
        }
    }
}