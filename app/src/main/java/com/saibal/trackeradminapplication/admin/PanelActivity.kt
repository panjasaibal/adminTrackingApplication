package com.saibal.trackeradminapplication.admin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.admin.ui.DashboardFragment
import com.saibal.trackeradminapplication.admin.ui.MoreOptionsFragment
import com.saibal.trackeradminapplication.admin.ui.WorkerFragment
import com.saibal.trackeradminapplication.admin.ui.WorkersFragment
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.controller.TrackerAdapter
import com.saibal.trackeradminapplication.model.Admin
import com.saibal.trackeradminapplication.model.TrackerResponse
import com.saibal.trackeradminapplication.model.Worker
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class PanelActivity : AppCompatActivity() {


    //private lateinit var container:FragmentContainer;
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var admin: Admin
    private lateinit var bundle: Bundle
    private lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_panel);

        bottomNavigationView = findViewById(R.id.bottom_navbar);
        requestQueue = Volley.newRequestQueue(this@PanelActivity)

        if(intent.hasExtra(Utill.ADMIN_INTENT_KEY)){
            admin = intent.getSerializableExtra(Utill.ADMIN_INTENT_KEY) as Admin


            var url = Utill.BASE_URL+"/getAllWorker/"+admin.id
            var strRes:String = ""
            var jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url,null, { response->


                if(response.length()==0){
                    Toast.makeText(this,"No data found", Toast.LENGTH_SHORT).show()

                }else{
                    strRes = response.toString()
                    bundle.putString(Utill.JSONARRAY_STORE_KEY,strRes)

                }
            },{error->
                Log.d("err", error.stackTraceToString())
            } )
            requestQueue.add(jsonArrayRequest)

           // loadTracker()


            bundle = Bundle()
            //bundle.putSerializable(Utill.ADMIN_STORE_KEY,admin)
            bundle.putString(Utill.ADMIN_STORE_KEY,admin.id)
            bundle.putString(Utill.ADMIN_NAME_STORE_KEY,admin.name)
            bundle.putString(Utill.ADMIN_EMAIL_STORE_KEY,admin.email)

        }

        loadFragement(DashboardFragment())

        bottomNavigationView.setOnItemReselectedListener {
            when(it.itemId){
                R.id.dashboard->{
                    loadFragement(DashboardFragment())
                    true
                }
                R.id.workers->{
                    loadFragement(WorkersFragment())
                    true
                }
                R.id.more->{
                    loadFragement(MoreOptionsFragment())
                    true
                }
            }
        }




    }

    private fun loadFragement(fragment:Fragment){
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container, fragment).commit()
    }

    override fun onPause() {
        super.onPause()
        finishAffinity()

    }

    override fun onDestroy() {
        super.onDestroy()
        finishAffinity()
    }

    private fun loadTracker(){

        var url2:String = Utill.BASE_URL+"/getTracks/"+admin

        var req:RequestQueue = Volley.newRequestQueue(applicationContext)
        var data = ""

        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url2,null, { response->


            if(response.length()==0){
                Toast.makeText(applicationContext,"No data found", Toast.LENGTH_SHORT).show()

            }else{
                data = response.toString()
                Log.i("data",data)
                bundle.putString(Utill.JSONARRAY_OF_TRACKER_STORE_KEY, data)

            }


        },{error->
            Log.d("err", error.stackTraceToString())
        } )
        req.add(jsonObjectRequest)
        Log.d("wck5",data)

    }




}