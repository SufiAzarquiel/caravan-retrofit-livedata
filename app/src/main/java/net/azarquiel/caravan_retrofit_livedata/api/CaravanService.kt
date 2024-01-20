package net.azarquiel.caravan_retrofit_livedata.api

import kotlinx.coroutines.Deferred
import net.azarquiel.caravan_retrofit_livedata.model.Punto
import net.azarquiel.caravan_retrofit_livedata.model.Respuesta
import net.azarquiel.caravan_retrofit_livedata.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    // Get from http://www.ies-azarquiel.es/paco/apicaravan/lugar/{idlugar}/avgpuntos
    @GET("lugar/{idlugar}/avgpuntos")
    fun getAvgStarsByIdLugar(@Path("idlugar") idlugar: Int): Deferred<Response<Respuesta>>

    // Post to /lugar/{idlugar}/puntos with json -> object:
    //    {
    //      "usuario": "1",
    //      "lugar": "613",
    //      "puntos": "5"
    //    }
    @POST("lugar/{idlugar}/puntos")
    fun addPuntosByIdLugar(@Path("idlugar") idlugar: Int, @Body puntos: Punto): Deferred<Response<Respuesta>>

    // Post to /usuario with json -> object:
    //    {
    //      "nick": "sufi",
    //      "pass": "1234"
    //    }
    @POST("usuario")
    fun addUser(@Body usuario: Usuario): Deferred<Response<Respuesta>>
}