package net.azarquiel.caravan_retrofit_livedata.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.adapter.AdapterMunicipios
import net.azarquiel.caravan_retrofit_livedata.databinding.ActivityMunicipiosBinding
import net.azarquiel.caravan_retrofit_livedata.model.Municipio
import net.azarquiel.caravan_retrofit_livedata.model.Provincia
import net.azarquiel.caravan_retrofit_livedata.model.Usuario
import net.azarquiel.caravan_retrofit_livedata.viewmodel.MainViewModel

class MunicipiosActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var municipios: List<Municipio>
    private lateinit var adapterMunicipios: AdapterMunicipios
    private lateinit var binding: ActivityMunicipiosBinding
    private lateinit var user: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMunicipiosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        var provincia = intent.getSerializableExtra("provincia") as Provincia

        initRV()
        user = intent.getSerializableExtra("user") as Usuario

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getDataMunicipioByProvincia(provincia.id).observe(this, Observer {
                it -> it?.let {
                    adapterMunicipios.setMunicipios(it)
                    municipios = it
                }
        })
    }

    private fun initRV() {
        adapterMunicipios = AdapterMunicipios(this, R.layout.row_municipio)
        binding.contentmunicipios.rvMunicipios.adapter = adapterMunicipios
        binding.contentmunicipios.rvMunicipios.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_municipios, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ************* <Filtro> ************
    override fun onQueryTextChange(query: String): Boolean {
        val original = ArrayList<Municipio>(municipios)
        adapterMunicipios.setMunicipios(original.filter { municipio -> municipio.nombre.contains(query,true) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
    // ************* </Filtro> ************

    fun onClickMunicipio(v: View?) {
        val municipio = v!!.tag as Municipio

        val intent = Intent(this, LugaresActivity::class.java)
        intent.putExtra("municipio", municipio)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}