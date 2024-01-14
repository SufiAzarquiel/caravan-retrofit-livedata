package net.azarquiel.caravan_retrofit_livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.model.Municipio

class AdapterMunicipios(val context: Context,
val layout: Int
) : RecyclerView.Adapter<AdapterMunicipios.ViewHolder>() {

    private var dataList: List<Municipio> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setMunicipios(Municipios: List<Municipio>) {
        this.dataList = Municipios
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Municipio){
            var tvRowMunicipio = itemView.findViewById(R.id.tvRowMunicipio) as TextView

            tvRowMunicipio.text = dataItem.nombre

            itemView.tag = dataItem
        }
    }
}