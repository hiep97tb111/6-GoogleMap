package com.example.googlemapdemo.direction.model

import java.io.Serializable

data class Leg (
    val viaWaypoints: List<Any?>,
    val admins: List<Admin>,
    val weight: Double,
    val duration: Double,
    val steps: List<Step>,
    val distance: Double,
    val summary: String
): Serializable