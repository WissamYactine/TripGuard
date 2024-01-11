package com.example.team25.backGroundServices


import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.os.IBinder
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.team25.api.avalancheWarningByRegion
import com.example.team25.api.floodWarningByMunicipality
import com.example.team25.api.landslideWarningByMunicipality
import com.example.team25.persistence.AppDatabase
import com.example.team25.persistence.SavedLocation
import com.example.team25.utils.Language
import com.example.team25.utils.createNotification
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class BackgroundFetchData : Service() {
	lateinit var sharedPreferences: SharedPreferences
	var lastCountDown: Int = 0
	private var timeChoice = 0
	var body = ""

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		super.onStartCommand(intent, flags, startId)
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
		timeChoice = sharedPreferences.getInt("sync", 0)
		lastCountDown = timeChoice
		when (timeChoice) {
			0 -> fetchData(TimeUnit.MINUTES.toMillis(15)) //CountDown is 15 min
			1 -> fetchData(TimeUnit.MINUTES.toMillis(30)) //CountDown is 30 min
			2 -> fetchData(TimeUnit.MINUTES.toMillis(45)) //CountDown is 45 min
			else -> fetchData(TimeUnit.MINUTES.toMillis(((timeChoice - 2) * 60).toLong())) // 1 + Hours
		}
		return START_STICKY
	}

	override fun onBind(intent: Intent?): IBinder? {
		return null
	}

	private fun fetchData(time: Long) {
		object : CountDownTimer(time, 60000) {
			override fun onFinish() {
				/**
				 * Check dangers and send notif for all saved location with matching dangers!
				 */
				val allowed = sharedPreferences.getBoolean("locationNotification", true)

				if (allowed) {
					GlobalScope.launch {
						val db = Room.databaseBuilder(
							this@BackgroundFetchData,
							AppDatabase::class.java, "savedLocations-db"
						).fallbackToDestructiveMigration().build()

						val locationList = db.savedLocationDao().getAll().toMutableList()
						body = ""

						for (SavedLocation in locationList) {
							if (SavedLocation.status) {
								when (SavedLocation.disaster) {
									0 -> checkAvalanche(SavedLocation)
									1 -> checkFlood(SavedLocation)
									2 -> checkLandSlide(SavedLocation)
								}
							}
						}
						val switch = sharedPreferences.getBoolean(
							"locationNotification",
							true
						)
						if (switch && body != "") {
							createNotification(
								1,
								"Mine Lokasjoner Oppdatering",
								body,
								this@BackgroundFetchData
							)
						}
					}
				}
				start()
			}

			override fun onTick(millisUntilFinished: Long) {
				/**
				 * Check si le temps a ete change!
				 * Si oui stop + start a new service with correct time!
				 */
				timeChoice = sharedPreferences.getInt("sync", 0)
				if (timeChoice != lastCountDown) {
					lastCountDown = timeChoice
					cancel()
					when (timeChoice) {
						0 -> fetchData(TimeUnit.MINUTES.toMillis(15)) //CountDown is 15 min
						1 -> fetchData(TimeUnit.MINUTES.toMillis(30)) //CountDown is 30 min
						2 -> fetchData(TimeUnit.MINUTES.toMillis(45)) //CountDown is 45 min
						else -> fetchData(TimeUnit.MINUTES.toMillis(((timeChoice - 2) * 60).toLong())) // 1 + Hours
					}
				}
			}
		}.start()
	}

	@SuppressLint("SimpleDateFormat")
	fun checkAvalanche(location: SavedLocation) {
		val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
		val dangerLevel = sharedPreferences.getInt("levelDangerNotification", 0)

		val dangers = avalancheWarningByRegion(
			location.areaId,
			Language.NORWEGIAN,
			date,
			date
		)

		if (dangers.isNotEmpty()) {
			if (dangers[0].DangerLevel >= dangerLevel) {
				body += "Posisjon: ${location.area}\n" +
					"Fare type: Avalanche\n" +
					"Farenivå: ${dangers[0].DangerLevel}\n\n"
			}
		}
	}

	@SuppressLint("SimpleDateFormat")
	fun checkFlood(location: SavedLocation) {
		val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
		val dangerLevel = sharedPreferences.getInt("levelDangerNotification", 0)

		val dangers = floodWarningByMunicipality(
			location.areaId,
			Language.NORWEGIAN,
			date,
			date
		)

		if (dangers.isNotEmpty()) {
			if (dangers[0].ActivityLevel >= dangerLevel) {
				body += "Posisjon: ${location.area}\n" +
					"Fare type: Flood\n" +
					"Farenivå: ${dangers[0].ActivityLevel}\n\n"
			}
		}
	}

	@SuppressLint("SimpleDateFormat")
	fun checkLandSlide(location: SavedLocation) {
		val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
		val dangerLevel = sharedPreferences.getInt("levelDangerNotification", 0)

		val dangers = landslideWarningByMunicipality(
			location.areaId,
			Language.NORWEGIAN,
			date,
			date
		)

		if (dangers.isNotEmpty()) {
			if (dangers[0].ActivityLevel >= dangerLevel) {
				body += "Posisjon: ${location.area}\n" +
					"Fare type: Landslide\n" +
					"Farenivå: ${dangers[0].ActivityLevel}\n\n"
			}
		}
	}
}