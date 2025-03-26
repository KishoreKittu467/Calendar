package model

import metaModel.IHabitMeta

/**
 * [cover] /Book Cover/
 * @sample IEntity(name = "Diary", notes = "My life", image = Image(color = /Diary BgColor/, photo = /Front cover/)
 * [subTasks] /Pages of the Book/ //Each subTask's group will act as a 'Chapter' in the book//
 * @sample
 * subTask.group = Entity(name = "Chapter 1", notes = "The Beginning", image = /Chapter 1 image/)
 * subTask.[status].percentage /Page Number/
 * [rating] : [IHabitMeta]
 * @sample IHabitMeta(milestones = listOf(
 * IMilestone(name = "WORST", notes = "Rating", image = Image(icon = "★☆☆☆☆")),
 * IMilestone(name = "BAD", notes = "Rating", image = Image(icon = "★★☆☆☆")),
 * IMilestone(name = "AVERAGE", notes = "Rating", image = Image(icon = "★★★☆☆")),
 * IMilestone(name = "GOOD", notes = "Rating", image = Image(icon = "★★★★☆")),
 * IMilestone(name = "GREAT", notes = "Rating", image = Image(icon = "★★★★★"))),
 * type = HabitType.MilestoneOnly)
 *
 * [isMarked] = isFav
 * */
interface INotes: ITask {
    val cover: IEntity?
    val rating: IHabitMeta?
}

data class Notes(
    override val cover: IEntity? = null,
    override val rating: IHabitMeta? = null,
    private val iTask: ITask
) : INotes, ITask by iTask
