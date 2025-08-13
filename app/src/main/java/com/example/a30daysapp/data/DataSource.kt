package com.example.a30daysapp.data

import com.example.a30daysapp.R
import com.example.a30daysapp.model.Tip

class DataSource () {
    fun loadTips(): List<Tip> {
        val tips = listOf(
            Tip(R.string.Tips1, R.drawable.image1, 0),
            Tip(R.string.Tips2, R.drawable.image2, 0),
            Tip(R.string.Tips3, R.drawable.image3, 0),
            Tip(R.string.Tips4, R.drawable.image4, 0),
            Tip(R.string.Tips5, R.drawable.image5, 0),
            Tip(R.string.Tips6, R.drawable.image6, 0),
            Tip(R.string.Tips7, R.drawable.image7, 0),
            Tip(R.string.Tips8, R.drawable.image8, 0),
            Tip(R.string.Tips9, R.drawable.image9, 0),
            Tip(R.string.Tips10, R.drawable.image10, 0)
        )

        return tips.mapIndexed { index, tip ->
            tip.copy(day = index)
        }
    }
}