package com.example.hiltpractice.network

import com.example.hiltpractice.model.Post
import javax.inject.Inject

class PostServiceImpl @Inject constructor(private val postApiService: PostApiService){

    suspend fun getPost(): List<Post> = postApiService.getPost()
}