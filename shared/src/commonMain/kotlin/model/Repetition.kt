package model

/**
 * [id] = id of the Event
 * [startTime] // Lower bound for repetition // Default value = event.startTime
 * [duration] // duration for each repetition // Default value = null but consider as 24 hours or 86400000L when reading the value if `duration == null`.
 * [endTime] // Upper bound for repetition //
 * [repeatCount] // No of times the Event is repeated // Default value = null, So that each event is 1 time event if not specified explicitly //
 * (Default Condition) `repeatCount is in [null, 1]) ?` One time event that happens for [duration] period.
 * `repeatCount == 0 && endTime == null ?` Infinite Repetitions for every [duration] period starting from [startTime]. // Definitely need lazy creation of event repetition //
 * `repeatCount == 0 && endTime != null ?` Repeats until endTime for every [duration] period starting from [startTime].
 *      Make sure `[duration] != null && startTime + duration <= endTime` for this condition.
 * `repeatCount > 1 && endTime == null ?` Repeats exactly repeatCount times.
 * `repeatCount > 1 && endTime != null ?` Repeats exactly repeatCount times or until endTime whichever comes first
 * */
interface IRepetition {
    val id: String
    val startTime: Long
    val duration: Long?
    val endTime: Long?
    val repeatCount: Int?
}

data class Repetition(
    override val id: String,
    override val startTime: Long,
    override val duration: Long? = null,
    override val endTime: Long? = null,
    override val repeatCount: Int? = null
) : IRepetition
