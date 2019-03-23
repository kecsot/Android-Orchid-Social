package com.kecsot.orchidsocial.utils

import org.ocpsoft.prettytime.PrettyTime
import java.util.*


object DateUtil {

    fun getFormattedTime(date: Date): String {
        return PrettyTime(Date()).format(date)
    }

}