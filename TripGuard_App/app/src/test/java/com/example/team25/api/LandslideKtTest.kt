package com.example.team25.api

import com.example.team25.utils.Language
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class LandslideKtTest {

    @Test
    fun landslideWarningByMunicipality() {

		var mockMunicipalityWarning = MunicipalityWarning(0,"Ikke vurdert",null, emptyList())
		var warnings = landslideWarningByMunicipality("3001", Language.NORWEGIAN,"2020-09-22","2020-09-22")

		assertEquals(mockMunicipalityWarning,warnings[0])
    }

    @Test
    fun landslideGetMunicipalities() {

		var testCountyNr = "30" //County ID - Viken
		var numCountys = 51 //Number of municipalities in Viken

		val list = landslideGetMunicipalities(testCountyNr)
		assertEquals(list?.size, numCountys)
    }
}