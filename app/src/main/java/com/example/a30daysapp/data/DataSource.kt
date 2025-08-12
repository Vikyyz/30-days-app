package com.example.a30daysapp.data

import com.example.a30daysapp.R
import com.example.a30daysapp.model.Tip

class DataSource () {
    fun loadTips () : List<Tip> {
        return listOf<Tip>(
            Tip(R.string.Tips1, R.drawable.image1),
            Tip(R.string.Tips2, R.drawable.image2),
            Tip(R.string.Tips3, R.drawable.image3),
            Tip(R.string.Tips4, R.drawable.image4),
            Tip(R.string.Tips5, R.drawable.image5),
            Tip(R.string.Tips6, R.drawable.image6),
            Tip(R.string.Tips7, R.drawable.image7),
            Tip(R.string.Tips8, R.drawable.image8),
            Tip(R.string.Tips9, R.drawable.image9),
            Tip(R.string.Tips10, R.drawable.image10))
    }
}