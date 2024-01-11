package com.example.team25.ui.preferences

import android.os.Bundle
import android.view.View
import androidx.preference.*
import com.example.team25.R

class PreferenceSettings : PreferenceFragmentCompat() {
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		setPreferencesFromResource(R.xml.preferences, rootKey)

		val fetchDataTime: SeekBarPreference? = findPreference("sync")
		val dangerLevel: SeekBarPreference? = findPreference("levelDangerNotification")

		fetchDataTime?.updatesContinuously = true
		dangerLevel?.updatesContinuously = true

		setFetchedValue(fetchDataTime)

		dangerLevel?.summary = dangerLevel?.value.toString()

		fetchDataTime?.setOnPreferenceChangeListener { preference, newValue ->
			val valueToInt = Integer.parseInt(newValue.toString())
			when (newValue) {
				0 -> preference.summary = "15 min"
				1 -> preference.summary = "30 min"
				2 -> preference.summary = "45 min"
				3 -> preference.summary = "1 time"
				else -> preference?.summary = (valueToInt-2).toString() + " timer"
			}
			true
		}

		dangerLevel?.setOnPreferenceChangeListener { preference, newValue ->
			preference.summary = newValue.toString()
			true
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val rv = listView /* This holds the PreferenceScreen's items*/
		rv.setPadding(0, 0, 0, 150) /*This sets the padding of the PreferenceScreen's items*/
	}

	private fun setFetchedValue(fetchPref: SeekBarPreference?) {
		when (fetchPref?.value) {
			0 -> fetchPref.summary = "15 min"
			1 -> fetchPref.summary = "30 min"
			2 -> fetchPref.summary = "45 min"
			3 -> fetchPref.summary = "1 time"
			else -> fetchPref?.summary = (fetchPref!!.value-2).toString() + " timer"
		}
	}
}