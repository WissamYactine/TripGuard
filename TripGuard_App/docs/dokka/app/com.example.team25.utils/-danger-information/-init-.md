[app](../../index.md) / [com.example.team25.utils](../index.md) / [DangerInformation](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`DangerInformation(disaster: `[`Disaster`](../-disaster/index.md)`, areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dangerLevel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, area: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, higherOrderArea: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, mainText: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`

This data class is used to keep track of data before passing it onto the dangerFragment.

### Parameters

`disaster` - : The type of disaster.

`areaId` - : Unique identifier for area. This is regionId if avalanche, else municipalityNr.

`dangerLevel` - : How dangerous it currently is.

`area` - : The place to show information of. This string will be at the top of the fragment.

`higherOrderArea` - : The string which will be below `place`. Typically the county we are in.

`mainText` - : The string which contains some tiny information about the danger.