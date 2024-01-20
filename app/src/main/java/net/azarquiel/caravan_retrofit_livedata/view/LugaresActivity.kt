package net.azarquiel.caravan_retrofit_livedata.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.adapter.AdapterLugares
import net.azarquiel.caravan_retrofit_livedata.model.Comunidad
import net.azarquiel.caravan_retrofit_livedata.model.Municipio
import net.azarquiel.caravan_retrofit_livedata.model.Lugar
import net.azarquiel.caravan_retrofit_livedata.model.Usuario
import net.azarquiel.caravan_retrofit_livedata.viewmodel.MainViewModel

class LugaresActivity : AppCompatActivity() {
    private lateinit var adapter: AdapterLugares
    private lateinit var rvLugares: RecyclerView
    private lateinit var municipio: Municipio
    private lateinit var user: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugares)
        
        municipio = intent.getSerializableExtra("municipio") as Municipio

        initRV()
        user = intent.getSerializableExtra("user") as Usuario

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLugarByLatitudLongitud(municipio.latitud, municipio.longitud).observe(this, Observer {
                it -> it?.let {adapter.setLugares(it)}
        })
    }

    private fun initRV() {
        rvLugares = findViewById(R.id.rvLugares)
        adapter = AdapterLugares(this, R.layout.row_lugar)
        rvLugares.adapter = adapter
        rvLugares.layoutManager = LinearLayoutManager(this)
    }

    fun onClickLugar(v: View?) {
        val lugar = v!!.tag as Lugar

        val intent = Intent(this, DetalleLugarActivity::class.java)
        intent.putExtra("lugar", lugar)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}