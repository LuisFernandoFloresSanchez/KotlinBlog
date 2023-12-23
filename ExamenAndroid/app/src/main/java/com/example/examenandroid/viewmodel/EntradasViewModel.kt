package com.example.examenandroid.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenandroid.data.remote.RetrofitClient
import com.example.examenandroid.model.Entrada
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("MutableCollectionMutableState")
class EntradasViewModel : ViewModel() {

    var _listaEntradas: ArrayList<Entrada> by mutableStateOf(arrayListOf())
    private val _entradaDetalle: MutableLiveData<Entrada?> = MutableLiveData(null)
    val entradaDetalle: LiveData<Entrada?> get() = _entradaDetalle
    var _query: String by mutableStateOf("")

    fun getEntradas() {
        val currentQuery = _query
        viewModelScope.launch(Dispatchers.IO) {
            val response = if (currentQuery.isNotEmpty()) {
                RetrofitClient.webService.searchEntradas(currentQuery)
            } else {
                RetrofitClient.webService.getEntradas()
            }

            withContext(Dispatchers.Main) {
                if (response.body()?.codigo == "200") {
                    _listaEntradas = (response.body()?.data ?: emptyList()) as ArrayList<Entrada>
                }
            }
        }
    }

    fun getEntradaById(idEntrada: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val response = RetrofitClient.webService.getEntradaById(idEntrada)

            withContext(Dispatchers.Main) {
                if (response.body()?.codigo == "200") {
                    _entradaDetalle.value = response.body()?.data?.firstOrNull()
                    Log.wtf("getEntradaById", "Entrada recibida: ${response.body()?.data?.firstOrNull()}")

                }else {
                    Log.wtf("getEntradaById", "Respuesta del servidor sin éxito: ${response.body()}")
                }
            }

        }
    }


    fun addEntrada(entrada: Entrada) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.addEntradas(entrada)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    getEntradas()
                }
            }
        }
    }

    fun deleteEntrada(idEntrada: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.deleteEntrada(idEntrada)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    getEntradas()
                }
            }
        }
    }

    fun setQuery(newQuery: String) {
        _query = newQuery
    }

}