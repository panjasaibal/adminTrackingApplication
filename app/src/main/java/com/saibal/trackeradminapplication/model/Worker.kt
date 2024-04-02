package com.saibal.trackeradminapplication.model

import java.io.Serializable

data class Worker(var _id:String, var name: String, var phone:String ,
                  var adhar:String, var status: String, var timestamp:String): Serializable
