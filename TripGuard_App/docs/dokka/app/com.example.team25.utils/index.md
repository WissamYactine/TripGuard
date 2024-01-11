[app](../index.md) / [com.example.team25.utils](./index.md)

## Package com.example.team25.utils

### Types

| Name | Summary |
|---|---|
| [DangerInformation](-danger-information/index.md) | `data class DangerInformation`<br>This data class is used to keep track of data before passing it onto the dangerFragment. |
| [Disaster](-disaster/index.md) | `enum class Disaster`<br>An enum for coding the different disasters we support. |
| [Language](-language/index.md) | `enum class Language`<br>Norwegian or english language. |

### Properties

| Name | Summary |
|---|---|
| [avalancheRegionIDToPlace](avalanche-region-i-d-to-place.md) | `val avalancheRegionIDToPlace: <ERROR CLASS>`<br>An immutable map which maps avalanche regionIDs specified by NVE to places in Norway. |
| [countyIDToPlace](county-i-d-to-place.md) | `val countyIDToPlace: <ERROR CLASS>` |
| [dangerColor](danger-color.md) | `val dangerColor: <ERROR CLASS>` |
| [placeToAvalancheRegionID](place-to-avalanche-region-i-d.md) | `val placeToAvalancheRegionID: <ERROR CLASS>`<br>An immutable map which maps places in Norway to avalanche regionIDs specified by NVE. |
| [placeToCountyID](place-to-county-i-d.md) | `val placeToCountyID: <ERROR CLASS>`<br>An immutable map which maps Countys in Norway to IDs specified by NVE. |

### Functions

| Name | Summary |
|---|---|
| [createDangerInformationBundle](create-danger-information-bundle.md) | `fun createDangerInformationBundle(dangerInformation: `[`DangerInformation`](-danger-information/index.md)`): <ERROR CLASS>`<br>Creates a bundle from a dangerInformation object. |
| [createNotification](create-notification.md) | `fun createNotification(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, body: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, context: <ERROR CLASS>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Creates a notification from a given context. |
