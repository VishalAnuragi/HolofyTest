package com.example.holofytest

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

var packageName : String ?= "com.example.holofytest"
var vidLink : String ?= null
var visibleItem : Int ?= 0
var p : Int  ?= 0

class VideoAdapter (
    private val context: Context,
    private val arr: Array<VideoModal>
) : RecyclerView.Adapter<VideoAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemCard = itemView.findViewById<CardView>(R.id.itemCard)
        var itemVideo  = itemView.findViewById<VideoView>(R.id.itemVideo)
        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
        val itemSubTitle = itemView.findViewById<TextView>(R.id.itemSubTitle)



        fun bindView(vid: VideoModal) {

            vidLink = vid.videoURL
            itemTitle.text = vid.title
            itemSubTitle.text = vid.subtitle

            itemVideo.setVideoPath(vidLink);

                itemVideo.start();


            var fi : MainActivity = MainActivity()

            visibleItem = fi.lastItem
            Log.i("TAG123" , visibleItem.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_videos, parent, false))


    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        holder.bindView(arr[position])
        p = position

        Log.i("TAG123" , p.toString())


        holder.itemCard.setOnClickListener {


        }
    }
}