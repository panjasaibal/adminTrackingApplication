package com.saibal.trackeradminapplication.common

import android.content.Context
import android.content.SharedPreferences

class Utill {


    //private lateinit var sharedPreferences: SharedPreferences;


    companion object{
        public var SHARED_PREF:String = "shared_pref"
        public var ADMIN_STORE_KEY = "admin_store_key"
        public var ADMIN_NAME_STORE_KEY = "admin_name_store_key"
        public var ADMIN_EMAIL_STORE_KEY = "admin_email_store_key"
        public var WORKER_STORE_KEY = "worker_store_key"
        public var ADMIN_INTENT_KEY = "admin_intent_key"
        public var READ_FROM_SHARED_PREF = "READ_FROM_SHARED_PREF"
        public var WRITE_TO_SHARED_PREF = "WRITE_TO_SHARED_PREF"
        public var MY_IP = "192.168.150.72"
        public var BASE_URL = "http://$MY_IP:5000/api/admin"
        public var JSONARRAY_STORE_KEY = "json_array_store_key"
        public var JSONARRAY_OF_TRACKER_STORE_KEY = "json_array_of_tracker_store_key"

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