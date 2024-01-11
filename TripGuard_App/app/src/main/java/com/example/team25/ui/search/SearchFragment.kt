package com.example.team25.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.team25.R
import com.example.team25.utils.Disaster

/**
 * Fragment so that one could choose to search one of the following: flom, snøskred, jordskred.
 */
class SearchFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_search, container, false)

		val avalancheButton: Button = root.findViewById(R.id.button_avalanche)
		val floodButton: Button = root.findViewById(R.id.button_flood)
		val landslideButton: Button = root.findViewById(R.id.button_landslide)

		avalancheButton.setOnClickListener {
			Navigation.findNavController(root).navigate(R.id.avalanche_search_fragment)
		}

		floodButton.setOnClickListener {
			val bundle = bundleOf("disasterType" to Disaster.FLOOD)
			Navigation.findNavController(root).navigate(R.id.modular_search_fragment, bundle)
		}

		landslideButton.setOnClickListener {
			val bundle = bundleOf("disasterType" to Disaster.LANDSLIDE)
			Navigation.findNavController(root).navigate(R.id.modular_search_fragment, bundle)
		}

		return root
	}
}