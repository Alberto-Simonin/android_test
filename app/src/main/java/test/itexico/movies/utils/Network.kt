package test.itexico.movies.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager


object Network {

    fun isAvailable(context: Context): Boolean{
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo.isConnected
    }
}