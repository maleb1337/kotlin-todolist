package cl.maleb.todolist.ui.addedittask

sealed class AddEditTaskEvent {
    data class ShowInvalidInputMessage(val message: String) : AddEditTaskEvent()
    data class NavigateBackWithResult(val result: Int) : AddEditTaskEvent()
}
