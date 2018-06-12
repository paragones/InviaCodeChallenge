package com.inviacodechallenge.parag.services

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.inviacodechallenge.parag.R
import com.squareup.picasso.Picasso

class PicasoImageLoader(context: Context) : ImageLoader {
    private val picasso: Picasso = Picasso.Builder(context).build()

    override fun loadInto(uri: Uri?, view: ImageView) {
        picasso.load(uri)
                .placeholder(R.drawable.ic_github_icon)
                .into(view)
    }
}
