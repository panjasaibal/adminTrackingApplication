package com.saibal.trackeradminapplication.model

import java.io.Serializable

data class Tracker(var _id:String, var timestamp:String, var date:String, var worker:String,
                   var latitude:String, var longitude:String):Serializable
