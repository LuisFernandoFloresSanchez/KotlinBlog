package com.example.examenandroid.navigation

sealed class AppScreens (val route: String){
    object ListEntradas: AppScreens("list_entradas")
    object DetalleEntrada : AppScreens("detalle_entrada/{entradaId}")
    object AgregarEntrada: AppScreens("agregar_entrada")
}