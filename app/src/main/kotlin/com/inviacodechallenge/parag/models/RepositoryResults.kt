package com.inviacodechallenge.parag.models

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import com.inviacodechallenge.parag.services.DefaultParcelable
import com.inviacodechallenge.parag.services.read
import com.inviacodechallenge.parag.services.write
import java.io.Serializable
import java.util.*

data class RepositoryResults(@SerializedName("name") val repositoryName: String?,
                             @SerializedName("full_name") val repositoryFullName: String?,
                             @SerializedName("owner") val ownerResults: OwnerResults?,
                             @SerializedName("description") val repositoryDescription: String?,
                             @SerializedName("language") val repositoryLanguage: String?,
                             @SerializedName("forks") val repositoryForks: Int?,
                             @SerializedName("updated_at") val update: Date?) : Serializable, DefaultParcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(repositoryName,
                repositoryFullName,
                ownerResults,
                repositoryDescription,
                repositoryLanguage,
                repositoryForks,
                update)
    }

    companion object {
        @JvmField
        val CREATOR = DefaultParcelable.generateCreator {
            RepositoryResults(it.read(), it.read(), it.read(), it.read(), it.read(), it.read(), it.read())
        }
    }
}
