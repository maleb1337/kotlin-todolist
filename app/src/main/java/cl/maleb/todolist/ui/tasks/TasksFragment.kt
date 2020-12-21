package cl.maleb.todolist.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cl.maleb.todolist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private val viewModel: TaskViewModel by viewModels()


}