package com.example.a30daysapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.a30daysapp.R

private val DarkColorScheme = darkColorScheme(
    primary = Surface,
    secondary = Surface,
    tertiary = Surface
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = Surface,
    onSurface = onSurface,
)

@Composable
fun _30DaysAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val LexendGiga = FontFamily(
        Font(R.font.lexend_giga, weight = FontWeight.Normal)
    )
    val AbrilFatface = FontFamily(
        Font(R.font.abril_fatface, weight = FontWeight.Normal)
    )

    val MyTypography = androidx.compose.material3.Typography(
        bodyLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = AbrilFatface,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        titleLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = AbrilFatface,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        ),
        labelSmall = androidx.compose.ui.text.TextStyle(
            fontFamily = AbrilFatface,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        headlineSmall = androidx.compose.ui.text.TextStyle(
            fontFamily = AbrilFatface,
            fontWeight = FontWeight.Thin,
            fontSize = 10.sp
        ),
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MyTypography,
        content = content,
    )
}