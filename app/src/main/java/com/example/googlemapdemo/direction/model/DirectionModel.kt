package com.example.googlemapdemo.direction.model

import java.io.Serializable


data class DirectionModel(val routes: ArrayList<Route>, val waypoint: ArrayList<Waypoint>, val code: String, val uuid: String): Serializable