package com.saibal.trackeradminapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.lifecycle.lifecycleScope
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.saibal.trackeradminapplication.admin.PanelActivity
import com.saibal.trackeradminapplication.common.Utill

import com.saibal.trackeradminapplication.model.Admin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    private lateinit var requestQueue:RequestQueue
    private lateinit var loginBtn:Button
    private lateinit var emailEdtText:TextInputEditText
    private lateinit var passwdEdtText:TextInputEditText
    private lateinit var errorMessageTv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestQueue = Volley.newRequestQueue(this)
        loginBtn = findViewById(R.id.login_btn);
        emailEdtText = findViewById(R.id.email_edit_text)
        passwdEdtText = findViewById(R.id.password_edit_text)
        errorMessageTv = findViewById(R.id.errMessageTv)
        loginBtn.setOnClickListener {
            login(emailEdtText.text.toString(), passwdEdtText.text.toString())
        }
    }


    private fun login(email:String,password:String){


            val url = Utill.BASE_URL+"/loginadmin";
            var params = mapOf<String, String>("email" to email,"passwd" to password)

            val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.POST, url, JSONObject(params),object: Response.Listener<JSONObject?> {
                override fun onResponse(response: JSONObject?) {
                    Log.d("called", "post api called")
                    errorMessageTv.visibility = View.INVISIBLE
                    try {
                        var jsonObject = response?.getJSONObject("result")
                        val toDoJson:String = jsonObject.toString();
                        Log.i("todoJson", toDoJson)
                       // Toast.makeText(applicationContext,toDoJson,Toast.LENGTH_LONG).show()
                        var gson:Gson = Gson()
                        var admin: Admin = gson.fromJson(toDoJson, Admin::class.java)
                        var intent: Intent = Intent(applicationContext, PanelActivity::class.java)
                        intent.putExtra(Utill.ADMIN_INTENT_KEY, admin)
                        Log.d("admin",admin.id);

                       // Utills.saveDataInSharedPrefrences(applicationContext,Utills.SHAREDPREFRENCE_WORKER_KEY,worker._id.toString())
                        startActivity(intent)

                    }catch(e: JSONException){
                        Log.d("err", e.stackTraceToString())
                    }
                }
            },
                object: Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {

                        var response: NetworkResponse? = error?.networkResponse
                        errorMessageTv.visibility = View.VISIBLE
                        try{
                            Log.i("error",error?.networkResponse?.statusCode.toString())
                            var logMessage = response?.data?.decodeToString()
                            var errJsonMsg  = JSONObject(logMessage)
                            Log.i("errorMSG",logMessage.toString())
                            Toast.makeText(applicationContext, errJsonMsg.get("error").toString(), Toast.LENGTH_SHORT).show()
                            errorMessageTv.text = errJsonMsg.get("error").toString()

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