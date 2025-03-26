package model

/**
 * [name] //heading for notification//
 * [notes] //description for notification//
 * [image] //icon or photo for notification//
 *
 * [notifyAt] //time for notification to appear//
 * [notifyBefore] //list of notifications to appear before the [notifyAt] time reminding the mills//
 * [notifyAfter] //list of notifications to appear after the [notifyAt] time reminding the mills//
 * [links] //links or click actions for the notification//
 * OnClick of a Notification, if links == null ? Navigate to Home page
 * @sample IEntity(name = "Open", notes = "https://com.app.path.screen/Event/eventId", image = Image(icon = "📂"))
 * @sample IEntity(name = "Close", image = Image(icon = "❌"))
 * OnClick of a Link, if link.notes == null ? Dismiss the notification without performing any action
 *
 * [IReminder]
 * @sample IReminder(name = "Train to Bangalore, notes = "Get ready & pack luggage for your upcoming Journey", image = Image(icon = "🚌"), "notifyAt = /Tomorrow 5 PM in millis/, links = listOf(IEntity(name = "Open", notes = "https://com.app.path.screen/Event/eventId", image = Image(icon = "📂")),)
 * @Optimise : To evaluate if notifyBefore & notifyAfter are really needed
 * */
interface IReminder: IEntity {
    val notifyAt: Long?
    val notifyBefore: Long?
    val notifyAfter: Long?
    val links: LinkedHashSet<IEntity>?
}

data class Reminder(
    override val notifyAt: Long? = null,
    override val notifyBefore: Long? = null,
    override val notifyAfter: Long? = null,
    override val links: LinkedHashSet<IEntity>? = null,
    private val iEntity: IEntity
): IReminder, IEntity by iEntity
