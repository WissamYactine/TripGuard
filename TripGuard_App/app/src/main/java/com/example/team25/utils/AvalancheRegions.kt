package com.example.team25.utils

/**
 * An immutable map which maps avalanche regionIDs specified by NVE to places in Norway.
 */
val avalancheRegionIDToPlace = mapOf(
	// A-regions
	"3003" to "Nordenskiöld Land",
	"3006" to	"Finnmarkskysten",
	"3007" to "Vest-Finnmark",
	"3009" to	"Nord-Troms",
	"3010" to	"Lyngen",
	"3011" to "Tromsø",
	"3012" to	"Sør-Troms",
	"3013" to "Indre Troms",
	"3014" to "Lofoten og Vesterålen",
	"3015" to "Ofoten",
	"3016" to	"Salten",
	"3017" to	"Svartisen",
	"3022" to "Trollheimen",
	"3023" to "Romsdal",
	"3024" to "Sunnmøre",
	"3027" to "Indre Fjordane",
	"3028" to "Jotunheimen",
	"3029" to "Indre Sogn",
	"3031" to "Voss",
	"3032" to "Hallingdal",
	"3034" to "Hardanger",
	"3035" to "Vest-Telemark",
	"3037" to "Heiane",

	// B-regions
	"3001" to "Svalbard øst",
	"3002" to "Svalbard vest",
	"3004" to "Svalbard sør",
	"3005" to "Øst-Finnmark",
	"3008" to "Finnmarksvidda",
	"3018" to "Helgeland",
	"3019" to "Nord-Trøndelag",
	"3020" to "Sør-Trøndelag",
	"3021" to "Ytre Nordmøre",
	"3025" to "Nord-Gudbrandsdalen",
	"3026" to "Ytre Fjordane",
	"3030" to "Ytre Sogn",
	"3033" to "Hordalandskysten",
	"3036" to "Rogalandskysten",
	"3038" to "Agder sør",
	"3039" to "Telemark sør",
	"3040" to "Vestfold",
	"3041" to "Buskerud sør",
	"3042" to "Oppland sør",
	"3043" to "Hedmark",
	"3044" to "Akershus",
	"3045" to "Oslo",
	"3046" to "Østfold"
)

// Swap keys and values of the map above.
/**
 * An immutable map which maps places in Norway to avalanche regionIDs specified by NVE.
 */
val placeToAvalancheRegionID = avalancheRegionIDToPlace.toList()
	.map{ (x,y) -> Pair(y, x) }.toMap()
