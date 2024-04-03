package com.saibal.trackeradminapplication.controller

import androidx.appcompat.app.AlertDialog;
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.model.Worker

class WorkerAdapter(
    private var context: Context,
    private var workers:ArrayList<Worker>
):RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.wroker_list_layout, parent, false)
        return WorkerViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        var currentWorker:Worker = workers.get(position)
        holder.workerNameTv.text = currentWorker.name
        holder.infoBtn.setOnClickListener {

            var builder:AlertDialog.Builder = AlertDialog.Builder(context)
            val customlayout:View = LayoutInflater.from(context).inflate(R.layout.worker_detail_layout, null)
            customlayout.findViewById<TextView>(R.id.worker_details_name_tv).text = currentWorker.name
            customlayout.findViewById<TextView>(R.id.worker_details_phone_tv).text = currentWorker.phone

            builder.setView(customlayout)
            builder.setPositiveButton("Ok",
                DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                    // When the user click yes button then app will close
                    dialog?.cancel()
                } as DialogInterface.OnClickListener)

            var alertDialog:AlertDialog = builder.create()
            alertDialog.show()

        }
        holder.deleteBtn.setOnClickListener {

        }


    }

    override fun getItemCount(): Int {
        return workers.size
    }

    class WorkerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){


          var  workerNameTv:TextView = itemView.findViewById(R.id.worker_name_tv)
          var  infoBtn:Button = itemView.findViewById(R.id.infoBtn)
          var  deleteBtn:Button = itemView.findViewById(R.id.workerDeleteButton)

    }
}