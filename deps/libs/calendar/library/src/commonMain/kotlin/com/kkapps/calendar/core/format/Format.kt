package com.kkapps.calendar.core.format

import com.kkapps.calendar.core.Year
import com.kkapps.calendar.core.YearMonth
import com.kkapps.calendar.core.atMonth
import com.kkapps.calendar.core.atStartOfMonth
import com.kkapps.calendar.core.yearMonth
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.char

private val ISO_YEAR_MONTH by lazy {
    LocalDate.Format {
        year()
        char('-')
        monthNumber()
    }
}

private val ISO_YEAR by lazy {
    LocalDate.Format { year() }
}

private val ISO_LOCAL_DATE by lazy {
    LocalDate.Formats.ISO
}

internal fun LocalDate.toIso8601String() = ISO_LOCAL_DATE.format(this)

internal fun YearMonth.toIso8601String() = ISO_YEAR_MONTH.format(atStartOfMonth())

internal fun Year.toIso8601String() = ISO_YEAR.format(atMonth(1).atStartOfMonth())

internal fun String.fromIso8601LocalDate(): LocalDate =
    LocalDate.parse(this, ISO_LOCAL_DATE)

internal fun String.fromIso8601YearMonth(): YearMonth =
    LocalDate.parse("$this-01", ISO_LOCAL_DATE).yearMonth

internal fun String.fromIso8601Year(): Year =
    Year(LocalDate.parse("$this-01-01", ISO_LOCAL_DATE).year)
