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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.controller.WorkerAdapter
import com.saibal.trackeradminapplication.model.Worker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList

class WorkersFragment : Fragment() {

    private  lateinit var adminId:String
    private  lateinit var data:String
    var workersList= ArrayList<Worker>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_workers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adminId = arguments?.getString(Utill.ADMIN_STORE_KEY,"").toString()
        data = arguments?.getString(Utill.JSONARRAY_STORE_KEY,"").toString()
        fetchAllWorkers()
        if(workersList.size ==0){
            Toast.makeText(mContext,"Save me",Toast.LENGTH_SHORT).show()
        }
        recyclerView = view.findViewById(R.id.workerRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        var adapter = WorkerAdapter(mContext,workersList)
        recyclerView.adapter = adapter

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun fetchAllWorkers(){

        var jsonArray: JSONArray = JSONArray(data)

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