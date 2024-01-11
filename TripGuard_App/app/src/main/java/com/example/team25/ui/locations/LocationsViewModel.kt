package com.example.team25.ui.locations

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team25.persistence.SavedLocation

/**
 * ViewModel for LocationsFragment.
 */
class LocationsViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "Mine lokasjoner"
	}
	val text: LiveData<String> = _text

	/**
	 *List of locations.
	 */
	lateinit var locationList: MutableList<SavedLocation>
	lateinit var context: Context
}

