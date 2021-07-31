package com.example.holofytest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

var callback2: VideoCallback?= null

class VideoAdapter(videomodal: Array<VideoModal>, mainActivity: MainActivity) : RecyclerView.Adapter<VideoAdapter.videoviewholder>() {

    var mdata: Array<VideoModal> = videomodal
    val callback: VideoCallback?= mainActivity




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): videoviewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_videos, parent, false)
        callback2 = callback

        return videoviewholder(view)

    }

    override fun onBindViewHolder(holder: videoviewholder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mdata!!.size
    }

    class videoviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        init {
            val itemCard = itemView.findViewById<CardView>(R.id.itemCard)
           // var linearLayout = itemView.findViewById<LinearLayout>(R.id.ll)
            var itemVideo  = itemView.findViewById<VideoView>(R.id.itemVideo)
            val itemTitle = itemView.findViewById<TextView>(R.id.itemTitle)
            val itemSubTitle = itemView.findViewById<TextView>(R.id.itemSubTitle)

            // itemVideo.start();

            //  Log.i("TAG123S" , visibleItem.toString())
            itemView.setOnClickListener { callback2!!.onVideoItemClick( adapterPosition ,
                itemCard,
              //  linearLayout,
                itemVideo,
                itemTitle,
                itemSubTitle)


            }
            }
        }
    }
