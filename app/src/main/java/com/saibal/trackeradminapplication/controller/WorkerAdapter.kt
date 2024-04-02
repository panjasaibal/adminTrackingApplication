package com.saibal.trackeradminapplication.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.model.Worker

class WorkerAdapter(
    private var context: Context,
    private var workers:List<Worker>
):RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.wroker_list_layout, parent, false)
        return WorkerViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        var currentWorker:Worker = workers.get(holder.adapterPosition)
        holder.workerNameTv.text = currentWorker.name


    }

    override fun getItemCount(): Int {
        return workers.size
    }

    class WorkerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){


         var workerNameTv:TextView
         var infoBtn:Button
         var deleteBtn:Button

        init {
            workerNameTv = itemView.findViewById(R.id.worker_name_tv)
            infoBtn = itemView.findViewById(R.id.infoBtn)
            deleteBtn = itemView.findViewById(R.id.workerDeleteButton)
        }


    }
}