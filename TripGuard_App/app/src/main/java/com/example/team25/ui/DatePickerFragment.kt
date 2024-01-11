package com.example.team25.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

/**
 * Fragment for picking the date inside of dangerFragment. This class is take from
 * [here](https://developer.android.com/guide/topics/ui/controls/pickers).
 *
 * @param viewModel: This class takes a DangerViewModel so that it can update it when date is set.
 */
class DatePickerFragment(
	private val viewModel: DangerViewModel
) : DialogFragment(), DatePickerDialog.OnDateSetListener {
	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		// Create a new instance of DatePickerDialog and return it
		return activity?.let {
			DatePickerDialog(it, this, viewModel.year, viewModel.month, viewModel.day)
		}!!
	}

	override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
		viewModel.year = year
		viewModel.month = month
		viewModel.day = day

		/**
		 * Date(int, int, int) is deprecated, so we don't do the below.
		 * Instead we do everything ourselves :)
		 *
		 * month + 1 is needed because for some reason January is represented by 0, Feb by 1, etc..
		 */
		// val date: String = SimpleDateFormat("yy-MM-dd").format(Date(year, month, day))

		val yearString = year.toString()
		val monthString = if (month + 1 >= 10) (month + 1).toString() else "0${month + 1}"
		val dayString = if (day >= 10) day.toString() else "0$day"

		val date = "$yearString-$monthString-$dayString"
		viewModel.updateInformation(date)
	}
}
