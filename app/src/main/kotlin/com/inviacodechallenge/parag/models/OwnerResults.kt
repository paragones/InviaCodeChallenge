package com.inviacodechallenge.parag.models

import android.net.Uri
import android.os.Parcel
import com.google.gson.annotations.SerializedName
import com.inviacodechallenge.parag.services.DefaultParcelable
import com.inviacodechallenge.parag.services.read
import com.inviacodechallenge.parag.services.write
import java.io.Serializable

data class OwnerResults(@SerializedName("login") val ownerName: String?,
                        @SerializedName("avatar_url") val ownerAvatarUrl: Uri?):Serializable, DefaultParcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(ownerName,
                ownerAvatarUrl)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            OwnerResults(it.read(), it.read())
        }
    }
}
