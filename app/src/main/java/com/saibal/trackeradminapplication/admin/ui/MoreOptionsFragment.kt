package com.saibal.trackeradminapplication.admin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.common.Utill



class MoreOptionsFragment : Fragment() {


    private lateinit var adminId:String

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
        adminId = this.arguments?.getString(Utill.ADMIN_STORE_KEY,"").toString();
    }


}