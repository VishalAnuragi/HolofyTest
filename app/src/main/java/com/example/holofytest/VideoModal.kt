package com.example.holofytest

import java.io.Serializable

class VideoModal : Serializable {
    var title :String ?= null
    var subtitle : String ?= null
    var videoURL : String ?= null

    constructor()
    constructor(title: String?, subtitle: String?, videoURL: String?) {
        this.title = title
        this.subtitle = subtitle
        this.videoURL = videoURL
    }


}


