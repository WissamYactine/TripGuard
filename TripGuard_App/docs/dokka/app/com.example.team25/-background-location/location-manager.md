[app](../../index.md) / [com.example.team25](../index.md) / [BackgroundLocation](index.md) / [locationManager](./location-manager.md)

# locationManager

`lateinit var locationManager: <ERROR CLASS>`

**Var**
locationManager: This call a class that provides access to the system location services.

**Var**
gps: The boolean telling us if the user activated he's device's location.

**Var**

locationGps: The current position of the user.



We redefine locationManager and gps here so that the location updates can happen even tho the mainActivity is killed.
If not, getGps() would not have access to those var from MainActivity if it is killed

