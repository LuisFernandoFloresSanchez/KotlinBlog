package com.example.examenandroid.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examenandroid.ui.entradas.AgregarEntrada
import com.example.examenandroid.ui.entradas.DetalleEntrada
import com.example.examenandroid.ui.entradas.ListEntradas
import com.example.examenandroid.viewmodel.EntradasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.ListEntradas.route) {
        composable(route = AppScreens.ListEntradas.route) {
            ListEntradas(navController = navController, viewModel = EntradasViewModel())
        }
        composable(route = AppScreens.DetalleEntrada.route + "/{idEntrada}",
            arguments = listOf(navArgument(name = "idEntrada"){
                type = NavType.StringType
            })
        ) {
            DetalleEntrada(navController,it.arguments?.getString("idEntrada"), viewModel = EntradasViewModel())
        }
        composable (route = AppScreens.AgregarEntrada.route) {
            AgregarEntrada(navController = navController, EntradasViewModel())
        }
    }
}