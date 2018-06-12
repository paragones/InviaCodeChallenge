package com.inviacodechallenge.parag.ui.subscriber

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.inviacodechallenge.parag.R
import com.inviacodechallenge.parag.models.Subscriber
import com.inviacodechallenge.parag.services.ImageLoader
import com.makeramen.roundedimageview.RoundedImageView

class SubscriberAdapter(private val imageLoader: ImageLoader, val subscribers: List<Subscriber>) : RecyclerView.Adapter<SubscriberAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberAdapter.ViewHolder {
        return SubscriberAdapter.ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.subscribers_view, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById(R.id.avatar) as RoundedImageView
        val name = view.findViewById(R.id.name) as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = subscribers[position].name
        imageLoader.loadInto(subscribers[position].avatar, holder.avatar)
    }


    override fun getItemCount(): Int = subscribers.size
}
