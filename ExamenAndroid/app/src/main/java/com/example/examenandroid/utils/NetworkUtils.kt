package com.example.examenandroid.utils


import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

object NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        val isAvailable = networkInfo != null && networkInfo.isConnected
        Log.d("NetworkUtils", "isNetworkAvailable: $isAvailable")
        return isAvailable
    }
}