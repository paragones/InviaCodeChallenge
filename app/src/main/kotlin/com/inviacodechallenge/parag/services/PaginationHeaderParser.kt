package com.inviacodechallenge.parag.services

import com.inviacodechallenge.parag.models.Link
import com.inviacodechallenge.parag.models.Pagination
import okhttp3.Headers
import retrofit2.Response


object PaginationHeaderParser {
    val HEADER_KEY_LINK = "Link"

    fun parse(response: Response<*>?): Pagination {
        return if (response == null) {
            Pagination()
        } else parseHeaders(response.headers())

    }

    private fun parseHeaders(headers: Headers?): Pagination {
        var pagination = Pagination()
        val linksString = headers?.get(HEADER_KEY_LINK)
        val linkStrings = linksString?.split(",".toRegex())?.dropLastWhile({ it.isEmpty() })?.toTypedArray()

        linkStrings?.let {
            if (!it.isEmpty()){
                val links = arrayOfNulls<Link>(it.size)
                for (i in links.indices) {
                    val link = it[i].let { Link.fromSource(it) }
                    links[i] = link
                }
                pagination = Pagination(links)
            }
        }
        return pagination
    }
}