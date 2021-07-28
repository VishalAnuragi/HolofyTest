package com.example.holofytest

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

var packageName : String ?= "com.example.holofytest"
var vidLink : String ?= null

class VideoAdapter (
    private val context: Context,
    private val arr: Array<VideoModal>
) : RecyclerView.Adapter<VideoAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemCard = itemView.findViewById<CardView>(R.id.itemCard)
        val itemVideo = itemView.findViewById<VideoView>(R.id.itemVideo)
        val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
        val itemSubTitle = itemView.findViewById<TextView>(R.id.itemSubTitle)


        fun bindView(vid: VideoModal) {
            itemVideo.setVideoURI(Uri.parse(vidLink))
            itemTitle.text = vid.title
            itemSubTitle.text = vid.subtitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_videos, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(arr[position])

        when(position) {

            0 -> vidLink = "android.resource://" + packageName + "/" + R.raw.eggs
            1 -> vidLink = "android.resource://" + packageName + "/" + R.raw.eggs
            2 -> vidLink = "android.resource://" + packageName + "/" + R.raw.eggs
        }

        holder.itemCard.setOnClickListener {


        }
    }
}