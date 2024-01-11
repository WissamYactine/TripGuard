package com.example.team25.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team25.R
import com.example.team25.utils.placeToAvalancheRegionID


/**
 * Fragment for searching after avalanche region by list.
 */
class AvalancheRegionList : Fragment() {
	private lateinit var recyclerView: RecyclerView

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_avalanche_region_list, container, false)

		val viewManager = LinearLayoutManager(activity)

		/**
		 * Let avalancheRegions be list of pairs sorted by region name.
		 * We don't need to store this information in a viewmodel because its static data.
		 */
		val avalancheRegions = placeToAvalancheRegionID.toList().sortedWith(
			compareBy({it.first}, {it.first})
		)
		val viewAdapter = activity?.let { AvalancheRegionListAdapter(avalancheRegions, root, it) }

		recyclerView = root.findViewById<RecyclerView>(
			R.id.avalanche_region_list_recycler_view
		).apply {
			layoutManager = viewManager
			adapter = viewAdapter
		}

		return root
	}
}
