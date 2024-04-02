package com.saibal.trackeradminapplication.admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.admin.ui.DashboardFragment
import com.saibal.trackeradminapplication.admin.ui.MoreOptionsFragment
import com.saibal.trackeradminapplication.admin.ui.WorkersFragment
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.model.Admin


class PanelActivity : AppCompatActivity() {



    //private lateinit var container:FragmentContainer;
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var admin: Admin
    private lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_panel);

        bottomNavigationView = findViewById(R.id.bottom_navbar);
        if(intent.hasExtra(Utill.ADMIN_INTENT_KEY)){
            admin = intent.getSerializableExtra(Utill.ADMIN_INTENT_KEY) as Admin
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


}