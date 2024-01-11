package com.example.team25.utils

val countyIDToPlace = mapOf(
	"03" to "Oslo",
	"11" to "Rogaland",
	"15" to "Møre og Romsdal",
	"18" to "Nordland",
	"30" to "Viken",
	"34" to "Innlandet",
	"38" to "Vestfold og Telemark",
	"42" to "Agder",
	"46" to "Vestland",
	"50" to "Trøndelag",
	"54" to "Troms og Finnmark"
)

// Swap keys and values of the map above.
/**
 * An immutable map which maps Countys in Norway to IDs specified by NVE.
 */
val placeToCountyID = countyIDToPlace.toList()
	.map{ (x,y) -> Pair(y, x) }.toMap()