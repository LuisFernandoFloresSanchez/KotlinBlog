package com.example.examenandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenandroid.R
import com.example.examenandroid.model.Entrada
import com.example.examenandroid.ui.theme.PinkPrimaryD
import com.example.examenandroid.ui.theme.TertiaryD
import com.example.examenandroid.ui.theme.backgroundL
import com.example.examenandroid.ui.theme.textColorOpa
import com.example.examenandroid.ui.theme.textNoBlack

@Composable
fun CardEntrada(
    entrada: Entrada,
    funBorrarEntrada: (String) -> Unit,
    funVerEntrada: (Entrada) -> Unit,

    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            Arrangement.Center
        ) {
            // Fecha en posición superior derecha
            Text(
                text = entrada.fecha,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp),
                color = textColorOpa

            )
            // Título en negrita y más grande
            Text(
                text = entrada.titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = TertiaryD
            )

            // Autor en cursiva y más grande que el texto regular
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Icono de persona",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 4.dp),

                    )
                Text(
                    text = entrada.autor,
                    color = textNoBlack
                )
            }



            Spacer(modifier = Modifier.padding(8.dp))
            // Contenido con espacio entre el autor
            val contenidoToShow = entrada.contenido?.take(60)
            val contenidoText = if ((entrada.contenido?.length ?: 0) > 60) {
                "$contenidoToShow..."
            } else {
                contenidoToShow.orEmpty()
            }

            Text(
                text = contenidoText,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = {
                        funVerEntrada(entrada)
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = backgroundL,
                        containerColor = PinkPrimaryD
                    )
                ) {
                    Text(
                        text = "Ver"
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = {
                        funBorrarEntrada(entrada.idEntrada)
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = backgroundL,
                        containerColor = TertiaryD
                    )

                ) {
                    Text(
                        text = "Borrar"
                    )
                }
            }
        }
    }
}