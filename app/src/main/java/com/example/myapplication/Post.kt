package com.example.myapplication

import com.google.gson.annotations.SerializedName

class Post {
    val userId: Int = 0
    val id: Int = 0
    val title: String? = "XYZ"

    @SerializedName("body")
    val text: String? = null
}