package com.example.team25.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team25.api.avalancheWarningByRegion
import com.example.team25.api.floodWarningByMunicipality
import com.example.team25.api.landslideWarningByMunicipality
import com.example.team25.utils.DangerInformation
import com.example.team25.utils.Disaster
import com.example.team25.utils.Language
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewModel for DangerFragment.
 */
class DangerViewModel : ViewModel() {
	var initialized: Boolean = false

	/**
	 * The fields below are just temporary date and will be overwritten on fragment creation
	 * if initialized == false.
	 */
	var disaster: Disaster = Disaster.AVALANCHE
	var areaId: String = ""
	var area: String = ""
	var higherOrderArea: String = ""

	var dangerLevel = MutableLiveData<Int>()
	var date = MutableLiveData<String>()
	var mainText = MutableLiveData<String>()

	// These also have to be stored so that DatePickerFragment can start with a correct default.
	var year: Int = 0
	var month: Int = 0
	var day: Int = 0

	/**
	 * Function to be called on for initializing data in the ViewModel. This is only called from
	 * DangerFragment upon its original creation.
	 *
	 * @param dangerInformation: Information retrieved by the fragment to aid its creation.
	 */
	fun initialize(dangerInformation: DangerInformation) {
		disaster = dangerInformation.disaster
		areaId = dangerInformation.areaId
		area = dangerInformation.area
		higherOrderArea = dangerInformation.higherOrderArea

		dangerLevel.value = dangerInformation.dangerLevel
		mainText.value = dangerInformation.mainText
		date.value = SimpleDateFormat("yyyy-MM-dd").format(Date())

		val c = Calendar.getInstance()
		year = c.get(Calendar.YEAR)
		month = c.get(Calendar.MONTH)
		day = c.get(Calendar.DAY_OF_MONTH)

		initialized = true
	}

	/**
	 * Makes a new request to the API to update the information given a new date. This function is
	 * typically called upon by the DatePickerFragment.
	 */
	fun updateInformation(searchDate: String) {
		GlobalScope.launch {
			when (disaster) {
				Disaster.AVALANCHE -> updateAvalancheInformation(searchDate)
				Disaster.FLOOD -> updateFloodInformation(searchDate)
				Disaster.LANDSLIDE -> updateLandslideInformation(searchDate)
			}
		}
	}

	private fun updateAvalancheInformation(searchDate: String) {
		val warnings = avalancheWarningByRegion(
			areaId,
			Language.NORWEGIAN,
			searchDate,
			searchDate
		)

		// ??? What to do here
		if (warnings.isEmpty()) {
			return
		}

		dangerLevel.postValue(warnings[0].DangerLevel)
		mainText.postValue(warnings[0].MainText)
		date.postValue(searchDate)
	}

	private fun updateFloodInformation(searchDate: String) {
		val warnings = floodWarningByMunicipality(
			areaId,
			Language.NORWEGIAN,
			searchDate,
			searchDate
		)

		// ??? What to do here
		if (warnings.isEmpty()) {
			return
		}

		dangerLevel.postValue(warnings[0].ActivityLevel)
		date.postValue(searchDate)
	}

	private fun updateLandslideInformation(searchDate: String) {
		val warnings = landslideWarningByMunicipality(
			areaId,
			Language.NORWEGIAN,
			searchDate,
			searchDate
		)

		// ??? What to do here
		if (warnings.isEmpty()) {
			return
		}

		dangerLevel.postValue(warnings[0].ActivityLevel)
		date.postValue(searchDate)
	}
}

