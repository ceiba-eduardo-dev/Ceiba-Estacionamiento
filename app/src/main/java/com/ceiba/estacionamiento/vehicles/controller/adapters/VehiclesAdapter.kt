package com.ceiba.estacionamiento.vehicles.controller.adapters;

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.ceiba.estacionamiento.R
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.car
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.vehicleSerializable
import com.ceiba.estacionamiento.vehicles.controller.views.VehicleExitActivity
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity

class VehiclesAdapter :
    androidx.recyclerview.widget.RecyclerView.Adapter<VehiclesAdapter.VehicleHolder>() {

    private var vehicles: List<VehicleEntity> = listOf()
    private var context: Context? = null

    /**
     * Este metodo asocia el layout item_recycler_vehicles.xml con el recyclerView (infla el layout)
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_vehicles, parent, false)
        context = parent.context
        return VehicleHolder(view)
    }

    /**
     * Cantidad de elementos que contiene la lista
     */
    override fun getItemCount(): Int {
        return vehicles.size
    }

    /**
     * Notifica al adapter el cambio en los items a mostrar
     */
    fun setItemsList(vehiclesList: List<VehicleEntity>) {
        vehicles = vehiclesList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VehicleHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.vehicleTitle.text = vehicle.plate.toUpperCase()
        if (vehicles[position].type == car) {
            holder.vehicleImage.setImageResource(R.drawable.ic_car)
        } else {
            holder.vehicleImage.setImageResource(R.drawable.ic_bike)
        }
        holder.itemContainer.setOnClickListener {
            var intent = Intent(context, VehicleExitActivity::class.java)
            intent.putExtra(vehicleSerializable, vehicle).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)
        }
    }

    class VehicleHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val vehicleTitle: TextView = itemView.findViewById(R.id.vehicleTitle)
        val vehicleImage: ImageView = itemView.findViewById(R.id.vehicleImage)
        val itemContainer: CardView = itemView.findViewById(R.id.item_container)
    }

}