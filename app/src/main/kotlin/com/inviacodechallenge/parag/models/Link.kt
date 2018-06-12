package com.inviacodechallenge.parag.models

import android.net.Uri
import android.text.TextUtils


class Link(val page: Int, val perPage: Int, val rel: Rel) {
    enum class Rel {
        FIRST,
        PREV,
        NEXT,
        LAST
    }

    companion object {

        fun fromSource(source: String): Link? {
            if (TextUtils.isEmpty(source)) return null

            val resourceUri = getResourceUri(source)
            val page = getPage(resourceUri)
            val perPage = getPerPage(resourceUri)
            val rel = getRel(source)

            return Link(page, perPage, rel)
        }

        private fun getResourceUri(source: String): Uri? {
            val startIndex = source.indexOf("<")
            val endIndex = source.indexOf(">")
            if (startIndex < 0 || endIndex < 0) {
                return null
            } else {
                val resourceString = source.substring(startIndex, endIndex + 1)
                return Uri.parse(resourceString.substring(1, resourceString.length - 1))
            }
        }

        fun getPage(resourceUri: Uri?): Int {
            return getQueryParameter(resourceUri, "page")
        }

        fun getPerPage(resourceUri: Uri?): Int {
            return getQueryParameter(resourceUri, "per_page")
        }

        fun getQueryParameter(resourceUri: Uri?, key: String): Int {
            if (resourceUri == null) return -1

            val param = resourceUri.getQueryParameter(key)
            return if (TextUtils.isEmpty(param)) {
                -1
            } else {
                Integer.valueOf(param)
            }
        }

        fun getRel(source: String?): Rel {
            if (source != null) {
                for (rel in Rel.values()) {
                    val pattern = "rel=\"" + rel.name.toLowerCase() + "\""
                    if (source.indexOf(pattern) != -1) {
                        return rel
                    }
                }
            }

            return Rel.FIRST
        }
    }
}