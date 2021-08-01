package com.example.holofytest

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(mdata: List<VideoModal>, callback: VideoCallback) : RecyclerView.Adapter<VideoAdapter.videoviewholder>() {
    var mdata: List<VideoModal>
    var callback: VideoCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): videoviewholder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_videos, parent, false)
        return videoviewholder(view)
    }

    override fun onBindViewHolder(holder: videoviewholder, position: Int) {

        holder.vidTitle.text = mdata.get(position).title
        holder.vidSubTitle.text = mdata.get(position).subtitle

        var vv = holder.vidVideo
        vv.setVideoPath(mdata.get(position).videoURL.toString())
        vv.setOnPreparedListener { mp -> setVolumeControl(mp) }
        vv.start()

    }

    private fun setVolumeControl(mp: MediaPlayer?) {
        mp!!.setVolume(0F, 0F)
    }

    override fun getItemCount(): Int { return mdata.size
    }

    inner class videoviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vidCard: CardView
        var vidVideo: VideoView
        var vidTitle: TextView
        var vidSubTitle: TextView

        init {
            vidCard = itemView.findViewById(R.id.itemCard)
            vidVideo = itemView.findViewById(R.id.itemVideo)
            vidTitle = itemView.findViewById(R.id.itemTitle)
            vidSubTitle = itemView.findViewById(R.id.itemSubTitle)


            itemView.setOnClickListener {

                callback!!.onVideoItemClick( adapterPosition ,
                                                vidCard,
                                                vidVideo,
                                                vidTitle,
                                                vidSubTitle)

            }
        }
    }

    init {
        this.mdata = mdata
        this.callback = callback
    }
}
