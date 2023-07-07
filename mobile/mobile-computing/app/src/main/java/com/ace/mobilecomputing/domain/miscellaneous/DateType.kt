package com.ace.mobilecomputing.domain.miscellaneous


enum class DateType(val value: String) {
    FULL("yyyy-MM-dd'T'HH:mm:ss"),
    FULL_NO_T("yyyy.MM.dd HH:mm:ss"),
    NO_SECONDS("dd-MM-yyyy HH:mm"),
    YEAR_FIRST("yyyy-MM-dd"),
    YEAR_LAST_DASH("dd-MM-yyyy"),
    YEAR_LAST_SLASH("dd/MM/yyyy"),
    DAY_SHORT("EEE")
}