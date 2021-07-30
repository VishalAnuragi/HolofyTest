package com.example.holofytest

import android.content.Context

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
var mdata: List<VideoModal>? = null
var callback: VideoCallback? = null

class VideoAdapter (
    private val context: Context,
    private val arr: Array<VideoModal> , ) : RecyclerView.Adapter<VideoAdapter.ImageViewHolder>() {




    fun VideoAdapter(mdata2: List<VideoModal>, callback2: VideoCallback) {
        mdata = mdata2
        callback = callback2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_videos, parent, false))


    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        holder.bindView(arr[position])
        p = position

        Log.i("TAG123" , p.toString())



    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {








        fun bindView(vid: VideoModal) {

            val itemCard = itemView.findViewById<CardView>(R.id.itemCard)
            var itemVideo  = itemView.findViewById<VideoView>(R.id.itemVideo)
            val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
            val itemSubTitle = itemView.findViewById<TextView>(R.id.itemSubTitle)

            vidLink = vid.videoURL
            itemTitle.text = vid.title
            itemSubTitle.text = vid.subtitle

            itemVideo.setVideoPath(vidLink);

            itemVideo.start();


            var fi : MainActivity = MainActivity()

            visibleItem = fi.lastItem
            Log.i("TAG123" , visibleItem.toString())

            itemView.setOnClickListener {

                callback!!.onVideoItemClick(p ,
                    itemCard,
                    itemVideo,
                    itemTitle,
                    itemSubTitle

                )
            }
        }
    }
}