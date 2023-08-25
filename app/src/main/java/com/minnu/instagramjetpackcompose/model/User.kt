package com.minnu.instagramjetpackcompose.model

data class User(
    val profile: Int,
    val userName: String,
    val storyCount: Int = 1,
    val stories: List<Int> = listOf()
)