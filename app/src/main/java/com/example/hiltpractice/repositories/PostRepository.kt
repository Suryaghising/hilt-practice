package com.example.hiltpractice.repositories

import com.example.hiltpractice.model.Post
import com.example.hiltpractice.network.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class PostRepository {

    companion object {
        fun getPost(): Flow<List<Post>> = flow {
            val response = RetrofitBuilder.api.getPost()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}