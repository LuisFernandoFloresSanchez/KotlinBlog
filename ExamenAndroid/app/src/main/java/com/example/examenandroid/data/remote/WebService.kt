package com.example.examenandroid.data.remote

import com.example.examenandroid.data.repository.EntradasResponse
import com.example.examenandroid.model.Entrada
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface WebService {

    @GET("/entradas")
    suspend fun getEntradas(): Response<EntradasResponse>

    @GET("/entradas/{id}")
    suspend fun getEntradaById(@Path("id") id: String): Response<EntradasResponse>

    @GET("/entrada")
    suspend fun searchEntradas(@Query("q") query: String): Response<EntradasResponse>

    @POST("/entradas/agregar")
    suspend fun addEntradas(
        @Body entrada: Entrada
    ): Response<EntradasResponse>

    @PUT("/entradas/actualizar/{id}")
    suspend fun updateEntrada(
        @Path("id") id_usuario: String,
        @Body entrada: Entrada
    ): Response<EntradasResponse>

    @DELETE("/entradas/borrar/{id}")
    suspend fun deleteEntrada(
        @Path("id") id_usuario: String
    ): Response<EntradasResponse>
}