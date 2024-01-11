package com.example.team25.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for InformationFragment.
 */
class InformationViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "Informasjon"
	}
	val text: LiveData<String> = _text
}