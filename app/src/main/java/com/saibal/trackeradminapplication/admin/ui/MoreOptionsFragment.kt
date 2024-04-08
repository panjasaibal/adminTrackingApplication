package com.saibal.trackeradminapplication.admin.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.admin.PanelActivity
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.model.Admin
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class MoreOptionsFragment : Fragment() {

    private lateinit var adminId:String
    private lateinit var nameEdtTv:EditText
    private lateinit var phoneEdtTv:EditText
    private lateinit var adharEdtTv:EditText
    private lateinit var addressEdtTv:EditText
    private lateinit var messageTv:TextView
    private lateinit var addWorkerBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adminId = this.arguments?.getString(Utill.ADMIN_STORE_KEY,"").toString()
        nameEdtTv = view.findViewById(R.id.name_edt_txt)
        phoneEdtTv = view.findViewById(R.id.phone_edt_txt)
        adharEdtTv = view.findViewById(R.id.adhar_edt_txt)
        addressEdtTv = view.findViewById(R.id.address_edt_txt)
        messageTv = view.findViewById(R.id.messageTv)
        messageTv.visibility = View.GONE
        addWorkerBtn = view.findViewById(R.id.addWorkerBtn)

        addWorkerBtn.setOnClickListener {
            if(nameEdtTv.text.equals(" ")){
                nameEdtTv.error = "Please enter a valid name"
            }
            else if(phoneEdtTv.text.equals(" ") && phoneEdtTv.text.length!=10){
                phoneEdtTv.error = "Please enter a valid phone number"
            }
            else if(addressEdtTv.text.equals(" ")){
                phoneEdtTv.error = "Please enter a valid address"
            }
            else{
                nameEdtTv.setText("")
                phoneEdtTv.setText("")

                addWorker()
            }
        }
    }

    private fun addWorker(){

        val url = Utill.BASE_URL+"/addworker";
        var requestQueue:RequestQueue = Volley.newRequestQueue(context)
        var params = mapOf<String, String>("name" to nameEdtTv.text.toString(),"phone" to phoneEdtTv.text.toString(), "admin" to adminId)

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.POST, url, JSONObject(params),object: Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {
                    Log.d("called", "post api called")
                    try {
                        var jsonObject = response?.getJSONObject("id")
                        val toDoJson:String = jsonObject.toString()
                        Log.i("todoJson", toDoJson)
                        // Toast.makeText(applicationContext,toDoJson,Toast.LENGTH_LONG).show()
                        messageTv.visibility = View.VISIBLE
                        Toast.makeText(context, "Worker added succesfully with id:${toDoJson}",Toast.LENGTH_SHORT).show()
                        messageTv.text = "Worker added succesfully with id:${toDoJson}"

                    }catch(e: JSONException){
                        //messageTv.text = "Some error occured"
                        Log.e("errorz",e.stackTraceToString())
                    }
                }
            },
            object: Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                    var response: NetworkResponse? = error?.networkResponse
                    try{
                        Log.i("error",error?.networkResponse?.statusCode.toString())
                        var logMessage = response?.data?.decodeToString()
                        var errJsonMsg  = JSONObject(logMessage)

                        Log.i("errorMSG",logMessage.toString())
                        messageTv.visibility = View.VISIBLE
                        messageTv.text = errJsonMsg.getString("error")

                    }catch (e: Exception){
                        Log.e("excep",e.localizedMessage)
                    }
                }
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String, String>();
                header["Content-Type"] = "application/json";
                return header;
            }
        }
        var socketTimeout:Int = 5000
        var policy: RetryPolicy = DefaultRetryPolicy(socketTimeout, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        jsonObjectRequest.setRetryPolicy(policy)
        requestQueue.add(jsonObjectRequest)

    }
}