package model

/**
 * [name] is Unique & Mandatory
 * @sample "Event, "Tx", "Task", "Notes", "Habit", "Family", "Friend", "Colleagues", "Bank", "Other"
 * [notes] /Type/ ${FieldType.name}
 * notes == null ? (Standalone Category) : Grouped Tag
 * notes //Grouped tags will have a list of related tags from the same group and are suggested to the user//
 * @sample "Tx", "Event", "Habit", "Note", "Task", "Contact"
 * [subCategories] //Always suggested subCategories when a category is selected//
 * [rank] suggestions algorithm is same as [ITag]'s suggestions algorithm
 */
interface ICategory: ITag {
    val subCategories: List<String>?
}

data class Category(
    override val subCategories: List<String>? = null,
    private val iTag: ITag
) : ICategory, ITag by iTag
