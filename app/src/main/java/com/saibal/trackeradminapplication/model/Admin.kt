package com.saibal.trackeradminapplication.model

import java.io.Serializable

data class Admin(var id:String, var name:String,
                 var email:String, var access:Boolean
):Serializable
