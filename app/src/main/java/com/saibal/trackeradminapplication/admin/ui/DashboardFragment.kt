package com.saibal.trackeradminapplication.admin.ui

import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.controller.TrackerAdapter
import com.saibal.trackeradminapplication.model.Admin
import com.saibal.trackeradminapplication.model.Tracker
import com.saibal.trackeradminapplication.model.TrackerResponse
import com.saibal.trackeradminapplication.model.Worker
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private  lateinit var admin:String
    private  lateinit var adminName:String
    private  lateinit var adminEmail:String
    private lateinit var mContext: Context
    private lateinit var nameTv:TextView
    private lateinit var emailTv:TextView
    private lateinit var trackerRv:RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            admin = this.arguments?.getString(Utill.ADMIN_STORE_KEY,"").toString()
            adminName = this.arguments?.getString(Utill.ADMIN_NAME_STORE_KEY,"").toString()
            adminEmail = this.arguments?.getString(Utill.ADMIN_EMAIL_STORE_KEY,"").toString()

        //Toast.makeText(mContext,this.adminId,Toast.LENGTH_LONG).show()
        nameTv = view.findViewById(R.id.nameTv)
        emailTv = view.findViewById(R.id.emailTv)
        trackerRv = view.findViewById(R.id.trackListRv)
       // trackerRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        nameTv.text = adminName
        emailTv.text = adminEmail

        //loadTracker(mContext)
        //context?.let { loadTracker(it) }
//        adapter = TrackerAdapter(mContext,tracks)
       trackerRv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
//        trackerRv.adapter = adapter

        loadTracker()



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun loadTracker(){

        var url:String = Utill.BASE_URL+"/getTracks/"+admin
        var requestQueue: RequestQueue = Volley.newRequestQueue(mContext)
        var tracks = ArrayList<TrackerResponse>()
        var jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url,null, { response->

            if(response.length()==0){
                Toast.makeText(context,"No data found", Toast.LENGTH_SHORT).show()

            }else{
                var data = response.toString()
                var jsonArray: JSONArray = JSONArray(data)

                for(i in 0 until jsonArray.length()){
                        try {
                            var jsonObject = jsonArray.getJSONObject(i)
                            var jsonData: JSONObject = jsonObject.getJSONObject("data")

                            Log.i("wck2", jsonObject.toString())
                            var gson = Gson()
                            var todoJson: String = jsonObject.toString()
                            var tracker = gson.fromJson(todoJson, TrackerResponse::class.java)
                            Log.i("wck", tracker.workerName)
                            Log.i("wck4", tracker.data.timestamp)
                            //
                            tracks.add(tracker)
                            if(tracks.isNotEmpty()){
                                var adapter = TrackerAdapter(mContext, tracks)

                                trackerRv.adapter = adapter
                            }


                        }catch (e:JSONException){
                            Log.e("err",e.stackTraceToString())
                        }

                }
                if(tracks.size ==0){
                    Toast.makeText(context,"No Data has been fetched",Toast.LENGTH_SHORT).show()
                }
                tracks.forEach {
                    Log.i("wck3",it.workerName)
                }
                Log.i("size",tracks.size.toString())


            }
        },{error->
            Log.d("err", error.stackTraceToString())
        } )
        requestQueue.add(jsonArrayRequest)

    }




}