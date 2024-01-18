package net.azarquiel.caravan_retrofit_livedata.api

import net.azarquiel.caravan_retrofit_livedata.model.Comunidad
import net.azarquiel.caravan_retrofit_livedata.model.Lugar
import net.azarquiel.caravan_retrofit_livedata.model.Municipio
import net.azarquiel.caravan_retrofit_livedata.model.Provincia
import net.azarquiel.caravan_retrofit_livedata.model.Punto

class MainRepository() {
    val service = WebAccess.caravanService

    suspend fun getDataComunidades(): List<Comunidad> {
        val webResponse = service.getDataComunidades().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.comunidades
        }
        return emptyList()
    }

    suspend fun getDataProvinciaByComunidad(idcomunidad: Int): List<Provincia> {
        val webResponse = service.getDataProvinciaByComunidad(idcomunidad).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.provincias
        }
        return emptyList()
    }

    suspend fun getDataMunicipioByProvincia(idprovincia: Int): List<Municipio> {
        val webResponse = service.getDataMunicipioByProvincia(idprovincia).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.municipios
        }
        return emptyList()
    }

    suspend fun getLugarByLatitudLongitud(latitud: String, longitud: String): List<Lugar> {
        val webResponse = service.getLugarByLatitudLongitud(latitud, longitud).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.lieux
        }
        return emptyList()
    }

    suspend fun getAvgStarsByIdLugar(idlugar: Int): String {
        val webResponse = service.getAvgStarsByIdLugar(idlugar).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.avg
        }
        return "0"
    }

    suspend fun addPuntosByIdLugar(idlugar: Int, punto: Punto): Punto? {
        val webResponse = service.addPuntosByIdLugar(idlugar, punto).await()
        var puntoResponse: Punto? = null
        if (webResponse.isSuccessful) {
            puntoResponse = webResponse.body()!!.punto
        }
        return puntoResponse
    }
}
