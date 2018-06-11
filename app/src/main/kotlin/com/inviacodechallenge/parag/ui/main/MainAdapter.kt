package com.inviacodechallenge.parag.ui.main

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.inviacodechallenge.parag.R
import com.inviacodechallenge.parag.extensions.concatenateAlphaAnimations
import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.services.DateUtil
import com.inviacodechallenge.parag.services.ImageLoader
import com.makeramen.roundedimageview.RoundedImageView

class MainAdapter(val imageLoader: ImageLoader,val repositories: List<Repository>, function: (String) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.repositories_view, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rlRepository = view.findViewById(R.id.rlRepository) as ConstraintLayout
        val repositoryName = view.findViewById(R.id.repositoryName) as TextView
        val ownerAvatar = view.findViewById(R.id.ownerAvatar) as RoundedImageView
        val ownerName = view.findViewById(R.id.ownerName) as TextView
        val description = view.findViewById(R.id.description) as TextView
        val language = view.findViewById(R.id.language) as TextView
        val forks = view.findViewById(R.id.forks) as TextView
        val date = view.findViewById(R.id.date) as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repositoryName.text = repositories[position].name
        holder.ownerName.text = repositories[position].owner.name
        holder.description.text = repositories[position].description
        holder.language.text = repositories[position].language
        holder.forks.text = repositories[position].forks.toString()
        holder.date.text = DateUtil.parseDate(repositories[position].update)
        Log.e(this.javaClass.simpleName, "uri ${repositories[position].owner.avatarUrl}")
        imageLoader.loadInto(repositories[position].owner.avatarUrl, holder.ownerAvatar)
        concatenateAlphaAnimations(mutableListOf(holder.rlRepository),100, 1f)
//        holder.rlRepository.visible()
    }

    override fun getItemCount(): Int = repositories.size
}