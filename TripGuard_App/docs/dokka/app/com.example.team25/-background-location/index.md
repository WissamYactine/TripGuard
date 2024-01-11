[app](../../index.md) / [com.example.team25](../index.md) / [BackgroundLocation](./index.md)

# BackgroundLocation

`class BackgroundLocation`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BackgroundLocation()` |

### Properties

| Name | Summary |
|---|---|
| [gps](gps.md) | `var gps: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [locationGps](location-gps.md) | `var locationGps: <ERROR CLASS>?` |
| [locationManager](location-manager.md) | `lateinit var locationManager: <ERROR CLASS>` |

### Functions

| Name | Summary |
|---|---|
| [getGps](get-gps.md) | `fun getGps(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Get the last know location from user. The update is made every three seconds only if the user has moved three meters. |
| [onBind](on-bind.md) | `fun onBind(intent: <ERROR CLASS>?): <ERROR CLASS>?` |
| [onStartCommand](on-start-command.md) | `fun onStartCommand(intent: <ERROR CLASS>?, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, startId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
