package com.saibal.trackeradminapplication.model

import java.io.Serializable

data class TrackerResponse(
    val data:Tracker, val workerName:String, val phone:String
):Serializable
