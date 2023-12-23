package com.example.examenandroid.data.repository

import com.example.examenandroid.model.Entrada

data class EntradasResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Entrada>
)
