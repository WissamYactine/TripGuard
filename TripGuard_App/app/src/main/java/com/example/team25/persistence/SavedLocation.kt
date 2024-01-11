package com.example.team25.persistence

import androidx.room.*

/**
 * Data class for saving the locations such that the user can get notifications if anything
 * happens at location.
 *
 * @property areaId: The id to identify the place for notifications. RegionId or MunicipalityId.
 * @property disaster: The type of disaster. Avalanche(0), Flood(1), Municipality(2).
 * @property area: String to recognise the area.
 * @property status: Are we currently supposed to give notifications for this saved location or no?
 */
@Entity(primaryKeys = ["areaId", "disaster"])
data class SavedLocation(
	@ColumnInfo val areaId: String,
	@ColumnInfo val disaster: Int,
	@ColumnInfo val area: String,
	@ColumnInfo var status: Boolean
)

/**
 * Dao for SavedLocation.
 */
@Dao
interface SavedLocationDao {
	@Query("SELECT * FROM savedLocation")
	fun getAll(): List<SavedLocation>

	@Query("SELECT * FROM savedLocation WHERE areaId=:areaId AND disaster=:disaster")
	fun find(areaId: String, disaster: Int): SavedLocation

	@Query("UPDATE savedLocation SET status=:status WHERE areaId=:areaId AND disaster=:disaster")
	fun update(areaId: String, disaster: Int, status: Boolean)

	@Insert
	fun insertAll(vararg savedLocations: SavedLocation)

	@Delete
	fun delete(savedLocation: SavedLocation)
}

/**
 * General database for app. Currently only consisting of data for saved locations.
 */
@Database(entities = [SavedLocation::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
	abstract fun savedLocationDao(): SavedLocationDao
}
