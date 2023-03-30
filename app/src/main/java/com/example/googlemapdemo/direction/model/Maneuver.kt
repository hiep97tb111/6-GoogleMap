package com.example.googlemapdemo.direction.model

import java.io.Serializable

data class Maneuver (
    val type: String,
    val instruction: String,
    val bearingAfter: Long,
    val bearingBefore: Long,
    val location: List<Double>,
    val modifier: String? = null
): Serializable