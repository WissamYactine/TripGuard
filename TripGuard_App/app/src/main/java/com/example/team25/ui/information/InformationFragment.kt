package com.example.team25.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.team25.R

class InformationFragment : Fragment() {


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(R.layout.fragment_information, container, false)

		val infoFloodLandslideButton: Button = root.findViewById(R.id.button_info_fl)
		val infoAvalancheButton: Button = root.findViewById(R.id.button_info_a)
		val infoAboutButton: Button = root.findViewById(R.id.button_info_about)

		infoFloodLandslideButton.setOnClickListener {
			Navigation.findNavController(root).navigate(R.id.info_flood_landslide)
		}

		infoAvalancheButton.setOnClickListener {
			Navigation.findNavController(root).navigate(R.id.info_snow)
		}

		infoAboutButton.setOnClickListener {
			Navigation.findNavController(root).navigate(R.id.info_about)
		}

		return root
	}
}
