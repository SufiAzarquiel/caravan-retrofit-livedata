package net.azarquiel.caravan_retrofit_livedata.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.model.Comunidad
import net.azarquiel.caravan_retrofit_livedata.model.Foto

class AdapterFotos(val context: Context,
                   val layout: Int
) : RecyclerView.Adapter<AdapterFotos.ViewHolder>() {

    private var dataList: List<Foto> = emptyList()

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

    internal fun setFotos(fotos: List<Foto>) {
        this.dataList = fotos
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Foto){

            // Load image from url
            var ivRowFoto = itemView.findViewById(R.id.ivRowFoto) as ImageView
            Picasso.get().load(dataItem.link_thumb).into(ivRowFoto)

            itemView.tag = dataItem
        }
    }
}