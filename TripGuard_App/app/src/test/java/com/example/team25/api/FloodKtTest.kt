package com.example.team25.api

import com.example.team25.utils.Language
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FloodKtTest {

	@Test
	fun getMunicipalitiesTest() {

		var testCountyNr = "30" //County ID til Viken
		var numCountys = 51 //Number of municipalities in Viken

		val list = floodGetMunicipalities(testCountyNr)
		assertEquals(list?.size, numCountys)
	}

	@Test
	fun getMunicipalityWarningsTest() {

		var mockMunicipalityWarning = MunicipalityWarning(0,"Ikke vurdert",null, emptyList())
		var warnings = floodWarningByMunicipality("3001", Language.NORWEGIAN,"2020-09-23","2020-09-23")

		assertEquals(mockMunicipalityWarning,warnings[0])
	}
}