package model

/**
 * [rank] //Can be used for Suggestions, Sorting, Priority//
 * */
interface IEntity {
    val name: String
    val notes: String?
    val image: IImage?
    var rank: Float?
}

data class Entity(
    override val name: String,
    override val notes: String? = null,
    override val image: IImage? = null,
    override var rank: Float? = null
): IEntity