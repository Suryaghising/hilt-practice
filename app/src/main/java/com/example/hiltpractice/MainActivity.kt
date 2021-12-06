package com.example.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.hiltpractice.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        counterResult()
    }

    private fun counterResult() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.counter.collect {
                counter -> binding.textId.text = counter.toString()
            }
        }
    }

    private fun init() {
        binding.incrementId.setOnClickListener {
            mainViewModel.incrementState()
        }
        binding.decrementId.setOnClickListener {
            mainViewModel.decrementState()
        }
    }
}