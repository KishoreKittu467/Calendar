package model

/**
 * No Doc
 * */
interface IDay {
    val dateTime: Long
    val events: List<IEvent>?
}

data class Day(
    override val dateTime: Long,
    override val events: List<IEvent>? = null
): IDay
