[app](../index.md) / [com.example.team25.api](index.md) / [avalancheWarningByRegion](./avalanche-warning-by-region.md)

# avalancheWarningByRegion

`fun avalancheWarningByRegion(regionID: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, lang: `[`Language`](../com.example.team25.utils/-language/index.md)`, startDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AvalancheWarningSimple`](-avalanche-warning-simple/index.md)`>`

Returns a list of simple avalanche warnings for a given region within a closed time interval.
**Note:** api calls should always be done async with a try catch, as both HTTP errors and parsing
errors may occur.

Dates are strings of the format '2020-03-29'. This should be replaced with some proper type.

### Parameters

`regionID` - : The regionID (see ../utils/AvalancheRegions.kt).

`lang` - : The language, which is either norwegian or english.

`startDate` - : The start date of the interval where we are searching for avalanche warnings.

`endDate` - : The end date of the interval where we are searching for avalanche warnings.

**Return**
a list of avalanche warnings.

