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


class MainActivity : AppCompatActivity() , VideoCallback {

    var videoRecycler : RecyclerView ?= null
    val videomodal: Array<VideoModal> ?= null
    var videoAdapter : VideoAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

    }

    public fun initRecycler() {


        val videomodal: Array<VideoModal> = arrayOf<VideoModal>(
            VideoModal("Bird Ronald", getString(R.string.video_details1)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Blue Ball", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
            VideoModal("Bird Animation", getString(R.string.video_details1) ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Ball Animation", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
            VideoModal("Video 1", getString(R.string.video_details1) ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.eggs),
            VideoModal("Video 2", getString(R.string.video_details2)  ,"android.resource://" + packageName + "/" + com.example.holofytest.R.raw.ball),
        )

        videoRecycler = findViewById(R.id.videoRecycler)
        var llm : LinearLayoutManager = LinearLayoutManager( applicationContext , LinearLayoutManager.VERTICAL , false)
        videoRecycler!!.layoutManager = llm

        videoAdapter = VideoAdapter(videomodal , this)
        videoRecycler!!.setAdapter(videoAdapter)
        videoRecycler!!.hasFixedSize()

        videoRecycler!!.adapter = videoAdapter
        videoRecycler!!.itemAnimator = DefaultItemAnimator()



        val myLayoutManager: LinearLayoutManager = videoRecycler!!.getLayoutManager() as LinearLayoutManager
        val firstVisiblePosition = myLayoutManager.findFirstCompletelyVisibleItemPosition()

       Log.i("TAG123S" , firstVisiblePosition.toString())

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
                    val positionView = (videoRecycler!!.getLayoutManager() as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                    var oldFocusedLayout = (videoRecycler!!.getLayoutManager() as LinearLayoutManager).findViewByPosition(
                            positionView
                        )
                    Log.i("VISISBLE", positionView.toString() + "")
                    if (positionView >= 0) {
                        if (oldFocusedLayout != null) {
                            //Stop the previous video playback after new scroll
                         //   oldFocusedLayout.findViewById(R.id.itemVideo).stopPlayback()
                        }
                        var currentFocusedLayout =
                            (videoRecycler!!.getLayoutManager() as LinearLayoutManager).findViewByPosition(
                                positionView
                            )
                        //to play video of selected recylerview, videosData is an array-list which is send to recyclerview adapter to fill the view. Here we getting that specific video which is displayed through recyclerview.
                      //  (VideoAdapter.get(positionView))
                        oldFocusedLayout = currentFocusedLayout
                    }
                }
            }
        })
        
    }

    override fun onVideoItemClick(
        pos: Int?,
        videoCard: CardView,
       // linear: LinearLayout,
        video: VideoView,
        title: TextView,
        subTitle: TextView
    ) {

        val intent = Intent(this, VideoDetailsActivity::class.java)
        intent.putExtra("videoObject", videomodal?.get(pos!!))



        val p1 = Pair.create(videoCard as View, "videoCardTh")

      //  val p2 = Pair.create(linear as View, "linearTh")

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


