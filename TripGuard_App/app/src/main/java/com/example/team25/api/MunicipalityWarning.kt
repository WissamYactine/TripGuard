package com.example.team25.api

data class CatastropheCause(
	val Id: Int,
	val Name: String
)

data class MunicipalityWarning(
	val ActivityLevel: Int,
	val MainText: String?,
	val WarningText: String?,
	val CauseList: List<CatastropheCause>
)
