package com.example.team25

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.team25.backGroundServices.BackgroundFetchData
import com.example.team25.backGroundServices.BackgroundLocation
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * Creating all the variables needed for the location
 *
 * @var permissions: An array of permissions required to get the user's location
 * @var locationManager: This call a class that provides access to the system location services.
 * @var gps: The boolean telling us if the user activated he's device's location.
 *
 * The locationManager and gps variables are defined in the onCreate function.
 * This will allow us to check weather or not the user gave his permission and activated
 * location on his device.
 */
var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
	android.Manifest.permission.ACCESS_COARSE_LOCATION)
lateinit var locationManager: LocationManager
var gps = false
var lastRegisterLocation : Location? = null
lateinit var sharedPreferences: SharedPreferences
var accessGranted = true
var dangerWarning = true
var isInBackground = true
var myProcess = ActivityManager.RunningAppProcessInfo()

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val navView: BottomNavigationView = findViewById(R.id.nav_view)
		val navController = findNavController(R.id.nav_host_fragment)
		navView.setupWithNavController(navController)

		val fetchIntent = Intent(this, BackgroundFetchData::class.java)
		startService(fetchIntent)

		locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
		gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
		accessGranted = sharedPreferences.getBoolean("location", false)
		dangerWarning = sharedPreferences.getBoolean("dangerNotification", true)

		if (!isNetworkAvailable()) {
			Toast.makeText(this,
				"Mangler wifi/4G, mesteparten av funksjonaliteten vil ikke fungere",
				Toast.LENGTH_LONG).show()
		}

		if (accessGranted && checkPermissions()) {
			sharedPreferences.edit().putBoolean("location", true).apply()
			if (gps && dangerWarning) {
				val locationIntent = Intent(this, BackgroundLocation::class.java)
				startService(locationIntent)
			}
		} else {
			getPermission()
		}
	}

	/**
	 * This function request the permissions needed to get access to the user's location.
	 */
	private fun getPermission() {
		ActivityCompat.requestPermissions(
			this,
			permissions,
			1
		)
		val handler = Handler()
		handler.postDelayed(Runnable {
			startBackgroundLocation()
		}, 8000) //10 seconds
	}

	/**
	 * Check if the user gave the permission to access his location.
	 * Check also if the location setting in the user's device is activated.
	 *@return Boolean : If the user gave permission -> true
	 * 					Else -> false
	 */
	private fun checkPermissions(): Boolean {
		var isGranted: Boolean = true
		for( i in permissions.indices) {
			if (ActivityCompat.checkSelfPermission(this, permissions[i]) == PackageManager.PERMISSION_DENIED) {
				isGranted = false
			}
		}
		if (isGranted) {
			accessGranted = true
			if (!gps){
				switchOnLocation()
			}
		}
		return isGranted
	}

	/**
	 * Open the devices settings allowing the user to activate location on the device.
	 */
	private fun switchOnLocation() {
		startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
		val handler = Handler()
		handler.postDelayed(Runnable {
			gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
		}, 5000) //10 seconds
	}

	private fun startBackgroundLocation() {
		if (checkPermissions()) {
			if (accessGranted) {
				sharedPreferences.edit().putBoolean("location", true).apply()
				val handler = Handler()
				handler.postDelayed(Runnable {
					dangerWarning = sharedPreferences.getBoolean("dangerNotification", true)
					if (gps && dangerWarning) {
						val intent = Intent(this, BackgroundLocation::class.java)
						startService(intent)
					}
				}, 5000) //10 seconds
			}
		}
	}

	/**
	 * Use in home page fragment! If false : Change søke funksjonalitet layout! Change Textview to can't search
	 */
	private fun isNetworkAvailable(): Boolean {
		val connectivityManager =
			getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeNetworkInfo = connectivityManager.activeNetwork
		return activeNetworkInfo != null
	}
}
