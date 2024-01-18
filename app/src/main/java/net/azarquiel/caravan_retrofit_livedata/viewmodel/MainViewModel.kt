package net.azarquiel.caravan_retrofit_livedata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.caravan_retrofit_livedata.api.MainRepository
import net.azarquiel.caravan_retrofit_livedata.model.Comunidad
import net.azarquiel.caravan_retrofit_livedata.model.Lugar
import net.azarquiel.caravan_retrofit_livedata.model.Municipio
import net.azarquiel.caravan_retrofit_livedata.model.Provincia
import net.azarquiel.caravan_retrofit_livedata.model.Punto

class MainViewModel: ViewModel() {
    private var repository: MainRepository = MainRepository()

    fun getDataComunidades(): MutableLiveData<List<Comunidad>> {
        val comunidades = MutableLiveData<List<Comunidad>>()
        GlobalScope.launch(Main) {
            comunidades.value = repository.getDataComunidades()
        }
        return comunidades
    }

    fun getDataProvinciaByComunidad(idcomunidad:Int): MutableLiveData<List<Provincia>> {
        val provincias = MutableLiveData<List<Provincia>>()
        GlobalScope.launch(Main) {
            provincias.value = repository.getDataProvinciaByComunidad(idcomunidad)
        }
        return provincias
    }

    fun getDataMunicipioByProvincia(idprovincia: Int): MutableLiveData<List<Municipio>> {
        val municipios = MutableLiveData<List<Municipio>>()
        GlobalScope.launch(Main) {
            municipios.value = repository.getDataMunicipioByProvincia(idprovincia)
        }
        return municipios
    }

    fun getLugarByLatitudLongitud(latitud: String, longitud: String): MutableLiveData<List<Lugar>> {
        val lugares = MutableLiveData<List<Lugar>>()
        GlobalScope.launch(Main) {
            lugares.value = repository.getLugarByLatitudLongitud(latitud, longitud)
        }
        return lugares
    }

    fun getAvgStarsByIdLugar(idlugar: Int): MutableLiveData<String> {
        val avg = MutableLiveData<String>()
        GlobalScope.launch(Main) {
            avg.value = repository.getAvgStarsByIdLugar(idlugar)
        }
        return avg
    }

    fun addPuntosByIdLugar(idlugar: Int, punto: Punto): MutableLiveData<Punto> {
        val puntoLocal = MutableLiveData<Punto>()
        GlobalScope.launch(Main) {
            puntoLocal.value = repository.addPuntosByIdLugar(idlugar, punto)
        }
        return puntoLocal
    }
}