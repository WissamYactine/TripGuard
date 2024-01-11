package com.example.team25.api

import com.example.team25.utils.Language
import com.github.salomonbrys.kotson.*
import com.google.gson.Gson
import khttp.get as khttpGet

private val baseUrl = "https://api01.nve.no/hydrology/forecast/flood/v1.0.6/api"

/**
 * Returns a list of warnings for the municipality specified. For each day in the date interval,
 * there will be one element in the returned list. **Note:** api calls should always be done async
 * with a try catch, as both HTTP errors and parsing errors may occur.
 *
 * Dates are strings of the format '2020-03-29'. This should be replaced with some proper type.
 *
 * @param municipalityNr: Number of municipality. They are specified here: https://www.ssb.no/klass/klassifikasjoner/131
 * @param lang: The language, which is either norwegian or english.
 * @param startDate: The start date of the interval where we are searching for avalanche warnings.
 * @param endDate: The end date of the interval where we are searching for avalanche warnings.
 * @return a list of warnings for a specified municipality.
 */
fun floodWarningByMunicipality(municipalityNr: String, lang: Language, startDate: String,
							   endDate: String): List<MunicipalityWarning> {
	val url = "$baseUrl/Warning/Municipality/$municipalityNr/${lang.value}/$startDate/$endDate"
	val gson = Gson()
	val res = khttpGet(url)
	val warnings: List<MunicipalityWarning> = gson.fromJson(res.text)
	return warnings
}

/**
 * Returns a list of municipalities for a given county.
 *
 * The NVE API for some reason has two different calls for getting municipalities for a given
 * county; one for flood and one for landslide. There will be a somewhat equivalent function
 * for landslides.
 *
 * For another stupid reason, the API call returns a list of a single object, with another list
 * in the object which has the data we actually want. This results in us have to have a stupid
 * data class wrappers for the json.
 *
 * @param countyNr: Number of county: They are specified here: https://www.ssb.no/klass/klassifikasjoner/104
 * @return a list of municipalities for the given county.
 */
fun floodGetMunicipalities(countyNr: String): List<Municipality>? {
	val url = "$baseUrl/Region/$countyNr"
	val gson = Gson()
	val res = khttpGet(url)
	val wrapper: List<MunicipalityListWrapper> = gson.fromJson(res.text)
	return wrapper[0].MunicipalityList
}
