[app](../index.md) / [com.example.team25.api](index.md) / [landslideGetMunicipalities](./landslide-get-municipalities.md)

# landslideGetMunicipalities

`fun landslideGetMunicipalities(countyNr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Municipality`](-municipality/index.md)`>?`

Returns a list of municipalities for a given county.

The NVE API for some reason has two different calls for getting municipalities for a given
county; one for flood and one for landslide. There will be a somewhat equivalent function
for floods.

For another stupid reason, the API call returns a list of a single object, with another list
in the object which has the data we actually want. This results in us have to have a stupid
data class wrappers for the json.

### Parameters

`countyNr` - : Number of county: They are specified here: https://www.ssb.no/klass/klassifikasjoner/104

**Return**
a list of municipalities for the given county.

