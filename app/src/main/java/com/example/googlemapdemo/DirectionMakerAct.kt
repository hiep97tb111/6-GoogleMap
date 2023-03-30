package com.example.googlemapdemo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.googlemapdemo.direction.model.DirectionModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import java.util.ArrayList


class DirectionMakerAct: AppCompatActivity(), OnMapReadyCallback {
    private  var directionModel: DirectionModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_direction_maker)

         directionModel = intent.getSerializableExtra("keyData") as? DirectionModel
         Log.e("Logger",getLatitude(
             directionModel!!.routes[0].legs[0].steps[1].geometry.coordinates[0].toString()).toString() + "/" + getLongitude(
                 directionModel!!.routes[0].legs[0].steps[1].geometry.coordinates[0].toString()) + "/" +directionModel!!.routes[0].legs[0].steps[1].geometry.coordinates[0].toString())

        findViewById<TextView>(R.id.tvClick).setOnClickListener {
            // Get the SupportMapFragment and request notification when the map is ready to be used.
            val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        // add Maker 2 point
        val departure = LatLng(21.039000, 105.774186)
        googleMap.addMarker(MarkerOptions().position(departure).title("Departure"))

        val destination = LatLng(21.034265, 105.789060)
        googleMap.addMarker(MarkerOptions().position(destination).title("Destination"))

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(21.039000, 105.774186)))
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10f))

        var listLatLng = ArrayList<LatLng>()
        // Direction between 2 point
        for(k in 0 until directionModel!!.routes[0].legs[0].steps.size){
            for (i in 0 until directionModel!!.routes[0].legs[0].steps[k].geometry.coordinates.size){
                listLatLng.add(LatLng(getLatitude(directionModel!!.routes[0].legs[0].steps[k].geometry.coordinates[i].toString()), getLongitude(directionModel!!.routes[0].legs[0].steps[k].geometry.coordinates[i].toString())))
            }
        }
        val polylineOptionOne = PolylineOptions()
            .clickable(true)
            .color(Color.BLACK)
            .width(5F)
            .addAll(listLatLng)
        googleMap.addPolyline(polylineOptionOne)

    }
}

fun getLatitude(str: String): Double{
    return str.split(",")[1].trim().split("]")[0].trim().toDouble()
}

fun getLongitude(str: String): Double{
    return str.split(",")[0].trim().split("[")[1].trim().toDouble()
}