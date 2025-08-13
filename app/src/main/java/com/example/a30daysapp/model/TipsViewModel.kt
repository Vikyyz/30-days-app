package com.example.a30daysapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.a30daysapp.data.DataSource

class TipsViewModel : ViewModel() {
    var tips = mutableStateListOf<Tip>()
        private set

    init {
        tips.addAll(DataSource().loadTips())
    }

    fun removeTip(tip: Tip) {
        tips.remove(tip)
    }
}