package metaModel

import model.IEntity
import model.IMilestone
import model.IReminder
import model.IRepetition
import model.ITarget

/**
 * [name] //Name of the Habit, Should be Unique//
 * @sample { +..., Running, Swimming, Reading, Feeling, Sleep}
 * [notes] //Some Description about the habit//
 * @sample IHabitMeta(name = "Running", notes = "Run Every Day", image = Image(icon = "🏃‍♀️"))
 * [repetitions] //Use for recursive habits//Set one [reminders] for each repetition//
 * repetitions // By Default, habit should be daily event & repeat infinitely
 * (Default Value) `IRepetition(id = name, startTime = /Date is currentDate, Time is when everyday user should enter the habit/, duration = 86400000 /1 Day/, repeatCount = 0, endTime = null)`
 * [milestones] /List of milestones belongs to the Habit/
 * @sample {+..., IMilestone(name = "HAPPY", notes = "Feeling", image = Image(icon = "☺︎")),
 *          IMilestone(name = "Low", notes = "Running", image = Image(icon = "↓"))}
 * [targets] //List of targets for the Habit//
 * @sample ITarget(startTime = /current date & time/, duration = /1 week/, entryCount = 7, achievedCount = 3)
 *
 * [longestStreak] //The longest Steak of the habit // To encourage & keep the User engaged with Habit//
 * longestStreak //This is incremented every time all the Targets of the Habit are achieved and reset to 0 when a Target is missed//
 * longestStreak //Naive Solution: Streak Calculation is heavy computation, avoid periodic calculation, implement an on demand calculation like "Click to Recalculate"//
 *
 * // For every entry in IHabit, the corresponding IHabitMeta.longestStreak should be updated //
 * IHabit.rank (score) //Represents actual achievement of the Goal, use it only for Habit entries and not for Habit meta data items//
 *
 * //Get the matched IHabitMeta based on IHabit entry//
 * `currentHabitMeta = allHabitMeta.first(habitMeta -> habitMeta.name == currentHabit.notes)`
 * //Get all milestones of the currentHabitMeta based on type of currentHabitMeta//
 * currentHabitMeta.type == null || [HabitType.Default] ? //Consider Milestone name as checkPoint and silently calculate score from currentHabit.rank //
 * currentHabitMeta.type == HabitType.MilestoneOnly ? //Consider Milestone name as checkPoint and no need to calculate score // Ignore currentHabit.rank //
 * currentHabitMeta.type == HabitType.ScoreOnly ? //Consider currentHabit.rank as checkPoint and calculate score //
 *
 * `matchedMilestones = currentHabitMeta.milestones.filter { it.name == currentHabit.name }`
 * rank //Will be compared for estimating the milestone reached by comparing it with matchedMilestones.startPoint & matchedMilestones.endPoint (Range of each milestone)
 * rank //If the rank is in the range of 2 or more milestones, the winner milestone will be considered the milestone with isTarget == true//
 * rank //If the rank is in the range of 2 or more milestones with matchedMilestones.isTargeted == true, the winner milestone will be considered based on matchedMileStone.bettermentType //
 * rank //If the rank is "equidistant" in the range of 2 or more winner milestones || matchedMilestone.bettermentType == null ? the priority will be given to the milestone with higher index in the list//
 *
 * rank //Get all milestones of the IHabit and calculate if currentHabit.rank is in the range of all the matched milestones //
 * `allMilestones.filter { IHabit.notes == IMilestone.notes && IMilestone.name == IMilestone.name && rank > IMilestone.startPoint && rank < IMilestone.endPoint}`
 * This results in achievedTarget(s)
 *
 * Update the longestStreak of the HabitMeta
 * `achievedTarget == null ? allHabitMeta.first(habitMeta -> habitMeta.name == currentHabit.name).longestStreak = 0`
 * `achievedTarget != null ? allHabitMeta.first(habitMeta -> habitMeta.name == currentHabit.name).longestStreak++`
 *
 * @sample IRepetition(id = name, startTime = /1st achievement time from when steak = 0 /, repeatCount = /No. of Target achievements so far in a row)/, if steak is continuing, endTime = null else endTime = /last achievement target time/)
 *
 * */
interface IHabitMeta : IEntity {
    val type: HabitType?
    val milestones: LinkedHashSet<IMilestone>?
    val targets: LinkedHashSet<ITarget>?
    var longestStreak: IRepetition?
    val repetitions: Set<IRepetition>?
    val reminders: Set<IReminder>?
}

data class HabitMeta(
    override val type: HabitType? = null,
    override val milestones: LinkedHashSet<IMilestone>? = null,
    override var targets: LinkedHashSet<ITarget>?,
    override var longestStreak: IRepetition? = null,
    override val repetitions: Set<IRepetition>? = null,
    override val reminders: Set<IReminder>? = null,
    private val iEntity: IEntity
) : IHabitMeta, IEntity by iEntity

sealed interface HabitType : IFieldType {
    data object Default : HabitType
    data object MilestoneOnly : HabitType
    data object ScoreOnly : HabitType
}
