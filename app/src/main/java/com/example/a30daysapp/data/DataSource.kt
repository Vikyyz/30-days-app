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
            Tip(R.string.Tips10, R.drawable.image10, 0),
            Tip(R.string.Tips11, R.drawable.image11, 0),
            Tip(R.string.Tips12, R.drawable.image12, 0),
            Tip(R.string.Tips13, R.drawable.image13, 0),
            Tip(R.string.Tips14, R.drawable.image14, 0),
            Tip(R.string.Tips15, R.drawable.image15, 0),
            Tip(R.string.Tips16, R.drawable.image16, 0),
            Tip(R.string.Tips17, R.drawable.image17, 0),
            Tip(R.string.Tips18, R.drawable.image18, 0),
            Tip(R.string.Tips19, R.drawable.image19, 0),
            Tip(R.string.Tips20, R.drawable.image20, 0),
            Tip(R.string.Tips21, R.drawable.image21, 0),
            Tip(R.string.Tips22, R.drawable.image22, 0),
            Tip(R.string.Tips23, R.drawable.image23, 0),
            Tip(R.string.Tips24, R.drawable.image24, 0),
            Tip(R.string.Tips25, R.drawable.image25, 0),
            Tip(R.string.Tips26, R.drawable.image26, 0),
            Tip(R.string.Tips27, R.drawable.image27, 0),
            Tip(R.string.Tips28, R.drawable.image28, 0),
            Tip(R.string.Tips29, R.drawable.image29, 0),
            Tip(R.string.Tips30, R.drawable.image30, 0)
        )

        return tips.mapIndexed { index, tip ->
            tip.copy(day = index)
        }
    }
}