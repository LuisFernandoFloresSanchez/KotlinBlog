package com.example.examenandroid.ui.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.examenandroid.navigation.AppNavigation
import com.example.examenandroid.ui.components.NoInternetAppBar
import com.example.examenandroid.ui.theme.ExamenAndroidTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private var isNetworkAvailable by mutableStateOf(true)

    private val connectivityReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            checkNetworkStatus()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        setContent {
            ExamenAndroidTheme(darkTheme = false) {
                // Utilizando la composable principal
                MainContent(isNetworkAvailable)
            }
        }
    }

    override fun onDestroy() {
        // Desregistrar el BroadcastReceiver cuando la actividad se destruye
        unregisterReceiver(connectivityReceiver)
        super.onDestroy()
    }

    private fun checkNetworkStatus() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        isNetworkAvailable = networkInfo != null && networkInfo.isConnected
    }
}

@Composable
fun MainContent(isNetworkAvailable: Boolean) {
    val context = LocalContext.current

    // Verificar la conexión a Internet
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Si hay conexión a Internet, mostrar el contenido principal
        if (isNetworkAvailable) {
            AppNavigation()
        } else {
            // Si no hay conexión a Internet, mostrar la AppBar de NoInternet
            Column {
                NoInternetAppBar(activity = context as ComponentActivity)
                Spacer(modifier = Modifier.height(16.dp)) // Espaciador opcional
                // Tu contenido existente
                AppNavigation()
            }
        }
    }
}

