package com.example.technews.pojo

import com.google.gson.annotations.SerializedName

data class TechNewsResp(
    @SerializedName("category") var category: String?,
    @SerializedName("data") var data: List<PostData>,
    @SerializedName("success") var success: Boolean
)

data class PostData(
    @SerializedName("author") var author: String,
    @SerializedName("content") var content: String,
    @SerializedName("date") var date: String,
    @SerializedName("imageUrl") var imageUrl: String,
    @SerializedName("readMoreUrl") var readMoreUrl: String,
    @SerializedName("time") var time: String,
    @SerializedName("title") var title: String,
    @SerializedName("url") var url: String
)