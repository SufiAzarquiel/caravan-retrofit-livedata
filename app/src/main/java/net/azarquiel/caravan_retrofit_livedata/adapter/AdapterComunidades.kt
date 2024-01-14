package net.azarquiel.caravan_retrofit_livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.model.Comunidad

class AdapterComunidades(val context: Context,
                         val layout: Int
) : RecyclerView.Adapter<AdapterComunidades.ViewHolder>() {

        private var dataList: List<Comunidad> = emptyList()

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

        internal fun setComunidades(Comunidades: List<Comunidad>) {
            this.dataList = Comunidades
            notifyDataSetChanged()
        }

        class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
            fun bind(dataItem: Comunidad){
                var tvRowComunidad = itemView.findViewById(R.id.tvRowComunidad) as TextView
                var ivRowCOmunidad = itemView.findViewById(R.id.ivRowComunidad) as ImageView

                tvRowComunidad.text = dataItem.nombre

                // image from drawable with id
                var id = context.resources.getIdentifier("ccaa"+dataItem.id, "drawable", context.packageName)
                ivRowCOmunidad.setImageResource(id)

                itemView.tag = dataItem
            }
        }
    }