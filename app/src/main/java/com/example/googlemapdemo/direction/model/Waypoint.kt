package com.example.googlemapdemo.direction.model

import java.io.Serializable

data class Waypoint (
    val distance: Double,
    val name: String,
    val location: List<Double>
): Serializable