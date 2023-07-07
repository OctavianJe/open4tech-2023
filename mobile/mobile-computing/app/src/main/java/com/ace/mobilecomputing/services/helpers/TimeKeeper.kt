package com.ace.mobilecomputing.services.helpers

import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import com.ace.mobilecomputing.domain.miscellaneous.DateType

object TimeKeeper {
    fun nowUTC(): String = getFullUTC()
    fun now(): String = getFullLocal()

    private fun getFullLocal(): String =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateType.FULL.value))

    private fun getFullUTC(zoneOffset: ZoneId = ZoneOffset.UTC): String =
        OffsetDateTime.now(zoneOffset).toString()

    private fun getFullNoT(): String =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateType.FULL_NO_T.value))

    private fun getNoSeconds(date: Date = Date(), zoneOffset: ZoneId = ZoneOffset.UTC): String =
        LocalDateTime.ofInstant(date.toInstant(), zoneOffset)
            .format(DateTimeFormatter.ofPattern(DateType.NO_SECONDS.value))

    private fun getDateYearFirst(date: Date = Date(), zoneOffset: ZoneId = ZoneOffset.UTC): String =
        LocalDateTime.ofInstant(date.toInstant(), zoneOffset)
            .format(DateTimeFormatter.ofPattern(DateType.YEAR_FIRST.value))

    private fun getDateYearLast(date: Date = Date(), zoneOffset: ZoneId = ZoneOffset.UTC): String =
        LocalDateTime.ofInstant(date.toInstant(), zoneOffset)
            .format(DateTimeFormatter.ofPattern(DateType.YEAR_LAST_DASH.value))

    private fun getDayShort(date: Date = Date(), zoneOffset: ZoneId = ZoneOffset.UTC): String =
        LocalDateTime.ofInstant(date.toInstant(), zoneOffset)
            .format(DateTimeFormatter.ofPattern(DateType.DAY_SHORT.value))

    private fun getDateNoHours(date: Date = Date(), zoneOffset: ZoneId = ZoneOffset.UTC): Long =
        LocalDateTime.ofInstant(date.toInstant(), zoneOffset)
            .toLocalDate()
            .atStartOfDay(zoneOffset)
            .toInstant()
            .toEpochMilli()

    fun oneDayInMS() = 24 * 60 * 60 * 1000

    fun getFullNoTDate(date: String): Date? = SimpleDateFormat(DateType.FULL_NO_T.value, Locale.US).parse(date)

    fun getFullNoTCurrentDate(): String = getFullNoT()
}