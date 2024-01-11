package com.example.team25.ui.search

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
import com.example.team25.api.*
import com.example.team25.utils.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date


/**
 * Fragment so for searching which municipality one wants to see dangers of.
 */
class MunicipalitySearch : Fragment() {
	private var dataIsFetched = false

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_municipality_search, container, false)

		val disaster: Disaster = arguments?.get("disasterType") as Disaster
		val currentCounty = arguments?.get("county") as String
		val normalProgressBar: ProgressBar = root.findViewById(R.id.normal_loading_progress_bar)
		val smallProgressBar: ProgressBar = root.findViewById(R.id.small_loading_progress_bar)

		/**
		 * Why fetch municipalities every time instead of storing locally?
		 *
		 * Pro: Municipalities might merge, so if we store locally we might make API calls that
		 * 		doesn't make sense.
		 *
		 * Con: Making the API call and loading them takes time away from user.
 		 */
		root.findViewById<AutoCompleteTextView>(R.id.input_search).setOnClickListener {
			if (!dataIsFetched) smallProgressBar.visibility = View.VISIBLE
		}

		fetchMunicipalities(disaster, currentCounty, root, smallProgressBar, normalProgressBar)

		val disasterTextView: TextView = root.findViewById(R.id.text_search_municipality_title)
		val countyTextView: TextView = root.findViewById(R.id.text_county)

		countyTextView.text = arguments?.get("county") as String

		when (disaster) {
			Disaster.FLOOD -> disasterTextView.text = getString(R.string.search_type_flood)
			Disaster.LANDSLIDE -> disasterTextView.text = getString(R.string.search_type_landslide)
			else -> {
				showError("Noe gikk galt", root)
			}
		}

		return root
	}

	private fun fetchMunicipalities(
		disaster: Disaster,
		currentCounty: String,
		root: View,
		smallProgressBar: ProgressBar,
		normalProgressBar: ProgressBar
	) {
		GlobalScope.launch {
			try {
				val municipalities = placeToCountyID[currentCounty]?.let {
					when (disaster) {
						Disaster.FLOOD -> floodGetMunicipalities(it)
						Disaster.LANDSLIDE -> landslideGetMunicipalities(it)
						else -> null
					}
				}

				if (municipalities == null) showError("Noe gikk galt", root)

				val municipalityStrings = municipalities!!.map {
						municipality: Municipality -> municipality.Name
				}

				setupInputSearch(
					disaster,
					currentCounty,
					municipalities,
					municipalityStrings,
					normalProgressBar,
					root
				)
				requireActivity().runOnUiThread {
					smallProgressBar.visibility = View.INVISIBLE
				}

				dataIsFetched = true
			} catch (e: Exception) {
				println(e)
				showError("Noe gikk galt", root)
			}
		}
	}

	private fun showError(toastMessage: String, root: View) {
		/**
		 * Race condition means requireActivity might not work, as well as Navigation messing up
		 */
		try {
			requireActivity().runOnUiThread {
				hideKeyboard(root)
				Navigation.findNavController(root).navigate(R.id.navigation_search)

				val duration = Toast.LENGTH_LONG
				val toast = Toast.makeText(activity, toastMessage, duration)
				toast.show()
			}
		} catch (e: Exception) {
			println(e)
		}
	}

	private fun setupInputSearch(
		disaster: Disaster,
		currentCounty: String,
		municipalities: List<Municipality>,
		municipalityStrings: List<String>,
		normalProgressBar: ProgressBar,
		root: View
	) {
		activity?.runOnUiThread {
			val inputSearchTextView: AutoCompleteTextView = root.findViewById(R.id.input_search)

			context?.let {
				ArrayAdapter<String>(it, android.R.layout.simple_list_item_1,
					municipalityStrings.toTypedArray()).also { adapter ->
					inputSearchTextView.setAdapter(adapter)
				}
			}

			inputSearchTextView.setOnItemClickListener { parent, _, position, _ ->
				// parent.getItemAtPosition is the string of the autocompleted hint clicked at
				val municipalityName = parent.getItemAtPosition(position) as String
				var municipalityId = "-1"

				for (municipality in municipalities) {
					if (municipality.Name == municipalityName) {
						municipalityId = municipality.Id.toString()
						break
					}
				}

				if (municipalityId == "-1") showError("Kunne ikke finne kommune", root)

				normalProgressBar.visibility = View.VISIBLE
				GlobalScope.launch {
					try {
						val warning = getWarning(disaster, municipalityId, root)

						var mainText = "Ikke vurdert"
						if (warning.MainText != null) mainText = warning.MainText

						val dangerInformation = DangerInformation(
							disaster,
							municipalityId,
							warning.ActivityLevel,
							municipalityName,
							currentCounty,
							mainText
						)

						requireActivity().runOnUiThread {
							root.findViewById<ProgressBar>(R.id.normal_loading_progress_bar)
								.visibility = View.INVISIBLE
						}
						gotoMunicipality(dangerInformation, inputSearchTextView, root)
					} catch (e: Exception) {
						println(e)
						showError("Noe gikk galt", root)
					}
				}
			}
		}
	}

	private fun gotoMunicipality(
		dangerInformation: DangerInformation,
		inputSearchTextView: AutoCompleteTextView,
		root: View
	) {
		/**
		 * Race condition:
		 * This means we have most likely changed navigation component so that our
		 * original view is no longer within reach of Navigation.
		 */
		try {
			activity?.runOnUiThread {
				try {
					hideKeyboard(root)
					inputSearchTextView.setText("")

					val bundle = createDangerInformationBundle(dangerInformation)
					Navigation.findNavController(root).navigate(R.id.danger_fragment, bundle)
				} catch (e: Exception) {
					println(e)
				}
			}
		} catch (e: Exception) {
			println(e)
		}
	}

	/**
	 * Helper function to decide what API call to use when retrieving warning for municipality.
	 *
	 * @param disaster: The type of disaster we are searching on.
	 * @param municipalityId: The id of the municipality we are searching on.
	 * @return the municipality warning.
	 */
	private fun getWarning(
		disaster: Disaster,
		municipalityId: String,
		root: View
	) : MunicipalityWarning {
		val warning = when (disaster) {
			Disaster.FLOOD -> getFloodWarning(municipalityId)
			Disaster.LANDSLIDE -> getLandslideWarning(municipalityId)
			else -> {
				showError("Noe gikk galt", root)
				null
			}
		}

		return warning!!
	}

	/**
	 * Fetches flood warning for given county on current date.
	 *
	 * @param municipalityId: The id of the municipality we are searching on.
	 * @return the municipality warning.
	 */
	private fun getFloodWarning(municipalityId: String) : MunicipalityWarning {
		val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
		val warnings = floodWarningByMunicipality(
			municipalityId,
			Language.NORWEGIAN,
			currentDate,
			currentDate
		)
		return warnings[0]
	}

	/**
	 * Fetches landslide warning for given county on current date.
	 *
	 * @param municipalityId: The id of the municipality we are searching on.
	 * @return the municipality warning.
	 */
	private fun getLandslideWarning(municipalityId: String) : MunicipalityWarning {
		val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
		val warnings = landslideWarningByMunicipality(
			municipalityId,
			Language.NORWEGIAN,
			currentDate,
			currentDate
		)

		return warnings[0]
	}

	/**
	 * Keyboard is open when a region is clicked, but has to be removed upon fragment transition.
	 *
	 * @param root: The root view of the fragment.
	 */
	private fun hideKeyboard(root: View) {
		val imm = root.context.getSystemService(
			Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(root.windowToken, 0)
	}
}
