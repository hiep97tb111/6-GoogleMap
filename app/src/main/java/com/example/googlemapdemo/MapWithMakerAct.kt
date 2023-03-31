package com.example.googlemapdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapWithMakerAct: AppCompatActivity(), OnMapReadyCallback {
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private lateinit var mMap: GoogleMap

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_map_with_maker)

        lat = intent.getDoubleExtra("keyLat",20.453951)
        lon = intent.getDoubleExtra("keyLon", 106.347054)

        //  Then use getMapAsync() to register for the map callback
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    // Implement the OnMapReadyCallback interface and override the onMapReady(): Handle Map
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = true
        mMap.setOnMyLocationClickListener {
            Log.e("Logger", "Lat: ${it.latitude}, Lon: ${it.longitude}")
        }

        val locationLive = LatLng(lat, lon)
        mMap.addMarker(
            MarkerOptions()
                .position(locationLive)
                .title("Live")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationLive))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))

        mMap.setOnMapClickListener {
            Log.d("Logger","Map clicked [" + it.latitude + " / " + it.longitude + "]");
            mMap.addMarker(
                MarkerOptions()
                    .position(it)
                    .title("Live")
            )
        }

    }

}

