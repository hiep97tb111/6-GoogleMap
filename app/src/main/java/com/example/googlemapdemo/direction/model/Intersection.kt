package com.example.googlemapdemo.direction.model

import java.io.Serializable

data class Intersection (
    val entry: List<Boolean>,
    val bearings: List<Long>,
    val duration: Double? = null,

    val mapboxStreetsV8: MapboxStreetsV8? = null,
    val isUrban: Boolean? = null,
    val adminIndex: Long,
    val out: Long? = null,
    val weight: Double? = null,
    val geometryIndex: Long,
    val location: List<Double>,
    val intersectionIn: Long? = null,
    val turnWeight: Double? = null,
    val turnDuration: Double? = null,
    val trafficSignal: Boolean? = null,
    val lanes: List<Lane>? = null
): Serializable