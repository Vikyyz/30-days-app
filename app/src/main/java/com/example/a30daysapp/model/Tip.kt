package com.example.a30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip (
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val day: Int
)