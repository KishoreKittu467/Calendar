package com.kkapps.calendar.data

internal fun <T : Comparable<T>> checkRange(start: T, end: T) {
    check(end >= start) {
        "start: $start is greater than end: $end"
    }
}
