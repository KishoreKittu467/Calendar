package model

/**
 * [name] is Unique & Mandatory //Name of the Habit// For making a new entry, User has to select habit first.//
 * @sample { +..., Running, Swimming, Reading, Feeling, Sleep}
 * [notes]  is Unique & Mandatory //Milestone name that user is entering//
 * @sample {+..., "HAPPY", "LOW", "MEDIUM", "FAST", "BIBLIOPHILE"}
 * [image] /Habit entry's Icon/
 * @sample IImage(icon = "🏃‍♀️", color = /Habit Background Color/)})}
 * [rank] /Can be used as score for the Habit/Given as an optional inout for User/
 * [description] //Some Description about the entry//
 * @sample "Today I won first prize in running"
 * [IHabit]
 * @sample +..., IEntity(name = "Feeling", notes = "HAPPY", image = Image(photo = /image taken when happy/), rank = 100)
 * */
interface IHabit: IEvent {
    val description: String?
}

data class Habit(
    override val description: String? = null,
    private val iEvent: IEvent
): IHabit, IEvent by iEvent
