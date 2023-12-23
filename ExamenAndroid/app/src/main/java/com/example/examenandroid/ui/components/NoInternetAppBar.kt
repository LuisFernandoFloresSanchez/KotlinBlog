package com.example.examenandroid.ui.components

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



@Composable
fun NoInternetAppBar(activity: ComponentActivity) {
    TopAppBar(
        title = {
            Text(
                text = "No hay conexión a Internet",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        },
        backgroundColor = Color.White, // Puedes ajustar el color de fondo según tus preferencias
        contentColor = Color.Black, // Puedes ajustar el color de texto según tus preferencias
        elevation = AppBarDefaults.TopAppBarElevation,
        actions = {
            IconButton(
                onClick = {
                    // Abrir configuraciones de conexión
                    val settingsIntent = Intent(android.provider.Settings.ACTION_WIFI_SETTINGS)
                    activity.startActivity(settingsIntent)
                }
            ) {
                Icon(Icons.Default.Settings, contentDescription = "Configuraciones")
            }
        }
    )
}