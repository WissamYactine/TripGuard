package com.example.team25.backGroundServices

import android.Manifest
import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.preference.PreferenceManager
import com.example.team25.*
import com.example.team25.api.avalancheWarningByCoordinates
import com.example.team25.utils.Language
import com.example.team25.utils.createNotification
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class BackgroundLocation : Service() {

	/**
	 * @var locationManager: This call a class that provides access to the system location services.
	 * @var gps: The boolean telling us if the user activated he's device's location.
	 * @var locationGps: The current position of the user.
	 *
	 * We redefine locationManager and gps here so that the location updates can happen even tho the mainActivity is killed.
	 * If not, getGps() would not have access to those var from MainActivity if it is killed
	 */
	private lateinit var locationManager: LocationManager
	var gps = false
	var locationGps: Location? = null
	lateinit var sharedPreferences: SharedPreferences
	var isLocationSetOn = true
	lateinit var backGround: String
	var missedLocation = false
	var lastRegisteredRegion = ""
	var lastRegisteredLevel = -1

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
		gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
		isLocationSetOn = sharedPreferences.getBoolean("location", false)
		backGround = sharedPreferences.getString("location_reply", "").toString()
		ActivityManager.getMyMemoryState(myProcess)
		isInBackground = myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
		getGps()
		return START_STICKY
	}

	override fun onBind(intent: Intent?): IBinder? {
		return null
	}

	/**
	 * Get the last know location from user.
	 * The update is made every three seconds only if the user has moved three meters.
	 *
	 * Param of requestLocationUpdates:
	 * @param provider: The gps provider from the LocationManager defined at the beginning
	 * @param minTime: The minimum time required to update the user's location
	 * @param minDistance: The minimum distance needed to update the user's location
	 * @param object: The class LocationListener that will execute findRisks() every time the user
	 * has a new location
	 */
	private fun getGps() {
		if (backGround != "reply_all" && isInBackground) {
			return
		}
		if (gps) {
			if (ActivityCompat.checkSelfPermission(
					this,
					Manifest.permission.ACCESS_FINE_LOCATION
				) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
					this,
					Manifest.permission.ACCESS_COARSE_LOCATION
				) != PackageManager.PERMISSION_GRANTED) {
					return
				}
			locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				5000, // 5 sec, should maybe be more, but nice for testing and presentation
				1000F, // 1 km
				object : android.location.LocationListener {
						override fun onLocationChanged(location: Location?) {
							isLocationSetOn = sharedPreferences.getBoolean("location", false)
							dangerWarning = sharedPreferences.getBoolean("dangerNotification", true)
							if (!isLocationSetOn) {
								accessGranted = sharedPreferences.getBoolean("location", false)
								return
							}
							if (!dangerWarning) {
								return
							}
							updateLocation(location)
							sendNotification()
						}

						override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

						override fun onProviderEnabled(provider: String?) {}

						override fun onProviderDisabled(provider: String?) {}
					})
				val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
				if (lastLocation != null)
					locationGps = lastLocation
			}
	}

	fun updateLocation(location: Location?) {
		if (location == null && !missedLocation) {
			println("Current location is missed!")
			missedLocation = true
		} else {
			try {
			    lastRegisterLocation = location
			} catch (e: Exception) {
				println(e)
			}
			locationGps = location
			missedLocation = false
		}
	}

	@SuppressLint("SimpleDateFormat")
	fun sendNotification() {
		if (!missedLocation) {
			val date = SimpleDateFormat("yyyy-MM-dd").format(Date())

			GlobalScope.launch {
				try {
					val dangers = avalancheWarningByCoordinates(
						locationGps!!.latitude.toString(),
						locationGps!!.longitude.toString(),
						Language.NORWEGIAN,
						date,
						date)

					val dangerLevel = sharedPreferences.getInt("levelDangerNotification", 0)

					if (dangers[0].DangerLevel >= dangerLevel) {
						if (dangers[0].RegionName != lastRegisteredRegion ||
							dangers[0].DangerLevel != lastRegisteredLevel) {

							lastRegisteredLevel = dangers[0].DangerLevel
							lastRegisteredRegion = dangers[0].RegionName
							createNotification(0,
								"Fare i nåværende posisjon",
								"Nåværendre posisjon: ${dangers[0].RegionName}\nFare nivå: $lastRegisteredLevel",
								this@BackgroundLocation)
						}
					}
				} catch (e: Exception) {
					println(e)
					println("Could not access this region!")
				}
			}
		}
	}
}