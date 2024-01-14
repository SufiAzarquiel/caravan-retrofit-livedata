package net.azarquiel.caravan_retrofit_livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.model.Lugar

class AdapterLugares(val context: Context,
                     val layout: Int
) : RecyclerView.Adapter<AdapterLugares.ViewHolder>() {

    private var dataList: List<Lugar> = emptyList()

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

    internal fun setLugares(Lugares: List<Lugar>) {
        this.dataList = Lugares
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Lugar){
            var tvRowLugarDescripcion = itemView.findViewById(R.id.tvRowLugarDescripcion) as TextView
            var tvRowLugarTitulo = itemView.findViewById(R.id.tvRowLugarTitulo) as TextView

            tvRowLugarDescripcion.text = dataItem.description_es
            tvRowLugarTitulo.text = dataItem.titre

            itemView.tag = dataItem
        }
    }
}