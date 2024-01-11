[app](../../index.md) / [com.example.team25.persistence](../index.md) / [SavedLocation](./index.md)

# SavedLocation

`data class SavedLocation`

Data class for saving the locations such that the user can get notifications if anything
happens at location.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SavedLocation(areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, disaster: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, area: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, status: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`<br>Data class for saving the locations such that the user can get notifications if anything happens at location. |

### Properties

| Name | Summary |
|---|---|
| [area](area.md) | `val area: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>: String to recognise the area. |
| [areaId](area-id.md) | `val areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>: The id to identify the place for notifications. RegionId or MunicipalityId. |
| [disaster](disaster.md) | `val disaster: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>: The type of disaster. Avalanche(0), Flood(1), Municipality(2). |
| [status](status.md) | `val status: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>: Are we currently supposed to give notifications for this saved location or no? |
