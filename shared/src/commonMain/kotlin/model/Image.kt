package model

/**
 * [icon] : DrawableResource, DrawableInt, ImageResource
 * @sample  {"vector", svg, png, ...}
 * [color] : ColorResource, ColorInt, Color, HexString
 * [emoji] //Unicode should be auto rendered to emoji of the OS// {"☺︎", "😂", "👍", ...}"}
 * [initials]
 * @sample {"K", "KK", "SK", ...}
 * [photo] : urlString, filePath, Url
 * @sample { jpg, webp, gif, ...}
 * @Improvement : Consider if raw files/video or documents can be supported along with caching
 * */
interface IImage {
    val color: String?
    val icon: Int?
    val emoji: String?
    val photo: String?
    val initials: String?
}

data class Image(
    override val color: String? = null,
    override val icon: Int? = null,
    override val emoji: String? = null,
    override val photo: String? = null,
    override val initials: String? = null
) : IImage