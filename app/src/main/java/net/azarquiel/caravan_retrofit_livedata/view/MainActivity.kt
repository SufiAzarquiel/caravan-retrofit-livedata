package net.azarquiel.caravan_retrofit_livedata.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.adapter.AdapterComunidades
import net.azarquiel.caravan_retrofit_livedata.model.Comunidad
import net.azarquiel.caravan_retrofit_livedata.model.Usuario
import net.azarquiel.caravan_retrofit_livedata.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var user: Usuario
    private lateinit var adapter: AdapterComunidades
    private lateinit var rvComunidades: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRV()
        user = intent.getSerializableExtra("user") as Usuario

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getDataComunidades().observe(this, Observer {
            it -> it?.let {adapter.setComunidades(it)}
        })
    }

    private fun initRV() {
        rvComunidades = findViewById(R.id.rvComunidades)
        adapter = AdapterComunidades(this, R.layout.row_comunidad)
        rvComunidades.adapter = adapter
        rvComunidades.layoutManager = LinearLayoutManager(this)
    }

    fun onClickComunidad(v: View?) {
        val comunidad = v!!.tag as Comunidad

        val intent = Intent(this, ProvinciaActivity::class.java)
        intent.putExtra("comunidad", comunidad)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}