package net.azarquiel.caravan_retrofit_livedata.model

import java.io.Serializable

data class Comunidad (
    var id: Int,
    var nombre: String
): Serializable

data class Provincia (
    var id: Int,
    var comunidad: Int,
    var nombre: String
): Serializable

data class Municipio (
    var id: Int,
    var nombre: String,
    var provincia: Int,
    var latitud: String,
    var longitud: String
): Serializable

data class Lugar (
    var id: Int,
    var latitude: String,
    var longitude: String,
    var titre: String,
    var description_es: String,
    var photos: List<Foto>
): Serializable

data class Foto (
    var id:Int,
    var link_large: String,
    var link_thumb: String
): Serializable

data class Respuesta (
    val comunidades: List<Comunidad>,
    var provincias: List<Provincia>,
    var municipios: List<Municipio>,
    var lieux: List<Lugar>,
    var avg: String,
    var punto: Punto
)

data class Punto (
    var usuario: Int,
    var lugar: Int,
    var puntos: Int
)