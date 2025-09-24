package model

import metaModel.IFieldType

/**
 * [name] is Unique //Represents Goal name in Habits//Do not allow special characters//
 * @sample +..., "SAD", "MARATHON", "HEAVY", "BIBLIOPHILE", MEDIUM
 *
 * [notes] is Unique //Acts as Type of milestone//
 * @sample +..., "Feelings", "Running", "Sleep"

 * id = [name]+[notes] // will as primary key
 * @sample IMilestone(name = "HAPPY", notes = "Feeling", image = Image(icon = "☺︎"))
 * @sample IMilestone(name = "Low", notes = "Running", image = Image(icon = "↓"))
 * @sample IMilestone(name = "Moderate", notes = "Walking", image = Image(icon = "🚶🏻"))
 * @sample IMilestone(name = "Bibliophile", notes = "Reading", image = Image(icon = "📚"))
 *
 * [units] == null ? //List of Units for the Milestone//Kind of list of Enum or sealed classes, but values are not fixed//
 * units //All items in the list should be having same type of Unit i.e, [units].notes is same of all items in the list//
 * units //First item in the list will be used as default and it should be the baseUnit for that Type of Unit ie [units].notes//
 * units //rank of the non default Units [units].rank will be used as multiplier for the default unit//
 * @sample {+..., IEntity(name = "Count", notes = "Units", image = Image(icon = "🧮"))}
 * @sample {+..., IEntity(name = "Meter", notes = "Distance", image = Image(icon = "📏")),
 *                IEntity(name = "KiloMeter", notes = "Distance", image = Image(icon = "🛣️"), rank = 1000)}
 * @sample {IEntity(name = "Litre", notes = "Quantity", image = Image(icon = "🥛")),
 *          IEntity(name = "Gallon", notes = "Quantity", image = Image(icon = "🫗"), rank = 3.78541)}
 * @sample IEntity(name = "Calories", notes = "Energy", image = Image(icon = "⚡️"))
 * @sample {+..., IEntity(name = "Seconds", notes = "Time", image = Image(icon = "⏱"))
 *         IEntity(name = "Minute", notes = "Time", image = Image(icon = "⏲️"), rank = 60),
 *         IEntity(name = "Hour", notes = "Time", image = Image(icon = "⏳"), rank = 3600),
 *         IEntity(name = "Day", notes = "Time", image = Image(icon = "⌛"), rank = 86400),
 *         IEntity(name = "Week", notes = "Time", image = Image(icon = "📅"), rank = 604800),
 *         IEntity(name = "Month", notes = "Time", image = Image(icon = "📆"), rank = 2592000),
 *         IEntity(name = "Year", notes = "Time", image = Image(icon = "📅"), rank = 31536000)}
 * @sample {+..., IEntity(name = "Gram", notes = "Mass", image = Image(icon = "⚖️"))
 *         IEntity(name = "KG", notes = "Mass", image = Image(icon = "⚖️"), rank = 1000)}
 *
 * [startPoint] //Lower Bound of Points for the milestone//
 * [endPoint] //Upper Bound of Points for the milestone//
 * `startPoint == endPoint == null ?` // No points system.Only [name] acts as "checkPoint" for the milestone//
 * `endPoint - startPoint` (range) //The difference between startPoint and endPoint will be the "range" of the milestone//
 * `endPoint == null || endPoint == startPoint ?` //startPoint will act as a single "checkPoint" for the milestone//
 *
 * [isTarget] //Denotes whether the milestone is an ideal target for the user or not//
 * isTarget //User can set milestone as target during creation or change the target later on//
 * */
interface IMilestone: IEntity {
    val bettermentType: BettermentType?
    val startPoint: Int?
    val endPoint: Int?
    val units: LinkedHashSet<IEntity>?
    var isTarget: Boolean
}

data class Milestone(
    override val startPoint: Int? = null,
    override val endPoint: Int? = null,
    override val units: LinkedHashSet<IEntity>? = null,
    override val bettermentType: BettermentType? = null,
    override var isTarget: Boolean = false,
    private val iEntity: IEntity
): IMilestone, IEntity by iEntity

sealed interface BettermentType: IFieldType {
    data object LowerIsBetter: BettermentType
    data object HigherIsBetter: BettermentType
    data object CloserIsBetter: BettermentType
    data object OuterIsBetter: BettermentType
}