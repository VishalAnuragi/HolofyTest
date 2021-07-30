package com.example.holofytest


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.util.Pair;


class MainActivity : AppCompatActivity() , VideoCallback {

    var videoRecycler : RecyclerView ?= null
   // var videoAdapter : VideoAdapter ?= null
    var mData : List<VideoModal> ?= null
    var lastItem : Int = 0

    val videomodal: Array<VideoModal> ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initRecycler()


    }

    private fun initRecycler() {


        val videomodal: Array<VideoModal> = arrayOf<VideoModal>(
            VideoModal("Bird Ronald", getString(R.string.video_details1)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Blue Ball", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
            VideoModal("Bird Animation", getString(R.string.video_details1) ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Ball Animation", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
            VideoModal("Video 1", getString(R.string.video_details1) ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Video 2", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
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

    override fun onVideoItemClick(
        pos: Int?,
        videoCard: CardView,
        video: VideoView,
        title: TextView,
        subTitle: TextView

    ) {

        val intent = Intent(this, VideoDetailsActivity::class.java)
        intent.putExtra("videoObject", videomodal?.get(pos!!))


        val p1 = Pair.create( videoCard as View, "videoCardTh")
        val p2 = Pair.create( video as View, "videoTh")
        val p3 = Pair.create( title as View, "titleTh")
        val p4 = Pair.create( subTitle as View, "subTitleTh")
      //  val p5 = Pair.create( detailsTextCard as View, "detailsTextCardTh")
     //   val p6 = Pair.create( detailsText as View, "detailsTextTh")

        val optionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3, p4)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent,optionsCompat.toBundle());
        }
        else
            startActivity(intent);


    }
    }


