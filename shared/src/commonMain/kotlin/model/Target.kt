package model

/**
 * [startTime] //start time of the target, update this when a target is modified//
 * [duration] //Time period for the target to achieve//
 * Target can be of 2 types:
 * [entryCount] //No. of Entries per time period
 * [achievedCount] //No. of Entries achieved per the time period//
 * */
interface ITarget {
    val startTime: Long
    val duration: Long
    val entryCount: Int?
    val achievedCount: Int?
}

data class Target(
    override var startTime: Long,
    override val duration: Long,
    override val entryCount: Int? = null,
    override val achievedCount: Int? = null
): ITarget