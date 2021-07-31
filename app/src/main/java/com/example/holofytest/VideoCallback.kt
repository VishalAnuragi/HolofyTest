package com.example.holofytest

import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView


interface VideoCallback {

    fun onVideoItemClick(pos : Int?,
                         videoCard : CardView,
                        // linear : LinearLayout,
                         video : VideoView,
                         title : TextView,
                         subTitle : TextView
    )
}