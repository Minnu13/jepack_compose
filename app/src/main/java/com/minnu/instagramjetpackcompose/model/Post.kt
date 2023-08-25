package com.minnu.instagramjetpackcompose.model

data class Post(
    val profile: Int,
    val userName: String,
    val postImageList: List<Int>,
    val description: String,
    val likedBy: List<User>,
)