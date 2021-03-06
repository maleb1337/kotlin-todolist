package cl.maleb.todolist.ui.tasks

import cl.maleb.todolist.data.Task

sealed class TasksEvent {
    data class ShowUndoDeleteTaskMessage(val task: Task) : TasksEvent()
    object NavigateToAddTaskScreen : TasksEvent()
    data class NavigateToEditTaskScreen(val task: Task) : TasksEvent()
    data class ShowTaskSavedConfirmationMessage(val message: String) : TasksEvent()
    object NavigateToDeleteAllCompletedScreen : TasksEvent()

}
