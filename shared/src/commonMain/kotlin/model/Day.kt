package model

/**
 * [dateTime] is start time of the day in milliseconds since epoch.
 * */
interface IDay {
    val dateTime: Long
    val events: List<IEvent>?
}

data class Day(
    override val dateTime: Long,
    override val events: List<IEvent>? = null
): IDay
