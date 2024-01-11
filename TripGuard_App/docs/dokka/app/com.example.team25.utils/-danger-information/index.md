[app](../../index.md) / [com.example.team25.utils](../index.md) / [DangerInformation](./index.md)

# DangerInformation

`data class DangerInformation`

This data class is used to keep track of data before passing it onto the dangerFragment.

### Parameters

`disaster` - : The type of disaster.

`areaId` - : Unique identifier for area. This is regionId if avalanche, else municipalityNr.

`dangerLevel` - : How dangerous it currently is.

`area` - : The place to show information of. This string will be at the top of the fragment.

`higherOrderArea` - : The string which will be below `place`. Typically the county we are in.

`mainText` - : The string which contains some tiny information about the danger.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DangerInformation(disaster: `[`Disaster`](../-disaster/index.md)`, areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dangerLevel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, area: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, higherOrderArea: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, mainText: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>This data class is used to keep track of data before passing it onto the dangerFragment. |

### Properties

| Name | Summary |
|---|---|
| [area](area.md) | `val area: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>: The place to show information of. This string will be at the top of the fragment. |
| [areaId](area-id.md) | `val areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>: Unique identifier for area. This is regionId if avalanche, else municipalityNr. |
| [dangerLevel](danger-level.md) | `val dangerLevel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>: How dangerous it currently is. |
| [disaster](disaster.md) | `val disaster: `[`Disaster`](../-disaster/index.md)<br>: The type of disaster. |
| [higherOrderArea](higher-order-area.md) | `val higherOrderArea: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>: The string which will be below `place`. Typically the county we are in. |
| [mainText](main-text.md) | `val mainText: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>: The string which contains some tiny information about the danger. |
