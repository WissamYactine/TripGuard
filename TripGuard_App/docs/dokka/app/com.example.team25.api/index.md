[app](../index.md) / [com.example.team25.api](./index.md)

## Package com.example.team25.api

### Types

| Name | Summary |
|---|---|
| [AvalancheWarningSimple](-avalanche-warning-simple/index.md) | `data class AvalancheWarningSimple`<br>Data class for JSON response from API call. |
| [CatastropheCause](-catastrophe-cause/index.md) | `data class CatastropheCause` |
| [Municipality](-municipality/index.md) | `data class Municipality` |
| [MunicipalityListWrapper](-municipality-list-wrapper/index.md) | `data class MunicipalityListWrapper` |
| [MunicipalityWarning](-municipality-warning/index.md) | `data class MunicipalityWarning` |

### Functions

| Name | Summary |
|---|---|
| [avalancheWarningByCoordinates](avalanche-warning-by-coordinates.md) | `fun avalancheWarningByCoordinates(latitude: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, longitude: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, lang: `[`Language`](../com.example.team25.utils/-language/index.md)`, startDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AvalancheWarningSimple`](-avalanche-warning-simple/index.md)`>`<br>Returns a list of simple avalanche warnings for a given coordinate within a closed time interval. |
| [avalancheWarningByRegion](avalanche-warning-by-region.md) | `fun avalancheWarningByRegion(regionID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, lang: `[`Language`](../com.example.team25.utils/-language/index.md)`, startDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AvalancheWarningSimple`](-avalanche-warning-simple/index.md)`>`<br>Returns a list of simple avalanche warnings for a given region within a closed time interval. **Note:** api calls should always be done async with a try catch, as both HTTP errors and parsing errors may occur. |
| [floodGetMunicipalities](flood-get-municipalities.md) | `fun floodGetMunicipalities(countyNr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Municipality`](-municipality/index.md)`>?`<br>Returns a list of municipalities for a given county. |
| [floodWarningByMunicipality](flood-warning-by-municipality.md) | `fun floodWarningByMunicipality(municipalityNr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, lang: `[`Language`](../com.example.team25.utils/-language/index.md)`, startDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MunicipalityWarning`](-municipality-warning/index.md)`>`<br>Returns a list of warnings for the municipality specified. For each day in the date interval, there will be one element in the returned list. **Note:** api calls should always be done async with a try catch, as both HTTP errors and parsing errors may occur. |
| [landslideGetMunicipalities](landslide-get-municipalities.md) | `fun landslideGetMunicipalities(countyNr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Municipality`](-municipality/index.md)`>?`<br>Returns a list of municipalities for a given county. |
| [landslideWarningByMunicipality](landslide-warning-by-municipality.md) | `fun landslideWarningByMunicipality(municipalityNr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, lang: `[`Language`](../com.example.team25.utils/-language/index.md)`, startDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MunicipalityWarning`](-municipality-warning/index.md)`>`<br>Returns a list of warnings for the municipality specified. For each day in the date interval, there will be one element in the returned list. **Note:** api calls should always be done async with a try catch, as both HTTP errors and parsing errors may occur. |