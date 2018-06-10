package com.inviacodechallenge.parag.image

import android.net.Uri
import android.widget.ImageView

interface ImageLoader {
    fun loadInto(uri: Uri, view: ImageView)
}
