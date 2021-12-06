package com.example.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {
            getData().catch { e ->
                Log.d("main", "onCreate: ${e.message}")
            }.collect {
                Log.d("main", "data is=== $it")
            }
        }

    }

    private fun getData(): Flow<Int> = flow {
        for (i in 1..5) {
            kotlinx.coroutines.delay(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)
}