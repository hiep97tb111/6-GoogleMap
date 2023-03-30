package com.example.googlemapdemo.direction.model

import java.io.Serializable

data class Route (val weightName: String,
                  val weight: Double,
                  val duration: Double,
                  val distance: Double,
                  val legs: List<Leg>,
                  val geometry: Geometry
                  ): Serializable