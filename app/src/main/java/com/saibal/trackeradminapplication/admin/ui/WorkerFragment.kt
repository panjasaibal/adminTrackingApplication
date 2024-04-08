package com.saibal.trackeradminapplication.admin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.model.Worker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException


class WorkerFragment : Fragment() {

    private lateinit var data:String
    private lateinit var mContext:Context
    var workersList = ArrayList<Worker>()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_worker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.getString(Utill.JSONARRAY_STORE_KEY,"").toString()
        if(data!=""){
            fetchDetailsOfWroker()
        }

        if(workersList.size ==0){
            Toast.makeText(mContext,"save me", Toast.LENGTH_LONG).show()
        }
    }
    private fun fetchDetailsOfWroker(){

       var jsonArray:JSONArray = JSONArray(data)

        for(i in 0 until jsonArray.length()){
            try{
                var jsonObject = jsonArray.getJSONObject(i)
                var gson = Gson()
                var todoJson:String = jsonObject.toString()
                var worker = gson.fromJson(todoJson, Worker::class.java)
                Log.i("wck",worker.name)
                workersList.add(worker)
            }catch(e: JSONException){
                Log.e("jsonexxception", e.stackTraceToString())
            }
        }

    }

    
}