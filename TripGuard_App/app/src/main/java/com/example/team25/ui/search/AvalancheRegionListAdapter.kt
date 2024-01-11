package com.example.team25.ui.search

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.team25.R
import com.example.team25.api.avalancheWarningByRegion
import com.example.team25.utils.DangerInformation
import com.example.team25.utils.Disaster
import com.example.team25.utils.Language
import com.example.team25.utils.createDangerInformationBundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Adapter for RecyclerView of buttons when searching for avalanche regions by list.
 */
class AvalancheRegionListAdapter(
	private val myDataset: List<Pair<String, String>>,
	private val view: View,
	private val activity: Activity
) : RecyclerView.Adapter<AvalancheRegionListAdapter.MyViewHolder>() {
	class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
		val regionButton: Button = view.findViewById(R.id.button_region)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val regionButton = LayoutInflater.from(parent.context)
			.inflate(R.layout.button_avalanche_search_region, parent, false)

		return MyViewHolder(regionButton)
	}

	override fun getItemCount(): Int {
		return myDataset.size
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.regionButton.text = myDataset[position].first
		holder.regionButton.setOnClickListener {
			val area = myDataset[position].first
			val regionId = myDataset[position].second

			view.findViewById<RecyclerView>(
				R.id.avalanche_region_list_recycler_view
			).alpha = 0.5F
			view.findViewById<ProgressBar>(R.id.loading_progress_bar).visibility = View.VISIBLE

			gotoRegion(area, regionId)
		}
	}

	/**
	 * Help function for navigating to a region as a button with region name on it is clicked.
	 */
	private fun gotoRegion(area: String, regionId: String) {
		GlobalScope.launch {
			try {
				val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())

				val warnings = avalancheWarningByRegion(
					regionId,
					Language.NORWEGIAN,
					currentDate,
					currentDate
				)

				if (warnings.isEmpty()) showError("Ingen varsler funnet")

				val dangerLevel = warnings[0].DangerLevel
				val mainText = warnings[0].MainText
				val dangerInformation = DangerInformation(
					Disaster.AVALANCHE,
					regionId,
					dangerLevel,
					area,
					"Region",
					mainText
				)

				/* Race condition might make this result in crash, will be picked up by try-catch. */
				activity.runOnUiThread {
					val bundle = createDangerInformationBundle(dangerInformation)
					try {
						Navigation.findNavController(view).navigate(R.id.danger_fragment, bundle)
					} catch (e: java.lang.IllegalStateException) {
						println(e)
					}
				}
			} catch (e: Exception) {
				println(e)
				showError("Noe gikk galt")
			}
		}
	}

	/**
	 * Shows toast and returns to main search menu.
	 */
	private fun showError(toastMessage: String) {
		/**
		 * Race condition might make this crash, so lets handle it.
		 */
		try {
			activity.runOnUiThread {
				try {
					Navigation.findNavController(view).navigate(R.id.navigation_search)

					val duration = Toast.LENGTH_LONG
					val toast = Toast.makeText(activity, toastMessage, duration)
					toast.show()
				} catch (e: Exception) {
					println(e)
				}
			}
		} catch (e: Exception) {
			println(e)
		}
	}
}
