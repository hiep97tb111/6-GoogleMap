package com.example.googlemapdemo.direction.model

import java.io.Serializable

data class Step (
    val intersections: List<Intersection>,
    val maneuver: Maneuver,
    val name: String,
    val duration: Double,
    val distance: Double,
    val drivingSide: String,
    val weight: Double,
    val mode: String,
    val geometry: Geometry,
    val ref: String? = null
): Serializable