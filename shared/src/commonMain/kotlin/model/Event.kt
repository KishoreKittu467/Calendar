package model

import metaModel.IFieldType

/**
 * [id] = Auto Generated,
 * [createdAt] // is time of the Event created // if event is updated, do not change createdAt, instead update the [lastModifiedAt] //
 * [startTime] //is time when the Event first starts or first repetition//
 * [group] (calender) == null ? Default Calender
 * @sample IEntity(name = "Birthdays", notes = "Description", image = Image(icon = "🎂"))
 * [ownerId] == null ? System Event : User Event
 * [eventType] == null ? General Event : Special Events like{Task, Habit, Notes, Tx}
 * [repetitions] //Use for recursive events & Set one [reminders] for each repetition//
 * [isMarked] (isAttending) == null ? Not Marked ? isAttending : Yes ? No
 * [tags] /List of the Tag names/
 * [categories] /List of the Category names/
 * [contributors] /List of the Person Ids/
 * [attachments] /List of the Bookmark Ids/
 * */
interface IEvent: IEntity {
    val id: String
    val createdAt: Long
    val startTime: Long
    val duration: Long
    val eventType: EventType
    val dueDate: Long?
    val group: IEntity?
    val subGroup: IEntity?
    val ownerId: String?
    val repetitions: Set<IRepetition>?
    val reminders: Set<IReminder>?
    val tags: LinkedHashSet<String>?
    val categories: LinkedHashSet<String>?
    val contributors: LinkedHashSet<String>?
    val attachments: LinkedHashSet<IBookMark>?
    val location: BookmarkType.Location?
    var isMarked: Boolean
    var lastModifiedAt: Long?
}

data class Event(
    override val id: String,
    override val createdAt: Long,
    override val startTime: Long,
    override val duration: Long,
    override val eventType: EventType = EventType.Event,
    override val dueDate: Long? = null,
    override val group: IEntity? = null,
    override val subGroup: IEntity? = null,
    override val ownerId: String? = null,
    override val repetitions: Set<IRepetition>? = null,
    override val reminders: Set<IReminder>? = null,
    override val tags: LinkedHashSet<String>? = null,
    override val categories: LinkedHashSet<String>? = null,
    override val contributors: LinkedHashSet<String>? = null,
    override val attachments: LinkedHashSet<IBookMark>? = null,
    override val location: BookmarkType.Location? = null,
    override var isMarked: Boolean = false,
    override var lastModifiedAt: Long? = createdAt,
    private val iEntity: IEntity
) : IEvent, IEntity by iEntity

sealed interface EventType : IFieldType {
    data object Event : EventType
    data object Task : EventType
    data object Notes : EventType
    data object Tx : EventType
    data object Habit : EventType
    data object Bookmark : EventType
}