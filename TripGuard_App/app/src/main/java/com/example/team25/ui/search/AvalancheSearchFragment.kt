package com.example.team25.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*

import androidx.navigation.Navigation
import com.example.team25.R
import com.example.team25.api.AvalancheWarningSimple
import com.example.team25.api.avalancheWarningByCoordinates
import com.example.team25.api.avalancheWarningByRegion
import com.example.team25.lastRegisterLocation
import com.example.team25.utils.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


/**
 * Fragment for searching after avalanches.
 */
class AvalancheSearchFragment : Fragment() {
	/* This warning is a lie. */
	@SuppressLint("SimpleDateFormat")
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(
			R.layout.fragment_avalanche_search,
			container,
			false
		)
		val inputSearchTextView: AutoCompleteTextView = root.findViewById(R.id.input_search_avalanche)
		val loadingProgressBar: ProgressBar = root.findViewById(R.id.loading_progress_bar)
		val regionStrings = placeToAvalancheRegionID.keys

		context?.let {
			ArrayAdapter<String>(
				it,
				android.R.layout.simple_list_item_1,
				regionStrings.toTypedArray()
			).also { adapter ->
				inputSearchTextView.setAdapter(adapter)
			}
		}

		inputSearchTextView.setOnItemClickListener { parent, _, position, _ ->
			// parent.getItemAtPosition is the string of the autocompleted hint clicked at
			val area = parent.getItemAtPosition(position).toString()
			loadingProgressBar.visibility = View.VISIBLE
			gotoRegion(area, root, inputSearchTextView)
		}

		val locationButton: Button = root.findViewById(R.id.location_button)
		val regionListButton: Button = root.findViewById(
			R.id.button_avalanche_region_list
		)

		/* Shit code but whatever */
		locationButton.setOnClickListener {
				GlobalScope.launch {
					try {
						val currentDate: String = SimpleDateFormat("yyyy-MM-dd").format(Date())
						val avalancheWarnings = avalancheWarningByCoordinates(
							lastRegisterLocation?.latitude.toString(),
							lastRegisterLocation?.longitude.toString(),
							Language.NORWEGIAN,
							currentDate,
							currentDate
						)

						switchToDangerFragment(root, avalancheWarnings[0])
					} catch (e: Exception) {
						handleFailedCallByCoordinates(e)
					}
				}
		}

		regionListButton.setOnClickListener {
			Navigation.findNavController(root)
				.navigate(R.id.avalanche_region_list_fragment)
		}

		return root
	}

	private fun handleFailedCallByCoordinates(e: Exception) {
		/**
		 * Data race might mess this up, so wrapping in try catch.
		 */
		try {
			activity?.runOnUiThread {
				println(e)
				val toastMessage = when (e) {
					/* IllegalStateException means NVE api could'nt find any info */
					is java.lang.IllegalStateException -> {
						"Ingen region tilhørende lokasjon funnet."
					}
					/* com.google.gson.JsonSyntaxException means we couldn't find the GPS location */
					is com.google.gson.JsonSyntaxException -> {
						"Kunne ikke hente GPS posisjon."
					}
					else -> {
						"Noe gikk galt."
					}
				}

				Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show()
			}
		} catch (e: Exception) {
			println(e)
		}
	}

	private fun switchToDangerFragment(root: View, warning: AvalancheWarningSimple) {
		val dangerInformation = DangerInformation(
			Disaster.AVALANCHE,
			warning.RegionId.toString(),
			warning.DangerLevel,
			warning.RegionName,
			"Region",
			warning.MainText
		)

		val bundle = createDangerInformationBundle(dangerInformation)
		activity?.runOnUiThread {
			hideKeyboard(root)
			Navigation.findNavController(root).navigate(R.id.danger_fragment, bundle)
		}
	}

	/**
	 * Help function for navigating to a region as an autocompleted suggestion is clicked.
	 */
	private fun gotoRegion(area: String, root: View, inputSearchTextView: AutoCompleteTextView) {
		GlobalScope.launch {
			try {
				val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
				val regionId = placeToAvalancheRegionID[area]

				if (regionId == null) showError("Noe gikk galt", root)

				val warnings = avalancheWarningByRegion(
					regionId!!,
					Language.NORWEGIAN,
					currentDate,
					currentDate
				)

				if (warnings.isEmpty()) showError("Ingen varsler funnet", root)

				val dangerLevel = warnings[0].DangerLevel
				val mainText = warnings[0].MainText
				val dangerInformation = DangerInformation(
					Disaster.AVALANCHE,
					regionId.toString(),
					dangerLevel,
					area,
					"Region",
					mainText
				)

				/* Nested try-catch is required as inner one is running on different thread */
				val bundle = createDangerInformationBundle(dangerInformation)
				activity?.runOnUiThread {
					try {
						hideKeyboard(root)
						inputSearchTextView.setText("")
						Navigation.findNavController(root).navigate(R.id.danger_fragment, bundle)
					} catch (e: Exception) {
						println(e)
					}
				}
			} catch (e: Exception) {
				println(e)
				showError("Noe gikk galt", root)
			}
		}
	}

	/**
	 * Keyboard is open when a region is clicked, but has to be removed upon fragment transition.
	 */
	private fun hideKeyboard(root: View) {
		val imm = root.context.getSystemService(
			Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(root.windowToken, 0)
	}

	/**
	 * Shows toast and returns to main search menu.
	 */
	private fun showError(toastMessage: String, root: View) {
		/**
		 * Data race might mess this up, so wrapping in try catch.
		 * Nested try-catches are required as the inner one runs on different thread.
		 */
		try {
			requireActivity().runOnUiThread {
				try {
					val duration = Toast.LENGTH_LONG
					val toast = Toast.makeText(activity, toastMessage, duration)
					toast.show()

					hideKeyboard(root)
					Navigation.findNavController(root).navigate(R.id.navigation_search)
				} catch (e: Exception) {
					println(e)
				}
			}
		} catch (e: Exception) {
			println(e)
		}
	}
}
