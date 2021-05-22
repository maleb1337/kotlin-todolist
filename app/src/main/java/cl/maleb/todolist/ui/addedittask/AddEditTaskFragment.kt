package cl.maleb.todolist.ui.addedittask

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cl.maleb.todolist.R
import cl.maleb.todolist.databinding.FragmentAddEditTaskBinding
import cl.maleb.todolist.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddEditTaskFragment : Fragment(R.layout.fragment_add_edit_task) {
    private val viewModel: AddEditTaskViewModel by viewModels()

    companion object {
        const val ADD_EDIT_REQUEST = "add_edit_request"
        const val ADD_EDIT_RESULT = "add_edit_result"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditTaskBinding.bind(view)

        binding.apply {
            editTextTaskName.setText(viewModel.taskName)
            checkboxImportant.isChecked = viewModel.taskImportance
            checkboxImportant.jumpDrawablesToCurrentState() // to skip animation
            textViewDateCreated.isVisible = viewModel.task != null
            textViewDateCreated.text =
                resources.getString(R.string.date_created, viewModel.task?.createdDateFormatted)

            editTextTaskName.addTextChangedListener {
                viewModel.taskName = it.toString()
            }

            checkboxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModel.taskImportance = isChecked
            }

            fabSaveTask.setOnClickListener {
                viewModel.onSaveClick()
            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.addEditTaskEvent.collect { event ->
                    when (event) {
                        is AddEditTaskEvent.NavigateBackWithResult -> {
                            editTextTaskName.clearFocus()
                            setFragmentResult(
                                ADD_EDIT_REQUEST,
                                bundleOf(ADD_EDIT_RESULT to event.result)
                            )
                            findNavController().popBackStack()
                        }
                        is AddEditTaskEvent.ShowInvalidInputMessage -> {
                            Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                        }
                    }.exhaustive
                }
            }
        }
    }
}