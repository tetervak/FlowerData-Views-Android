@file:Suppress("unused")
package ca.tetervak.flowerdata.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ca.tetervak.flowerdata.R

/*
    This dialog can be used with or without Navigation Component.

    If used with Navigation component, pass the following 2 required arguments
        title: String
        message: String

    Otherwise, if used without Navigation Component,
    use showErrorDialog function to open the dialog.
*/

fun AppCompatActivity.showErrorDialog(title: String, message: String) =
    ErrorDialog.showErrorDialog(
        supportFragmentManager,
        title,
        message
    )

fun Fragment.showErrorDialog(title: String, message: String) =
    ErrorDialog.showErrorDialog(
        parentFragmentManager,
        title,
        message
    )

fun AppCompatActivity.isErrorDialogShown() =
    ErrorDialog.isErrorDialogShown(supportFragmentManager)

fun Fragment.isErrorDialogShown() =
    ErrorDialog.isErrorDialogShown(parentFragmentManager)

fun AppCompatActivity.dismissErrorDialog() =
    ErrorDialog.dismissErrorDialog(supportFragmentManager)

fun Fragment.dismissErrorDialog() =
    ErrorDialog.dismissErrorDialog(parentFragmentManager)

class ErrorDialog : DialogFragment() {

    companion object {
        private const val TAG = "ErrorDialog"
        private const val TITLE = "title"
        private const val MESSAGE = "message"

        fun showErrorDialog(
            fragmentManager: FragmentManager,
            title: String,
            message: String
        ) = newInstance(title, message).show(fragmentManager, TAG)

        fun isErrorDialogShown(fragmentManager: FragmentManager): Boolean {
            val dialogFragment = fragmentManager.findFragmentByTag(TAG) as DialogFragment?
            return dialogFragment is DialogFragment
        }

        fun dismissErrorDialog(fragmentManager: FragmentManager){
            val dialogFragment = fragmentManager.findFragmentByTag(TAG) as DialogFragment?
            dialogFragment?.dismiss()
        }

        private fun newInstance(title: String, message: String) =
            ErrorDialog().apply {
                arguments = bundleOf(TITLE to title, MESSAGE to message)
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = requireArguments()
        val title = args.getString(TITLE)
        val message = getString(R.string.error_template, args.getString(MESSAGE))
        return AlertDialog.Builder(requireContext(), R.style.ErrorDialog)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .create()
    }

}