package com.example.hiltpractice.repositories

import com.example.hiltpractice.model.Post
import com.example.hiltpractice.network.PostServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class PostRepository
@Inject
constructor(private val postApiServiceImpl: PostServiceImpl) {
    fun getPost(): Flow<List<Post>> = flow {
        val response = postApiServiceImpl.getPost()
        emit(response)
    }.flowOn(Dispatchers.IO)
}