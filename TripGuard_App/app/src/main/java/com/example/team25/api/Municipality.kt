package com.example.team25.api

data class MunicipalityListWrapper(
	val MunicipalityList: List<Municipality>
)

data class Municipality(
	val Id: Int,
	val Name: String
)

