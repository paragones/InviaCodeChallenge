package com.inviacodechallenge.parag.models

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import com.inviacodechallenge.parag.services.DefaultParcelable
import com.inviacodechallenge.parag.services.read
import com.inviacodechallenge.parag.services.write
import java.io.Serializable

data class RepositoryResponse(@SerializedName("items") val repositoryResults: List<RepositoryResults>): Serializable, DefaultParcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(repositoryResults)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            RepositoryResponse(it.read())
        }
    }

}
