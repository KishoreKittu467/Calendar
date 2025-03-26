package model

/**
 * [name] is Unique & Mandatory
 * @sample +..., "Family", "Friend", "Colleagues", "Bank", "Other"
 * [notes] /Type/ ${FieldType.name}
 * notes == null ? (Standalone Tag) : Grouped Tag
 * notes //Grouped tags will have a list of related tags from the same group and are suggested to the user//
 * @sample "Tx", "Event", "Habit", "Note", "Task", "Contact"
 *
 * [rank] (Suggestions Algorithm) //Can be used for the level of suggestions//
 * NOTE: USE TREE or GRAPH SEARCH ALGORITHMS FOR BETTER PERFORMANCE//
 *
 * `rank == null ?` (Default Value) //Do not Suggest any additional tags//
 * `rank == 0 ?` //DANGER//Suggest all relatedTags & relatedTags//
 *
 * `rank == 1 :`
 * //Suggest the current tag's immediate related tags//
 * && //Suggest the tags that contains currentTag as one of their relatedTag//
 *
 * //Naive Solution
 * `currentTag.relatedTags + allTags.filter { tag -> tag.relatedTags.contains(currentTag) }`
 *
 * `rank == 2 :`
 * //Suggest the currentTag's relatedTags and their relatedTags also//
 * && //Suggest the tags that contains current tag as one of their related tags and do the process for these related tags also//
 *
 * //Naive Solution
 * `currentTag.relatedTags.forEach { tag -> tag.relatedTags } + allTags.filter { tag -> tag.relatedTags.contains(currentTag) }.forEach { parentTag -> allTags.filter { tag -> tag.relatedTags.contains(parentTag) } }`
 *
 * `rank == n :`
 * rank // Repeat the suggestions algorithm for the rank levels//
 */
interface ITag: IEntity {
    val relatedTags: List<String>?
    val relatedCategories: List<String>?
}

data class Tag(
    override val relatedTags: List<String>? = null,
    override val relatedCategories: List<String>? = null,
    private val iEntity: IEntity,
): ITag, IEntity by iEntity
