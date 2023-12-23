package com.example.examenandroid.ui.entradas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examenandroid.model.Entrada
import com.example.examenandroid.navigation.AppScreens
import com.example.examenandroid.ui.components.CardEntrada
import com.example.examenandroid.ui.theme.PinkPrimaryD
import com.example.examenandroid.ui.theme.PinkPrimaryL
import com.example.examenandroid.ui.theme.backgroundL
import com.example.examenandroid.utils.NetworkUtils
import com.example.examenandroid.viewmodel.EntradasViewModel

@Composable
fun ListEntradas(navController: NavController, viewModel: EntradasViewModel) {
    BodyContent(navController, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(navController: NavController, viewModel: EntradasViewModel) {
    val isNetworkAvailable = NetworkUtils.isNetworkAvailable(LocalContext.current)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Blog app") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    titleContentColor = backgroundL,
                    actionIconContentColor = backgroundL,
                    scrolledContainerColor = backgroundL,
                    navigationIconContentColor = backgroundL,
                    containerColor = PinkPrimaryD
                ),
                actions = {
                    // IconButton para el botón de "+"
                    IconButton(
                        onClick = {
                            navController.navigate(AppScreens.AgregarEntrada.route)
                        },
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar")
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 60.dp, 0.dp, 0.dp)
        ) {
            if (isNetworkAvailable) {
                viewModel.getEntradas()
                ExamScreen(viewModel._listaEntradas, viewModel, navController)
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun ExamScreen(
    listaEntradas: ArrayList<Entrada>,
    viewModel: EntradasViewModel,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Barra de búsqueda
                var query by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = query,
                    onValueChange = {
                        query = it
                        viewModel.setQuery(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    label = { Text("Buscar") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimaryL,
                        focusedLabelColor = PinkPrimaryL
                    )
                )
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(listaEntradas) { entrada ->
                        CardEntrada(
                            entrada = entrada,
                            funBorrarEntrada = {
                                viewModel.deleteEntrada(entrada.idEntrada)
                            },
                            funVerEntrada = {
                                navController.navigate(AppScreens.DetalleEntrada.route + "/${entrada.idEntrada}")

                            }
                        )
                    }
                }
            }
        }
    }
}