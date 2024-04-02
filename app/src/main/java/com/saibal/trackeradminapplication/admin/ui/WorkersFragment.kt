package com.saibal.trackeradminapplication.admin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.model.Worker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import kotlin.math.log

class WorkersFragment : Fragment() {
    private  lateinit var adminId:String
    var workers = ArrayList<Worker>();
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adminId = this.arguments?.getString(Utill.ADMIN_STORE_KEY,"").toString();
        doLogin()
        recyclerView = view.findViewById(R.id.workerRecyclerView)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workers, container, false)
    }

    private fun fetchAllWorkers(adminId:String){
        lifecycleScope.launch(Dispatchers.IO) {
            var requestQueue:RequestQueue = Volley.newRequestQueue(context)

            var url = Utill.BASE_URL+"/getAllWorker/${adminId}"

            var jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url,null, {response->
                if(response.length()==0){
                    Toast.makeText(context,"No duties", Toast.LENGTH_SHORT).show()
                }else{
                    for(i in 0 until response.length()){
                        try{
                            var jsonObject = response.getJSONObject(i)
                            var gson = Gson()
                            var todoJson:String = jsonObject.toString()
                            Log.i("worker",todoJson)
                            var worker = gson.fromJson(todoJson, Worker::class.java)
                            workers.add(worker)

                        }catch(e: JSONException){
                            Log.e("jsonexxception", e.stackTraceToString())
                        }
                    }
                }
            },{error->
                Log.d("err", error.localizedMessage)
            } )
            requestQueue.add(jsonArrayRequest)
        }
    }

    private fun doLogin(){
        lifecycleScope.launch (Dispatchers.Main){
            fetchAllWorkers(adminId)
        }
    }


}