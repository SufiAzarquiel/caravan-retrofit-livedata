package net.azarquiel.caravan_retrofit_livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.model.Provincia

class AdapterProvincias(val context: Context,
                         val layout: Int
) : RecyclerView.Adapter<AdapterProvincias.ViewHolder>() {

    private var dataList: List<Provincia> = emptyList()

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

    internal fun setProvincias(Provincias: List<Provincia>) {
        this.dataList = Provincias
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Provincia){
            var tvRowProvincia = itemView.findViewById(R.id.tvRowProvincia) as TextView

            tvRowProvincia.text = dataItem.nombre

            itemView.tag = dataItem
        }
    }
}