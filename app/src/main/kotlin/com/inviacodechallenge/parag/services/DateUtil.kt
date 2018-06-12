package com.inviacodechallenge.parag.services

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    const val MMM_DD = "MMM dd, yyyy"

    fun parseDate(date: Date?): String {
        val simpleDateFormat = SimpleDateFormat(MMM_DD, Locale.getDefault())
        return simpleDateFormat.format(date)
    }

}