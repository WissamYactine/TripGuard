package com.example.team25.api

import com.example.team25.utils.Language
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AvalancheKtTest {
	@Test
	fun avalancheWardingByLATest() {

		//Information grabbed from API site
		val testdangerLevel = 2 //Expected dangerlevel
		val testLongitude = "8.219244531250016"
		val testLatitude = "59.89892800773294"
		val date = "2019-12-12"

		val warningList : List<AvalancheWarningSimple> = avalancheWarningByCoordinates(testLatitude, testLongitude, Language.NORWEGIAN, date, date)

		assertEquals(testdangerLevel, warningList[0].DangerLevel)
	}
}