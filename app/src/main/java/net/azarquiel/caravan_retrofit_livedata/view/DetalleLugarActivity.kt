package net.azarquiel.caravan_retrofit_livedata.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import net.azarquiel.caravan_retrofit_livedata.databinding.ActivityDetalleLugarBinding
import net.azarquiel.caravan_retrofit_livedata.model.Foto
import net.azarquiel.caravan_retrofit_livedata.model.Lugar
import net.azarquiel.caravan_retrofit_livedata.model.Punto
import net.azarquiel.caravan_retrofit_livedata.viewmodel.MainViewModel

class DetalleLugarActivity : AppCompatActivity() {

    private lateinit var rbUser: RatingBar
    private lateinit var rbAvg: RatingBar
    private lateinit var lugar: Lugar
    private lateinit var binding: ActivityDetalleLugarBinding
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalleLugarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        lugar = intent.getSerializableExtra("lugar") as Lugar
        setTitle(lugar.titre)

        binding.contentdetallelugar.tvDetalleLugar.text = lugar.description_es

        setFotos()

        vm = ViewModelProvider(this).get(MainViewModel::class.java)


        // call API to get average rating
        rbAvg = binding.contentdetallelugar.ratingBarAvg
        vm.getAvgStarsByIdLugar(lugar.id).observe(this, Observer {
            it?.let {rbAvg.rating = it.toFloat()}
        })

        // call API to post user rating
        rbUser = binding.contentdetallelugar.ratingBarUser
        rbUser.setOnRatingBarChangeListener { rb, rating, fromUser ->
            val roundedRating = rating.toInt()
            val punto = Punto(1, lugar.id, roundedRating)
            vm.addPuntosByIdLugar(lugar.id, punto).observe(this, Observer {
                it?.let { rbUser.rating = it.puntos.toFloat()
                Log.d("sufiDev", "Upload rating: ${it.puntos}")}
            })

            // update average rating
            vm.getAvgStarsByIdLugar(lugar.id).observe(this, Observer {
                it?.let {rbAvg.rating = it.toFloat()
                    Log.d("sufiDev", "Average rating: $it")
                }
            })
        }
    }

    private fun setFotos() {
        val linearFotos = binding.contentdetallelugar.llhDetalleLugar
        lugar.photos.forEach { it: Foto ->
            val lpFoto = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
            lpFoto.setMargins(10)

            val ivFoto = ImageView(this)
            Picasso.get().load(it.link_large).into(ivFoto)

            linearFotos.addView(ivFoto, lpFoto)
        }
    }
}