package com.example.examenandroid.ui.entradas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examenandroid.model.Entrada
import com.example.examenandroid.ui.theme.PinkPrimaryD
import com.example.examenandroid.ui.theme.PinkPrimaryL
import com.example.examenandroid.ui.theme.backgroundL
import com.example.examenandroid.viewmodel.EntradasViewModel
import java.time.Instant
import java.time.ZoneId


@ExperimentalMaterial3Api
@Composable
fun AgregarEntrada(
    navController: NavController, viewModel: EntradasViewModel
) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var contenido by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var showDateDialog by remember {
        mutableStateOf(false)
    }
    // Botón para guardar la entrada
    val saveButton: @Composable () -> Unit = {
        Button(
            onClick = {
                viewModel.addEntrada(
                    Entrada(
                        idEntrada = "",
                        titulo = titulo,
                        autor = autor,
                        fecha = fecha,
                        contenido = contenido
                    )
                )
                navController.popBackStack()
            }, colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, containerColor = PinkPrimaryL
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Save, contentDescription = "Guardar")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guardar")
        }
    }

    // Contenido de la actividad

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


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 66.dp, 16.dp, 16.dp)
    ) {
        // Título
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            leadingIcon = { Icon(Icons.Default.Title, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkPrimaryL, focusedLabelColor = PinkPrimaryL
            )
        )

        // Autor
        OutlinedTextField(
            value = autor,
            onValueChange = { autor = it },
            label = { Text("Autor") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkPrimaryL, focusedLabelColor = PinkPrimaryL
            )
        )
        val state = rememberDatePickerState();
        // Fecha de publicación
        OutlinedTextField(
            value = fecha,
            onValueChange = {
                fecha = it
            },
            label = { Text("Fecha de publicación") },
            leadingIcon = {
                IconButton(onClick = {
                    showDateDialog = true
                }) {
                    Icon(Icons.Default.Today, contentDescription = null)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            readOnly = true, // Campo de solo lectura
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkPrimaryL, focusedLabelColor = PinkPrimaryL
            ),

            )

        if (showDateDialog) {
            DatePickerDialog(onDismissRequest = {
                showDateDialog = false
            }, confirmButton = {
                Button(
                    onClick = {
                        showDateDialog = false
                        val date = state.selectedDateMillis
                        date.let {

                            val localDate = it?.let { it1 ->
                                Instant.ofEpochMilli(it1).atZone(ZoneId.of("UTC")).toLocalDate()
                            }
                            if (localDate != null) {
                                fecha =
                                    "${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}"
                            }
                        }
                    }, colors = ButtonDefaults.buttonColors(
                        contentColor = backgroundL, containerColor = PinkPrimaryD
                    )
                ) {
                    Text(text = "Confirmar")
                }
            }, dismissButton = {
                OutlinedButton(
                    onClick = { showDateDialog = false }, colors = ButtonDefaults.buttonColors(
                        contentColor = PinkPrimaryD,
                        containerColor = backgroundL,

                        )
                ) {
                    Text(text = "Cancelar")
                }
            }, colors = DatePickerDefaults.colors(
                containerColor = backgroundL,
            )
            ) {
                DatePicker(
                    state = state, colors = DatePickerDefaults.colors(
                        selectedDayContainerColor = PinkPrimaryL,
                        todayDateBorderColor = PinkPrimaryL,
                        titleContentColor = PinkPrimaryL,
                        todayContentColor = PinkPrimaryL,
                        selectedYearContainerColor = PinkPrimaryL,
                        subheadContentColor = PinkPrimaryL,
                        currentYearContentColor = PinkPrimaryL

                    )
                )
            }
        }

        // Contenido
        OutlinedTextField(
            value = contenido,
            onValueChange = { contenido = it },
            label = { Text("Contenido") },
            leadingIcon = {
                Icon(
                    Icons.Default.DateRange, contentDescription = null
                )
            },
            maxLines = Int.MAX_VALUE,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkPrimaryL, focusedLabelColor = PinkPrimaryL
            )
        )

        // Botón de guardar
        saveButton()
    }
}

