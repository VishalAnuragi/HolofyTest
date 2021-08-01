package com.example.holofytest

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView

class VideoDetailsActivity : AppCompatActivity() {

    var videoView : VideoView ?= null
    var title : TextView ?= null
    var subtitle : TextView ?= null
    var details : TextView ?= null
    var volumeBtn : ImageButton ?= null
    var volumePlaying = true

    var mediaController : MediaController ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_details)

        initViews()
        initData()

    }

    private fun initData() {
        val Title = intent.extras!!.getString("videoTitle")
        val subTitle = intent.extras!!.getString("videoSubTitle")
        val path = intent.extras!!.getString("videoUrl")

        loadVideoData(Title , subTitle , path)
    }

    private fun initViews() {

        videoView = findViewById(R.id.detailsVideo)
        title = findViewById(R.id.detailsTitle)
        subtitle = findViewById(R.id.detailsSubTitle)
        details = findViewById(R.id.detailsText)
        volumeBtn = findViewById(R.id.speakerBtn)
    }

    private fun loadVideoData(Title: String?, subTitle: String?, path: String?) {

        title!!.text = Title.toString()
        subtitle!!.text = subTitle.toString()
        details!!.text = subTitle.toString()

        val uri: Uri = Uri.parse(path)
        videoView!!.setVideoURI(uri)

        mediaController = MediaController(this)
        videoView!!.setMediaController(mediaController)
        mediaController!!.setAnchorView(videoView)

        videoView!!.setOnPreparedListener { mp -> setVolumeControl(mp) }

        videoView!!.start()
    }

    private fun setVolumeControl(mp: MediaPlayer?) {

       // mp!!.seekTo(15*1000)
        volumeBtn!!.setOnClickListener {
          //  var duration = mp!!.currentPosition
            if (volumePlaying){
                mp!!.setVolume(0F, 0F)
                volumeBtn!!.background = getDrawable(R.drawable.ic_baseline_speaker_off)
            }
            else {
                mp!!.setVolume(1F, 1F)
                volumeBtn!!.background = getDrawable(R.drawable.ic_baseline_speaker_on)
            }
            volumePlaying = !volumePlaying
        }


    }
}