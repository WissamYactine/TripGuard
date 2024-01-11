package com.example.team25.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team25.R
import com.example.team25.utils.Disaster
import com.example.team25.utils.countyIDToPlace
import java.lang.Exception

/**
 * A search fragment that can be used for both flood search and landslide search. Name of fragment
 * should maybe be changed.
 */
class ModularSearchFragment : Fragment() {

	private lateinit var recyclerView: RecyclerView

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_modular_search, container, false)
		val textView: TextView = root.findViewById(R.id.texto)

		val viewManager = LinearLayoutManager(activity)
		val counties = countyIDToPlace.values.sorted()

		val viewAdapter = ModularCountySearchListAdapter(
			counties,
			root,
			arguments?.get("disasterType") as Disaster
		)

		recyclerView = root.findViewById<RecyclerView>(R.id.county_list_recycler_view).apply {
			layoutManager = viewManager
			adapter = viewAdapter
		}

		when (arguments?.get("disasterType")) {
			Disaster.FLOOD -> textView.text = getString(R.string.search_fragment_flood)
			Disaster.LANDSLIDE -> textView.text = getString(R.string.search_fragment_landslide)
			else -> {
				showError("Noe gikk galt", root)
			}
		}

		return root
	}

	/**
	 * Shows toast and returns to main search menu.
	 */
	private fun showError(toastMessage: String, root: View) {
		try {
			requireActivity().runOnUiThread {
				try {
					val duration = Toast.LENGTH_LONG
					val toast = Toast.makeText(activity, toastMessage, duration)
					toast.show()

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
