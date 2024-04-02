package com.saibal.trackeradminapplication.admin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.saibal.trackeradminapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WorkerFragment : Fragment() {

    private lateinit var workerId:String

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
    private fun fetchDetailsOfWroker(adminId:String){

        lifecycleScope.launch(Dispatchers.IO) {

        }

    }

    
}