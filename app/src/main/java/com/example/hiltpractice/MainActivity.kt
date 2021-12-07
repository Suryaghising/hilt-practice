package com.example.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.hiltpractice.databinding.ActivityMainBinding
import com.example.hiltpractice.util.checkConnect
import com.example.hiltpractice.util.textChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        counterResult()

        lifecycleScope.launchWhenStarted {
            checkConnect().collect {
                val check = when(it) {
                    true -> "Connected with internet."
                    false -> "Not connected"
                }
                binding.network.text = check
            }
        }

        lifecycleScope.launchWhenStarted {
            binding.username.textChange().debounce(400L).collect {
                Log.d("main", "onCreate: $it")
            }
        }
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