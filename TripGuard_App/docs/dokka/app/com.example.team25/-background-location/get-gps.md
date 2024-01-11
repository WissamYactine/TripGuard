[app](../../index.md) / [com.example.team25](../index.md) / [BackgroundLocation](index.md) / [getGps](./get-gps.md)

# getGps

`fun getGps(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Get the last know location from user.
The update is made every three seconds only if the user has moved three meters.

Param of requestLocationUpdates:

### Parameters

`provider` - : The gps provider from the LocationManager defined at the beginning

`minTime` - : The minimum time required to update the user's location

`minDistance` - : The minimum distance needed to update the user's location

`object` - : The class LocationListener that will execute findRisks() every time the user
has a new location