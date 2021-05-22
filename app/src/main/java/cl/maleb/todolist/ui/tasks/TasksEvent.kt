package cl.maleb.todolist.ui.tasks

import cl.maleb.todolist.data.Task

sealed class TasksEvent {
    data class ShowUndoDeleteTaskMessage(val task: Task) : TasksEvent()


}
