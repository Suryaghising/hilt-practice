package com.example.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltpractice.adapters.PostAdapter
import com.example.hiltpractice.model.Post
import com.example.hiltpractice.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var postAdapter: PostAdapter
    lateinit var postViewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postViewModel.getPost()
        postViewModel.responseLiveData.observe(this, Observer {
            postAdapter.setPost(it as ArrayList<Post>)
        })
    }

    private fun initUi() {
        recyclerView = findViewById(R.id.recyclerViewId)
        postAdapter = PostAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}