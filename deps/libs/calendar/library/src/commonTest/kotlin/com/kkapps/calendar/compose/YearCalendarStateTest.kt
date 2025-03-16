package com.kkapps.calendar.compose

import com.kkapps.calendar.compose.yearcalendar.YearCalendarState
import com.kkapps.calendar.core.OutDateStyle
import com.kkapps.calendar.core.Year
import com.kkapps.calendar.core.firstDayOfWeekFromLocale
import com.kkapps.calendar.core.minusYears
import com.kkapps.calendar.core.now
import com.kkapps.calendar.core.plusDays
import com.kkapps.calendar.core.plusYears
import com.kkapps.calendar.data.VisibleItemState
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertEquals

class YearCalendarStateTest {
    @Test
    @JsName("test1")
    fun `start year update is reflected in the state`() {
        val now = Year.now()
        val updatedStartYear = now.minusYears(8)
        val state = createState(
            startYear = now,
            endYear = now,
        )

        assertEquals(state.store[0].year, now)

        state.startYear = updatedStartYear

        assertEquals(state.store[0].year, updatedStartYear)
    }

    @Test
    @JsName("test2")
    fun `end year update is reflected in the state`() {
        val now = Year.now()
        val updatedEndMonth = now.plusYears(8)
        val state = createState(
            startYear = now,
            endYear = now,
        )

        assertEquals(state.store[0].year, now)

        state.endYear = updatedEndMonth

        assertEquals(state.store[8].year, updatedEndMonth)
    }

    @Test
    @JsName("test3")
    fun `first day of the week update is reflected in the state`() {
        val firstDayOfWeek = LocalDate.now().dayOfWeek

        val state = createState(firstDayOfWeek = firstDayOfWeek)

        state.store[0].months
            .flatMap { month -> month.weekDays }
            .forEach { week ->
                assertEquals(week.first().date.dayOfWeek, firstDayOfWeek)
            }
        do {
            val updatedFirstDayOfWeek = state.firstDayOfWeek.plusDays(1)
            state.firstDayOfWeek = updatedFirstDayOfWeek

            state.store[0].months
                .flatMap { month -> month.weekDays }
                .forEach { week ->
                    assertEquals(week.first().date.dayOfWeek, updatedFirstDayOfWeek)
                }
        } while (firstDayOfWeek != state.firstDayOfWeek)
    }

    @Test
    @JsName("test4")
    fun `out date style update is reflected in the state`() {
        val outDateStyle = OutDateStyle.EndOfRow
        // Nov 2022 has 5 weeks when Sun is the first day.
        val startYear = Year(2022)
        val state = createState(
            startYear = startYear,
            endYear = startYear,
            outDateStyle = outDateStyle,
            firstDayOfWeek = DayOfWeek.SUNDAY,
        )

        assertEquals(state.store[0].months[Month.NOVEMBER.ordinal].weekDays.count(), 5)

        state.outDateStyle = OutDateStyle.EndOfGrid

        assertEquals(state.store[0].months[Month.NOVEMBER.ordinal].weekDays.count(), 6)
    }

    private fun createState(
        startYear: Year = Year.now(),
        endYear: Year = startYear,
        firstVisibleYear: Year = startYear,
        outDateStyle: OutDateStyle = OutDateStyle.EndOfRow,
        firstDayOfWeek: DayOfWeek = firstDayOfWeekFromLocale(),
        visibleItemState: VisibleItemState = VisibleItemState(),
    ) = YearCalendarState(
        startYear = startYear,
        endYear = endYear,
        firstVisibleYear = firstVisibleYear,
        outDateStyle = outDateStyle,
        firstDayOfWeek = firstDayOfWeek,
        visibleItemState = visibleItemState,
    )
}
