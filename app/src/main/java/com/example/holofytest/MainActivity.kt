package com.example.holofytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager


import android.widget.AbsListView




class MainActivity : AppCompatActivity() {

    var videoRecycler : RecyclerView ?= null
   // var videoAdapter : VideoAdapter ?= null
    var mData : List<VideoModal> ?= null
    var lastItem : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initRecycler()


    }

    private fun initRecycler() {


        val videomodal: Array<VideoModal> = arrayOf<VideoModal>(
            VideoModal("Bird", "Child"  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Ball", "Ronald"  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
            VideoModal("Bird", "Child"  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Ball", "Ronald"  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
            VideoModal("Bird", "Child"  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Ball", "Ronald"  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
            )

        videoRecycler = findViewById(R.id.videoRecycler)
        videoRecycler!!.layoutManager = LinearLayoutManager( applicationContext , LinearLayoutManager.VERTICAL , false)
        videoRecycler!!.setHasFixedSize(true)

        val videoadapter = VideoAdapter(applicationContext , videomodal)
        videoRecycler!!.adapter = videoadapter
        videoRecycler!!.itemAnimator = DefaultItemAnimator()

        addScrollListener()

    }

    private fun addScrollListener() {

        videoRecycler!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    //get the recyclerview position which is completely visible and first
                    val positionView =
                        (videoRecycler!!.getLayoutManager() as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    Log.i("VISISBLE", positionView.toString() + "")
                  /*  if (positionView >= 0) {
                        if (oldFocusedLayout != null) {
                            //Stop the previous video playback after new scroll
                            oldFocusedLayout.findViewById(R.id.vv_dashboard).stopPlayback()
                        }
                        currentFocusedLayout =
                            (rv_featuredList.getLayoutManager() as LinearLayoutManager).findViewByPosition(
                                positionView
                            )
                        val vv_dashboard = currentFocusedLayout.findViewById(R.id.vv_dashboard)
                        //to play video of selected recylerview, videosData is an array-list which is send to recyclerview adapter to fill the view. Here we getting that specific video which is displayed through recyclerview.
                        playVideo(videosData.get(positionView))
                        oldFocusedLayout = currentFocusedLayout
                    }*/
                }


            }
        })
    }


}