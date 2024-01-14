package net.azarquiel.caravan_retrofit_livedata.api

import kotlinx.coroutines.Deferred
import net.azarquiel.caravan_retrofit_livedata.model.Respuesta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CaravanService {
    // Get from http://www.ies-azarquiel.es/paco/apicaravan/comunidad
    @GET("comunidad")
    fun getDataComunidades(): Deferred<Response<Respuesta>>

    // Get from http://www.ies-azarquiel.es/paco/apicaravan/comunidad/{idprovincia}/provincia
    @GET("comunidad/{idcomunidad}/provincia")
    fun getDataProvinciaByComunidad(@Path("idcomunidad") idcomunidad: Int): Deferred<Response<Respuesta>>

    // Get from http://www.ies-azarquiel.es/paco/apicaravan/provincia/{idmunicipio}/municipio
    @GET("provincia/{idprovincia}/municipio")
    fun getDataMunicipioByProvincia(@Path("idprovincia") idprovincia: Int): Deferred<Response<Respuesta>>

    // Get with parameters from url?latitud={latitud}&longitud={longitud}
    @GET("lugar")
    fun getLugarByLatitudLongitud(
        @Query("latitud") latitud: String,
        @Query("longitud") longitud: String): Deferred<Response<Respuesta>>
}