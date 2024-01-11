[app](../../index.md) / [com.example.team25.ui.search](../index.md) / [DangerViewModel](./index.md)

# DangerViewModel

`class DangerViewModel`

ViewModel for DangerFragment.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DangerViewModel()`<br>ViewModel for DangerFragment. |

### Properties

| Name | Summary |
|---|---|
| [area](area.md) | `var area: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [areaId](area-id.md) | `var areaId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [dangerLevel](danger-level.md) | `var dangerLevel: <ERROR CLASS>` |
| [date](date.md) | `var date: <ERROR CLASS>` |
| [day](day.md) | `var day: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [disaster](disaster.md) | `var disaster: `[`Disaster`](../../com.example.team25.utils/-disaster/index.md)<br>The fields below are just temporary date and will be overwritten on fragment creation if initialized == false. |
| [higherOrderArea](higher-order-area.md) | `var higherOrderArea: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [initialized](initialized.md) | `var initialized: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [mainText](main-text.md) | `var mainText: <ERROR CLASS>` |
| [month](month.md) | `var month: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [year](year.md) | `var year: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [initialize](initialize.md) | `fun initialize(dangerInformation: `[`DangerInformation`](../../com.example.team25.utils/-danger-information/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Function to be called on for initializing data in the ViewModel. This is only called from DangerFragment upon its original creation. |
| [updateInformation](update-information.md) | `fun updateInformation(searchDate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Makes a new request to the API to update the information given a new date. This function is typically called upon by the DatePickerFragment. |
