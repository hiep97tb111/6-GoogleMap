package com.example.googlemapdemo

import android.annotation.SuppressLint
import android.os.Bundle
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
    override fun onMapReady(googleMap: GoogleMap) {
        val locationLive = LatLng(lat, lon)
        googleMap.addMarker(
            MarkerOptions()
                .position(locationLive)
                .title("Live")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationLive))
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
    }

}

