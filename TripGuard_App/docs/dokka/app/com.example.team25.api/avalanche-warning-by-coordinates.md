[app](../index.md) / [com.example.team25.api](index.md) / [avalancheWarningByCoordinates](./avalanche-warning-by-coordinates.md)

# avalancheWarningByCoordinates

`fun avalancheWarningByCoordinates(latitude: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, longitude: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, lang: `[`Language`](../com.example.team25.utils/-language/index.md)`, startDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AvalancheWarningSimple`](-avalanche-warning-simple/index.md)`>`

Returns a list of simple avalanche warnings for a given coordinate within a closed time interval.

Dates are strings of the format '2020-03-29'. This should be replaced with some proper type.

### Parameters

`latitude` - : The latitude part of the coordinate as a string.

`longitude` - : The longitude part of the coordinate as a string.

`lang` - : The language, which is either norwegian or english.

`startDate` - : The start date of the interval where we are searching for avalanche warnings.

`endDate` - : The end date of the interval where we are searching for avalanche warnings.

**Return**
a list of avalanche warnings.

