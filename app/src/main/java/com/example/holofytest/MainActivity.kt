package com.example.holofytest


import android.content.Intent


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.util.Pair;
import java.util.ArrayList


class MainActivity : AppCompatActivity() , VideoCallback {

    var videoRecycler : RecyclerView ?= null

    var videoAdapter : VideoAdapter ?= null
    var videomodal: ArrayList<VideoModal> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoRecycler = findViewById(R.id.videoRecycler)
        initmData()
        initRecycler()

    }

    private fun initmData() {

        videomodal = ArrayList<VideoModal>()
        videomodal!!.add(VideoModal("Bird Ronald", getString(R.string.video_details1)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs))
        videomodal!!.add(VideoModal("Blue Ball", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball))
        videomodal!!.add(VideoModal("Bird Animation", getString(R.string.video_details1) ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs))
        videomodal!!.add(VideoModal("Ball Animation", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball))
        videomodal!!.add(VideoModal("Video 1", getString(R.string.video_details1) ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs))
        videomodal!!.add(VideoModal("Video 2", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball))

    }

    public fun initRecycler() {

        videoRecycler!!.setLayoutManager(LinearLayoutManager(this))
        videoRecycler!!.setHasFixedSize(true)

        videoAdapter = VideoAdapter(videomodal!!, this)
        videoRecycler!!.setAdapter(videoAdapter)

    }

    override fun onVideoItemClick( pos: Int?,
                                   videoCard: CardView,
                                   video: VideoView,
                                   title: TextView,
                                   subTitle: TextView) {

        val intent = Intent(this, VideoDetailsActivity::class.java)
        intent.putExtra("videoTitle", videomodal?.get(pos!!)!!.title.toString())
        intent.putExtra("videoSubTitle", videomodal?.get(pos!!)!!.subtitle.toString())
        intent.putExtra("videoUrl", videomodal?.get(pos!!)!!.videoURL.toString())



        val p1 = Pair.create(videoCard as View, "videoCardTh")
        val p3 = Pair.create(video as View, "videoTh")
        val p4 = Pair.create(title as View, "titleTh")
        val p5 = Pair.create(subTitle as View, "subTitleTh")
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p3, p4, p5)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent,optionsCompat.toBundle());
        }
        else
            startActivity(intent);
    }
    }


