package cl.maleb.todolist.ui.deleteallcompleted

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import cl.maleb.todolist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel: DeleteAllCompletedViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(context?.getString(R.string.delete_dialog_title))
            .setMessage(context?.getString(R.string.delete_dialog_message))
            .setNegativeButton(context?.getString(R.string.cancel), null)
            .setPositiveButton(context?.getString(R.string.yes)) { _, _ ->
                // call viewModel to delete
                viewModel.onConfirmClick()
            }
            .create()
}