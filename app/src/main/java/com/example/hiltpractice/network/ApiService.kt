package com.example.hiltpractice.network

import com.example.hiltpractice.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost(): List<Post>
}