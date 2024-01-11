package com.example.team25.utils

import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.team25.R

/**
 * This data class is used to keep track of data before passing it onto the dangerFragment.
 *
 * @param disaster: The type of disaster.
 * @param areaId: Unique identifier for area. This is regionId if avalanche, else municipalityNr.
 * @param dangerLevel: How dangerous it currently is.
 * @param area: The place to show information of. This string will be at the top of the fragment.
 * @param higherOrderArea: The string which will be below `place`. Typically the county we are in.
 * @param mainText: The string which contains some tiny information about the danger.
 */
data class DangerInformation(
	val disaster: Disaster,
	val areaId: String,
	val dangerLevel: Int,
	val area: String,
	val higherOrderArea: String,
	val mainText: String
)

val dangerColor = mapOf(
	0 to R.drawable.circle,
	1 to R.drawable.circle_level1,
	2 to R.drawable.circle_level2,
	3 to R.drawable.circle_level3,
	4 to R.drawable.circle_level4,
	5 to R.drawable.circle_level5,
	6 to R.drawable.circle_level_extra
)

/**
 * Creates a bundle from a dangerInformation object.
 *
 * @param dangerInformation: A data object containing information to be passed onto dangerFragment.
 * @return a bundle which can be sent while navigating to dangerFragment.
 */
fun createDangerInformationBundle(dangerInformation: DangerInformation): Bundle {
	return bundleOf(
		"disaster" to dangerInformation.disaster,
		"areaId" to dangerInformation.areaId,
		"dangerLevel" to dangerInformation.dangerLevel,
		"area" to dangerInformation.area,
		"higherOrderArea" to dangerInformation.higherOrderArea,
		"mainText" to dangerInformation.mainText
	)
}