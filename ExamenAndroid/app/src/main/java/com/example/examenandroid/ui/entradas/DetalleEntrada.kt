package com.example.examenandroid.ui.entradas

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examenandroid.model.Entrada
import com.example.examenandroid.ui.theme.PinkPrimaryD
import com.example.examenandroid.ui.theme.SecondaryD
import com.example.examenandroid.ui.theme.TertiaryD
import com.example.examenandroid.ui.theme.backgroundL
import com.example.examenandroid.viewmodel.EntradasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleEntrada(navController: NavController, idEntrada: String?, viewModel: EntradasViewModel) {
    if (idEntrada != null) {
        viewModel.getEntradaById(idEntrada)
    }
    val entrada: Entrada? by viewModel.entradaDetalle.observeAsState()

    TopAppBar(
        title = { Text("Blog app") },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            titleContentColor = backgroundL,
            actionIconContentColor = backgroundL,
            scrolledContainerColor = backgroundL,
            navigationIconContentColor = backgroundL,
            containerColor = PinkPrimaryD
        ),
    )

    // Diseño de la pantalla de detalle
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 66.dp, 16.dp, 16.dp)
    ) {
        // Título centrado con color SecondaryD y tamaño más grande
        if (entrada != null) {

            Text(
                text = entrada!!.titulo,
                color = PinkPrimaryD,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .wrapContentSize(Alignment.Center),
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Autor alineado a la izquierda, color diferente y tamaño más pequeño
            Text(
                text = "Autor: ${entrada!!.autor}",
                color = TertiaryD, // Cambia al color que prefieras
                modifier = Modifier.padding(bottom = 4.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal
                )
            )

            // Fecha alineada a la izquierda, color diferente y tamaño más pequeño
            Text(
                text = "Fecha: ${entrada!!.fecha}",
                color = SecondaryD, // Cambia al color que prefieras
                modifier = Modifier.padding(bottom = 55.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            )

            // Contenido completo con borde destacable
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, PinkPrimaryD)
                    .padding(8.dp, 16.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = entrada!!.contenido,
                    color = PinkPrimaryD,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}