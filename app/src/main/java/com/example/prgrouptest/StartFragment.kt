package com.example.prgrouptest

import android.net.ConnectivityManager
import android.content.Context
import android.content.Intent
import android.net.NetworkCapabilities
import android.os.Build

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class StartFragment : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_fragment)


    }

    //
    fun onYes(view: View) {

        if (checkForInternet(this)) {
            val intent = Intent(this, WebViewFragment::class.java)

            startActivity(intent)
        } else {
            val intent = Intent(this, NoInternetFragment::class.java)

            startActivity(intent)
        }


    }

    //
    fun onExit(view: View) {
        finish()
        android.os.Process.killProcess(android.os.Process.myPid ())

    }

    private fun checkForInternet(context: Context): Boolean {


        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            val network = connectivityManager.activeNetwork ?: return false


            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true


                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true


                else -> false
            }
        } else {

            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


}