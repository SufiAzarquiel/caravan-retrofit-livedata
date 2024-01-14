package net.azarquiel.caravan_retrofit_livedata.view

import android.os.Bundle
import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import net.azarquiel.caravan_retrofit_livedata.R
import net.azarquiel.caravan_retrofit_livedata.adapter.AdapterFotos
import net.azarquiel.caravan_retrofit_livedata.databinding.ActivityDetalleLugarBinding
import net.azarquiel.caravan_retrofit_livedata.model.Foto
import net.azarquiel.caravan_retrofit_livedata.model.Lugar

class DetalleLugarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleLugarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalleLugarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        var lugar = intent.getSerializableExtra("lugar") as Lugar
        setTitle(lugar.titre)
        var adapter = AdapterFotos(this, R.layout.row_foto)
        binding.contentdetallelugar.rvDetalleLugar.adapter = adapter
        binding.contentdetallelugar.rvDetalleLugar.layoutManager = LinearLayoutManager(this)

        binding.contentdetallelugar.tvDetalleLugar.text = lugar.description_es

        var fotoList: List<Foto> = lugar.photos
        adapter.setFotos(fotoList)
    }
}