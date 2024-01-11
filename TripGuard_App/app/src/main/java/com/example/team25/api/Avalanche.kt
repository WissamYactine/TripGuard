package com.example.team25.api

import com.example.team25.utils.Language
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import khttp.get as khttpGet

private val baseUrl = "https://api01.nve.no/hydrology/forecast/avalanche/v5.0.1/api"

/**
 * Data class for JSON response from API call.
 *
 * See [Here](http://api.nve.no/doc/snoeskredvarsel/#avalanchewarningbycoordinatessimple_sluttdato) for an example response.
*/
data class AvalancheWarningSimple(
	val RegId: Int,
	val RegionId: Int,
	val RegionName: String,
	val RegionTypeId: Int,
	val RegionTypeName: String,
	val ValidFrom: String,
	val ValidTo: String,
	val NextWarningTime: String,
	val PublishTime: String,
	val DangerLevel: Int,
	val MainText: String
)

/**
 * Returns a list of simple avalanche warnings for a given coordinate within a closed time interval.
 *
 * Dates are strings of the format '2020-03-29'. This should be replaced with some proper type.
 *
 * @param latitude: The latitude part of the coordinate as a string.
 * @param longitude: The longitude part of the coordinate as a string.
 * @param lang: The language, which is either norwegian or english.
 * @param startDate: The start date of the interval where we are searching for avalanche warnings.
 * @param endDate: The end date of the interval where we are searching for avalanche warnings.
 * @return a list of avalanche warnings.
 */
fun avalancheWarningByCoordinates(latitude: String, longitude: String, lang: Language, startDate: String,
								  endDate: String): List<AvalancheWarningSimple> {
	val url = "$baseUrl/AvalancheWarningByCoordinates/Simple/$latitude/$longitude/${lang.value}/$startDate/$endDate"
	val gson = Gson()
	val res = khttpGet(url)
	val avalanches: List<AvalancheWarningSimple> = gson.fromJson(res.text)
	return avalanches
}

/**
 * Returns a list of simple avalanche warnings for a given region within a closed time interval.
 * **Note:** api calls should always be done async with a try catch, as both HTTP errors and parsing
 * errors may occur.
 *
 * Dates are strings of the format '2020-03-29'. This should be replaced with some proper type.
 *
 * @param regionID: The regionID (see ../utils/AvalancheRegions.kt).
 * @param lang: The language, which is either norwegian or english.
 * @param startDate: The start date of the interval where we are searching for avalanche warnings.
 * @param endDate: The end date of the interval where we are searching for avalanche warnings.
 * @return a list of avalanche warnings.
 */
fun avalancheWarningByRegion(
	regionID: String, lang: Language, startDate: String,
	endDate: String): List<AvalancheWarningSimple> {
	val url = "$baseUrl/AvalancheWarningByRegion/Simple/$regionID/${lang.value}/$startDate/$endDate"
	val gson = Gson()
	val res = khttpGet(url)
	val avalanches: List<AvalancheWarningSimple> = gson.fromJson(res.text)
	return avalanches
}
