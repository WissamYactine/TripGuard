package com.example.team25.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.team25.R
import com.example.team25.persistence.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Main fragment for handling "my locations" functionality.
 */
private lateinit var locationsViewModel: LocationsViewModel
private lateinit var recyclerView: RecyclerView

class LocationsFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_my_locations, container, false)
		locationsViewModel =
			ViewModelProviders.of(this).get(LocationsViewModel::class.java)
		locationsViewModel.context = requireContext()

		val preference: Button = root.findViewById(R.id.button_preferences)

		preference.setOnClickListener {
			Navigation.findNavController(root).navigate(R.id.preferenceSettings)
		}

		GlobalScope.launch {
				val db = Room.databaseBuilder(
					requireContext(),
					AppDatabase::class.java, "savedLocations-db"
				).fallbackToDestructiveMigration().build()

				locationsViewModel.locationList = db.savedLocationDao().getAll().toMutableList()
				db.close()

			activity?.runOnUiThread {
				val viewManager = LinearLayoutManager(activity)
				val viewAdapter = LocationsListAdapter(locationsViewModel.locationList)

				recyclerView = root.findViewById<RecyclerView>(
					R.id.recyclerViewWithStoredLocations).apply {
					layoutManager = viewManager
					adapter = viewAdapter
				}
			}
		}

		return root
	}

	companion object {
		fun deleteSavedLocation(pos: Int) {
			val curLocation = locationsViewModel.locationList[pos]
			GlobalScope.launch {
				val db = Room.databaseBuilder(
					locationsViewModel.context,
					AppDatabase::class.java, "savedLocations-db"
				).fallbackToDestructiveMigration().build()

				db.savedLocationDao().delete(curLocation)
				db.close()
			}
			locationsViewModel.locationList.removeAt(pos)
			recyclerView.adapter!!.notifyDataSetChanged()
		}

		fun updateStatus(pos: Int) {
			val areaId = locationsViewModel.locationList[pos].areaId
			val disaster = locationsViewModel.locationList[pos].disaster

			GlobalScope.launch {
				val db = Room.databaseBuilder(
					locationsViewModel.context,
					AppDatabase::class.java, "savedLocations-db"
				).fallbackToDestructiveMigration().build()

				val newStatus = !db.savedLocationDao().find(areaId, disaster).status
				db.savedLocationDao().update(areaId, disaster, newStatus)
				locationsViewModel.locationList[pos].status = newStatus
			}
		}
	}
}

