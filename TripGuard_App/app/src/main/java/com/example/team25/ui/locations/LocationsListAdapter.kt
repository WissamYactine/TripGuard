package com.example.team25.ui.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.team25.R
import com.example.team25.persistence.SavedLocation
import kotlinx.android.synthetic.main.cardview_my_locations.view.*

class LocationsListAdapter(private val dataset : MutableList<SavedLocation>) : RecyclerView.Adapter<LocationsListAdapter.ViewHolder>() {

	class ViewHolder(var cardView : CardView) : RecyclerView.ViewHolder(cardView)
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsListAdapter.ViewHolder {
		val cardview = LayoutInflater.from(parent.context)
			.inflate(R.layout.cardview_my_locations, parent, false) as CardView
		return ViewHolder(cardview)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {

		// TO DO: FIX THIS LINE
		//holder.cardView.savedLocation.text = dataset[position].area
		holder.cardView.removeLocationButton.setOnClickListener {
			LocationsFragment.deleteSavedLocation(position)
		}

		var status = dataset[position].status
		val notif_icon= if (status) {
			R.drawable.ic_notif
		} else {
			R.drawable.ic_notif_off
		}

		val notifType = when (dataset[position].disaster) {
			0 -> "Snøskred"
			1 -> "Flom"
			2 -> "Jordskred"
			else -> "error"
		}

		val area = dataset[position].area

		holder.cardView.locationTitle.text = area
		holder.cardView.notifType.text = notifType
		holder.cardView.locationNotif.setBackgroundResource(notif_icon)
		holder.cardView.locationNotif.setOnClickListener {
			LocationsFragment.updateStatus(position)
			status = !status
			val updated_notif_icon= if (status) {
				R.drawable.ic_notif
			} else {
				R.drawable.ic_notif_off
			}
			holder.cardView.locationNotif.setBackgroundResource(updated_notif_icon)
		}
	}

	override fun getItemCount() = dataset.size
}
