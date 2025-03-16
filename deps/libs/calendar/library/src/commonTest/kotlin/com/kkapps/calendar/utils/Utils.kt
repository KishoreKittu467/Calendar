package com.kkapps.calendar.utils

import com.kkapps.calendar.core.OutDateStyle
import com.kkapps.calendar.core.YearMonth
import com.kkapps.calendar.core.minusMonths
import com.kkapps.calendar.core.plusMonths
import com.kkapps.calendar.data.getCalendarMonthData
import kotlinx.datetime.DayOfWeek

internal infix fun <A, B, C> Pair<A, B>.toTriple(that: C): Triple<A, B, C> = Triple(first, second, that)

internal fun YearMonth.weeksInMonth(firstDayOfWeek: DayOfWeek) = getCalendarMonthData(
    startMonth = this,
    offset = 0,
    firstDayOfWeek = firstDayOfWeek,
    outDateStyle = OutDateStyle.EndOfRow,
).calendarMonth.weekDays.count()

internal val YearMonth.nextMonth: YearMonth
    get() = this.plusMonths(1)

internal val YearMonth.previousMonth: YearMonth
    get() = this.minusMonths(1)
