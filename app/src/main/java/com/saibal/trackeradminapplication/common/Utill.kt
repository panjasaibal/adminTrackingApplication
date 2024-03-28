package com.saibal.trackeradminapplication.common

import android.content.Context
import android.content.SharedPreferences

class Utill {


    //private lateinit var sharedPreferences: SharedPreferences;


    companion object{

        public var SHARED_PREF:String = "shared_pref"
        public var ADMIN_STORE_KEY = "admin_store_key"
        public var ADMIN_INTENT_KEY = "admin_intent_key"
        public var READ_FROM_SHARED_PREF = "READ_FROM_SHARED_PREF"
        public var WRITE_TO_SHARED_PREF = "WRITE_TO_SHARED_PREF"
        public var MY_IP = "192.168.1.3"
        public var BASE_URL = "http://$MY_IP:5000/api/admin"

        fun handleSharedPref(context: Context, sharedPreferences: SharedPreferences, type:String, data:String):String?{
            //sharedPreferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)
            if(type== READ_FROM_SHARED_PREF){
                    var data = sharedPreferences.getString(ADMIN_STORE_KEY,"")
                    return data
                }

            else if (type == WRITE_TO_SHARED_PREF){
                    var editor = sharedPreferences.edit()
                    editor.putString(ADMIN_STORE_KEY,data)
                    editor.apply()
                    return data
                }
            else{
                return ""
            }
        }


    }
}