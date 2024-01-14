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
import net.azarquiel.caravan_retrofit_livedata.adapter.AdapterProvincias
import net.azarquiel.caravan_retrofit_livedata.model.Comunidad
import net.azarquiel.caravan_retrofit_livedata.model.Provincia
import net.azarquiel.caravan_retrofit_livedata.viewmodel.MainViewModel

class ProvinciaActivity : AppCompatActivity() {
    private lateinit var adapter: AdapterProvincias
    private lateinit var rvProvincias: RecyclerView
    private lateinit var comunidad: Comunidad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provincia)

        comunidad = intent.getSerializableExtra("comunidad") as Comunidad

        initRV()

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getDataProvinciaByComunidad(comunidad.id).observe(this, Observer {
                it -> it?.let {adapter.setProvincias(it)}
        })
    }

    private fun initRV() {
        rvProvincias = findViewById(R.id.rvProvincias)
        adapter = AdapterProvincias(this, R.layout.row_provincia)
        rvProvincias.adapter = adapter
        rvProvincias.layoutManager = LinearLayoutManager(this)
    }

    fun onClickProvincia(v: View?) {
        val provincia = v!!.tag as Provincia

        val intent = Intent(this, MunicipiosActivity::class.java)
        intent.putExtra("provincia", provincia)
        startActivity(intent)
    }
}