package com.example.googlemapdemo.direction.api

import com.example.googlemapdemo.direction.model.DirectionModel
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("driving/105.774186%2C21.039%3B105.78906%2C21.034265?alternatives=true&geometries=geojson&language=en&overview=simplified&steps=true&access_token=pk.eyJ1Ijoic21lYjk3MTYiLCJhIjoiY2xmcmdxajU1MDN6NTN2bmxnc3RrdDhjciJ9.JXjAEZJ-qQLk7NOmWYSFfQ")
    fun getData(): Call<DirectionModel>
}