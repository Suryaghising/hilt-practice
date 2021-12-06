package com.example.hiltpractice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    private val _couter: MutableStateFlow<Int> = MutableStateFlow(0)
    val counter: StateFlow<Int> = _couter
    fun incrementState() {
        _couter.value++
    }

    fun decrementState() {
        _couter.value--
    }
}