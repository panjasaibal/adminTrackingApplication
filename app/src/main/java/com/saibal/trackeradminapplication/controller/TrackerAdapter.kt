package com.saibal.trackeradminapplication.controller

import android.content.Context
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saibal.trackeradminapplication.R
import com.saibal.trackeradminapplication.model.TrackerResponse
import java.time.Instant
import java.time.ZoneId
import java.util.Locale

class TrackerAdapter(
    private var context:Context,
    private var trackersList:ArrayList<TrackerResponse>
) :RecyclerView.Adapter<TrackerAdapter.TrackerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackerViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.trackers_list_layout, parent, false)
        return TrackerAdapter.TrackerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackerViewHolder, position: Int) {

        var currentTrackerResponse:TrackerResponse = trackersList.get(position)
        holder.workerTrackerNameTv.text = currentTrackerResponse.workerName
        var time = Instant.ofEpochSecond(currentTrackerResponse.data.timestamp.toLong()).atZone(
            ZoneId.systemDefault()).toLocalTime()
        holder.timeSyncedTv.text = time.toString()
        var geocoder:Geocoder = Geocoder(context, Locale.getDefault())

        val addresses = geocoder.getFromLocation( currentTrackerResponse.data.latitude.toDouble(), currentTrackerResponse.data.longitude.toDouble(), 1)
        val address = addresses!![0].getAddressLine(0)
        holder.addressTv.text = address

    }

    override fun getItemCount(): Int {
        return trackersList.size
    }

    class TrackerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var workerTrackerNameTv:TextView = itemView.findViewById(R.id.worker_tracker_name_tv)
        var timeSyncedTv:TextView = itemView.findViewById(R.id.time_synced_tv)
        var addressTv:TextView = itemView.findViewById(R.id.addressTv)
    }
}