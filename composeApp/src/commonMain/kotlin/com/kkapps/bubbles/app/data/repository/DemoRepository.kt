package com.kkapps.bubbles.app.data.repository

import androidx.compose.ui.graphics.Color
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import com.kkapps.bubbles.app.data.repository.DemoRepository.Flight
import kotlin.time.Clock
import kotlin.time.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.YearMonth
import kotlinx.datetime.atTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.onDay
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

data class AppEntry(
    val title: String,
    val type: String,
    val createdAt: Long
)

data class AnalyticsData(
    val moodStreak: Int,
    val budgetUsage: Float,
    val entriesByType: Map<String, Int>
)

private typealias Airport = Flight.Airport

object DemoRepository {
    @OptIn(ExperimentalTime::class)
    private val now = Clock.System.now().toEpochMilliseconds()

    fun recentEntries(): List<AppEntry> = listOf(
        AppEntry("Diary: Great day", "Diary", now - 3_600_000_0),
        AppEntry("Mood: 😊", "Mood", now - 7_200_000_0),
        AppEntry("Note: Book ideas", "Note", now - 12_000_000_0),
        AppEntry("Event: Team sync", "Event", now - 24_000_000_0),
        AppEntry("Budget: Coffee $5", "Budget", now - 48_000_000),
        AppEntry("Habit: Read 20min", "Habit", now - 72_000_000),
    )

    @OptIn(ExperimentalTime::class)
    fun entriesGroupedByDate(): Map<String, List<AppEntry>> {
        return recentEntries()
            .plus(recentEntries())
            .plus(recentEntries())
            .groupBy {
                val ldt = Instant.fromEpochMilliseconds(it.createdAt).toLocalDateTime(TimeZone.currentSystemDefault())
                "${ldt.date}"
            }.mapValues { entry ->
                entry.value.sortedByDescending { it.createdAt }
            }
    }

    fun analytics(): AnalyticsData {
        return AnalyticsData(
            moodStreak = 7,
            budgetUsage = 65f,
            entriesByType = mapOf(
                "Diary" to 6,
                "Note" to 9,
                "Event" to 4,
                "Budget" to 5,
                "Mood" to 8
            )
        )
    }

    data class Flight(
        val time: LocalDateTime,
        val departure: Airport,
        val destination: Airport,
        val color: Color,
    ) {
        data class Airport(val city: String, val code: String)
    }

    @OptIn(ExperimentalTime::class)
    fun generateFlights(): List<Flight> = buildList {
        val currentMonth = YearMonth.now()

        currentMonth.onDay(17).also { date ->
            add(
                Flight(
                    date.atTime(14, 0),
                    Airport("Lagos", "LOS"),
                    Airport("Abuja", "ABV"),
                    Color(0xFF1565C0),
                ),
            )
            add(
                Flight(
                    date.atTime(21, 30),
                    Airport("Enugu", "ENU"),
                    Airport("Owerri", "QOW"),
                    Color(0xFFC62828),
                ),
            )
        }

        currentMonth.onDay(22).also { date ->
            add(
                Flight(
                    date.atTime(13, 20),
                    Airport("Ibadan", "IBA"),
                    Airport("Benin", "BNI"),
                    Color(0xFF5D4037),
                ),
            )
            add(
                Flight(
                    date.atTime(17, 40),
                    Airport("Sokoto", "SKO"),
                    Airport("Ilorin", "ILR"),
                    Color(0xFF455A64),
                ),
            )
        }

        currentMonth.onDay(3).also { date ->
            add(
                Flight(
                    date.atTime(20, 0),
                    Airport("Makurdi", "MDI"),
                    Airport("Calabar", "CBQ"),
                    Color(0xFF00796B),
                ),
            )
        }

        currentMonth.onDay(12).also { date ->
            add(
                Flight(
                    date.atTime(18, 15),
                    Airport("Kaduna", "KAD"),
                    Airport("Jos", "JOS"),
                    Color(0xFF0097A7),
                ),
            )
        }

        currentMonth.plusMonths(1).onDay(13).also { date ->
            add(
                Flight(
                    date.atTime(7, 30),
                    Airport("Kano", "KAN"),
                    Airport("Akure", "AKR"),
                    Color(0xFFC2185B),
                ),
            )
            add(
                Flight(
                    date.atTime(10, 50),
                    Airport("Minna", "MXJ"),
                    Airport("Zaria", "ZAR"),
                    Color(0xFFEF6C00),
                ),
            )
        }

        currentMonth.minusMonths(1).onDay(9).also { date ->
            add(
                Flight(
                    date.atTime(20, 15),
                    Airport("Asaba", "ABB"),
                    Airport("Port Harcourt", "PHC"),
                    Color(0xFFEF6C00),
                ),
            )
        }
    }

    val flightDateTimeFormatter by lazy {
        LocalDateTime.Format {
            dayOfWeek(DayOfWeekNames(DayOfWeekNames.ENGLISH_ABBREVIATED.names.map { it.uppercase() }))
            char('\n')
            day(Padding.ZERO)
            char(' ')
            monthName(MonthNames(MonthNames.ENGLISH_ABBREVIATED.names.map { it.uppercase() }))
            char('\n')
            hour()
            char(':')
            minute()
        }
    }

}