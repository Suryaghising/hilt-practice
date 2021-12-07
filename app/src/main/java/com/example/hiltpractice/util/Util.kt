package com.example.hiltpractice.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun Context.checkConnect(): Flow<Boolean> = callbackFlow {
    val callback = object: ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            offer(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            offer(false)
        }
    }

    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    manager.registerNetworkCallback(NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build(), callback)

    awaitClose {
        manager.unregisterNetworkCallback(callback)
    }

}

@ExperimentalCoroutinesApi
fun EditText.textChange(): Flow<Editable?> = callbackFlow {
    val callback = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            offer(s)
        }

    }

    awaitClose {
        removeTextChangedListener(callback)
    }
}