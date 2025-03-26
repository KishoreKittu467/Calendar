package model

import metaModel.FieldType
import model.BookmarkType.Link
import model.BookmarkType.Location

/**
 * [image] // Image or Icon for Bookmark //
 * @sample IImage(icon = "📌")
 * [value] /Bookmark details/ ${FieldType}
 * { +..., Location, Link, Contact, File, Password, Card, Bank Accounts, Subscriptions, Bill}
 * @sample BookmarkType.Location(locationDetails = IEntity(name = "My Home Location", notes = "Location of my own house", image = Image(icon = "🏠"))),
 * [attachments] /List of Files/
 * @sample {Entity(name = "Trip Memory", notes = "Went to a trip with friends on a beautiful beach.", image = Image(photo = /Selfie in the Beach/)),
 *          Entity(name = "PAN Card", notes = "ABC001234", image = Image(photo = /Document/))}
 * */
interface IBookMark : IEvent {
    val value: BookmarkType?
}

data class BookMark(
    override val value: BookmarkType? = null,
    private val iEvent: IEvent,
) : IBookMark, IEvent by iEvent

/**
 * [Link]
 * @sample Link(linkDetails = IEntity(name = "Google", notes = "https://com.app.path", image = Image(icon = "🌐")))
 * [Location]
 * @sample IEntity(name = "My Home Location", notes = "Location of my own house", image = Image(icon = "🏠"))
 * */
sealed class BookmarkType(val details: IEntity) : FieldType {
    data class Profile(val profileDetails: IPerson) : BookmarkType(profileDetails)
    data class Link(val linkDetails: IEntity) : BookmarkType(linkDetails)
    data class File(val fileDetails: IEntity) : BookmarkType(fileDetails)
    data class Bill(val billDetails: IEntity) : BookmarkType(billDetails)
    data class Location(
        val locationDetails: IEntity,
        val lat: Double,
        val long: Double,
        val link: Link
    ) : BookmarkType(locationDetails)

    data class Subscription(
        val subscriptionDetails: IEntity,
        val link: Link? = null
    ) : BookmarkType(subscriptionDetails)

    data class Bank(val account: IBankBookmark) : BookmarkType(account)

    data class Card(
        val cardDetails: IBankBookmark
    ) : BookmarkType(cardDetails)

    data class Custom(
        val value: IEntity,
        val fields: LinkedHashSet<IEntity>? = null
    ) : BookmarkType(value)
}

interface IBankBookmark : IEntity {
    val number: String
    val date: Long?
    val description: String?
    val registeredName: String?
    val type: String?
    val provider: IPerson?
    val owner: IPerson?
}

data class BankBookmark(
    override val number: String,
    override val date: Long? = null,
    override val description: String? = null,
    override val registeredName: String? = null,
    override val type: String? = null,
    override val provider: IPerson? = null,
    override val owner: IPerson? = null,
    private val iEntity: IEntity,
) : IBankBookmark, IEntity by iEntity
