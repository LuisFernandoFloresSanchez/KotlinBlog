package com.example.examenandroid.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily.Default,
    h1 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
    ),
    h2 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    h3 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    h5 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    ),

    /* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
)