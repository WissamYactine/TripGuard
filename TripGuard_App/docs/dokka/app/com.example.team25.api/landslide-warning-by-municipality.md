[app](../index.md) / [com.example.team25.api](index.md) / [landslideWarningByMunicipality](./landslide-warning-by-municipality.md)

# landslideWarningByMunicipality

`fun landslideWarningByMunicipality(municipalityNr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, lang: `[`Language`](../com.example.team25.utils/-language/index.md)`, startDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, endDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`MunicipalityWarning`](-municipality-warning/index.md)`>`

Returns a list of warnings for the municipality specified. For each day in the date interval,
there will be one element in the returned list. **Note:** api calls should always be done async
with a try catch, as both HTTP errors and parsing errors may occur.

Dates are strings of the format '2020-03-29'. This should be replaced with some proper type.

### Parameters

`municipalityNr` - : Number of municipality. They are specified here: https://www.ssb.no/klass/klassifikasjoner/131

`lang` - : The language, which is either norwegian or english.

`startDate` - : The start date of the interval where we are searching for avalanche warnings.

`endDate` - : The end date of the interval where we are searching for avalanche warnings.

**Return**
a list of warnings for a specified municipality.

