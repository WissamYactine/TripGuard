package com.example.team25.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.room.Room

import com.example.team25.R
import com.example.team25.persistence.AppDatabase
import com.example.team25.persistence.SavedLocation
import com.example.team25.utils.DangerInformation
import com.example.team25.utils.Disaster
import com.example.team25.utils.dangerColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Fragment for showing dangers of avalanche/flood/landslide over a given area.
 */
class DangerFragment : Fragment() {
	private lateinit var viewModel: DangerViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		viewModel = ViewModelProviders.of(this).get(DangerViewModel::class.java)
		val root = inflater.inflate(R.layout.fragment_danger, container, false)

		val areaText = root.findViewById<TextView>(R.id.text_area)
		val higherOrderAreaText = root.findViewById<TextView>(R.id.text_higher_order_area)
		val dangerLevelText = root.findViewById<TextView>(R.id.text_danger_level)
		val calendarText = root.findViewById<TextView>(R.id.text_search_date)
		val infoTitleText = root.findViewById<TextView>(R.id.info_title)
		val dangerText = root.findViewById<TextView>(R.id.danger_text)

		val backgroundCircle = root.findViewById<TextView>(R.id.circle)
		val calendarButton= root.findViewById<ImageButton>(R.id.calendarButton)
		val bookmarkButton = root.findViewById<Button>(R.id.bookmarkButton)
		val infoButton = root.findViewById<ImageButton>(R.id.infoButton)

		if (!viewModel.initialized) {
			val dangerInformation = getDangerInformation()
			viewModel.initialize(dangerInformation)
		}

		calendarButton.setOnClickListener {
			DatePickerFragment(viewModel)
				.show(activity?.supportFragmentManager!!, "datePicker")
		}

		bookmarkButton.setOnClickListener {
			saveLocation()
		}

		infoButton.setOnClickListener {
			when (viewModel.disaster) {
				Disaster.AVALANCHE -> {
					Navigation.findNavController(root).navigate(R.id.info_snow)
				}
				else -> {
					Navigation.findNavController(root).navigate(R.id.info_flood_landslide)
				}
			}
		}

		when (viewModel.disaster) {
			Disaster.AVALANCHE -> {
				infoTitleText.text = "Varselnivå snøskred"
			}
			Disaster.LANDSLIDE -> {
				infoTitleText.text = "Varselnivå jordskred"
			}
			Disaster.FLOOD -> {
				infoTitleText.text = "Varselnivå flom"
			}
		}

		areaText.text = viewModel.area
		higherOrderAreaText.text = viewModel.higherOrderArea

		val dangerLevelObserver = Observer<Int> { newLevel ->
			if(newLevel!=0) {
				dangerLevelText.text = "Farenivå: ${newLevel}"
			}else{
				dangerLevelText.text="Ingen data"}
			backgroundCircle.background = dangerColor[viewModel.dangerLevel.value]?.let {
				ResourcesCompat.getDrawable(
					resources,
					it,
					null
				)
			}
		}
		val dateObserver = Observer<String> { newDate ->
			calendarText.text = newDate
		}
		val dangerTextObserver = Observer<String> { newText ->
			dangerText.text = newText
		}

		viewModel.dangerLevel.observe(viewLifecycleOwner, dangerLevelObserver)
		viewModel.mainText.observe(viewLifecycleOwner, dangerTextObserver)
		viewModel.date.observe(viewLifecycleOwner, dateObserver)

		return root
	}

	private fun getDangerInformation() : DangerInformation {
		val arguments = requireArguments()
		return DangerInformation(
			arguments.get("disaster") as Disaster, // needs !! since it is of special type.
			arguments.get("areaId") as String,
			arguments.get("dangerLevel") as Int,
			arguments.get("area") as String,
			arguments.get("higherOrderArea") as String,
			arguments.get("mainText") as String
		)
	}

	/**
	 * Saves location so that it shows up in "mine lokasjoner", and we can notify upon dangers.
	 */
	private fun saveLocation() {
		val savedLocation = SavedLocation(
			viewModel.areaId,
			viewModel.disaster.value,
			viewModel.area,
			true
		)
		GlobalScope.launch {
			val db = Room.databaseBuilder(
				requireContext(),
				AppDatabase::class.java, "savedLocations-db"
			).fallbackToDestructiveMigration().build()

			val exists = db.savedLocationDao().find(savedLocation.areaId, savedLocation.disaster)

			// Don't believe the kotlin warning, this will NOT always be false
			@Suppress("SENSELESS_COMPARISON")
			if (exists != null) return@launch
			db.savedLocationDao().insertAll(savedLocation)
			db.close()
		}

		Toast.makeText(context, "Lokasjon lagt til", Toast.LENGTH_SHORT).show()
	}
}
