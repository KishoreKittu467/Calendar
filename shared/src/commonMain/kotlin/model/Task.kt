package model

import metaModel.FieldType

/**
 * [subGroup] /List of the Task/
 * @sample IEntity(name = "List Name", notes = "Board Name", image = Image(color = /List Background Color/))
 * [duration] /Estimated Duration of the Task/
 * [isMarked] (isStarred)
 * [checkList]: Set<TaskId>
 * [pages]: Set<NotesId>
 * */
interface ITask : IEvent {
    val subTasks: LinkedHashSet<ITask>?
    var status: TaskStatus?
    val checkList: LinkedHashSet<String>?
    val pages: LinkedHashSet<String>?
}

data class Task(
    override val subTasks: LinkedHashSet<ITask>? = null,
    override var status: TaskStatus? = null,
    override val checkList: LinkedHashSet<String>? = null,
    override val pages: LinkedHashSet<String>? = null,
    private val iEvent: IEvent,
) : ITask, IEvent by iEvent

sealed class TaskStatus(val percentage: Byte) : FieldType {
    data class Pending(val donePercentage: Byte) : TaskStatus(donePercentage)
    data object Completed : TaskStatus(100)
    data class Overdue(val currentStatus: Pending) : TaskStatus(currentStatus.percentage)
}