package com.ace.mobilecomputing.data.helpers

import androidx.room.TypeConverter
import java.util.*


class Converters {
    @TypeConverter
    fun calendarToDateStamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun dateStampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun intArrayToString(array: List<Int>?): String {
        var ret = ""
        array?.forEach { ret += it.toString() }
        return ret
    }

    @TypeConverter
    fun stringToIntArray(string: String?): List<Int>? {
        return string?.chunked(1)?.map { it.toInt() }
    }
}