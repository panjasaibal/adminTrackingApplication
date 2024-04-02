package com.saibal.trackeradminapplication.admin.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.common.Utill
import com.saibal.trackeradminapplication.model.Admin


class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private  lateinit var admin:String
    private  lateinit var adminName:String
    private  lateinit var adminEmail:String
    private lateinit var mContext: Context
    private lateinit var nameTv:TextView
    private lateinit var emailTv:TextView

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

        nameTv.text = adminName
        emailTv.text = adminEmail


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


}