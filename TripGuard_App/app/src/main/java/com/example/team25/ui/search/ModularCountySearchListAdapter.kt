package com.example.team25.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.team25.R
import com.example.team25.utils.Disaster

/**
 * Adapter for RecyclerView of buttons when searching for slide/flood countys by list.
 * Ideally, this doesnt take `disaster` and `root` upon creation. This is only needed to
 * setOnClickListener for the buttons, but it would be prettier if it was done from a fragment.
 */

class ModularCountySearchListAdapter(private val myDataset: List<String>, private val root: View,
									 private val disaster: Disaster) :
	RecyclerView.Adapter<ModularCountySearchListAdapter.MyViewHolder>() {
	class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
		val countyButton: Button = view.findViewById(R.id.button_county)

	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val countyButton = LayoutInflater.from(parent.context)
			.inflate(R.layout.button_search_county, parent, false)

		return MyViewHolder(countyButton)
	}
	override fun getItemCount(): Int {
		return myDataset.size
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.countyButton.text = myDataset[position]
		holder.countyButton.setOnClickListener {
			val bundle = bundleOf(
				"disasterType" to disaster,
				"county" to myDataset[position]
			)
			Navigation.findNavController(root).navigate(R.id.municipality_search_fragment, bundle)
		}
	}
}