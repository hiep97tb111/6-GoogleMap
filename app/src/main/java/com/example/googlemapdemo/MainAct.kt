package com.example.googlemapdemo

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.googlemapdemo.direction.api.RetrofitClient
import com.example.googlemapdemo.direction.model.DirectionModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainAct : AppCompatActivity() {
    private val myPermissionRequest: Int = 101
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private lateinit var directionModel: DirectionModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLocationUser()

        getDataDirectionTwoPoint()

        findViewById<TextView>(R.id.tvMapWithMaker).setOnClickListener {
            val intent = Intent(this, MapWithMakerAct::class.java)
            intent.putExtra("keyLat", lat)
            intent.putExtra("keyLon", lon)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.tvShowPolyline).setOnClickListener {
            val intent = Intent(this, ShowPolylineAct::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.tvDirectionMaker).setOnClickListener {
            val intent = Intent(this, DirectionMakerAct::class.java)
            intent.putExtra("keyData", directionModel as Serializable)
            startActivity(intent)
        }
    }

    private fun getDataDirectionTwoPoint() {
        // get list coordinates in routes -> steps -> geometry -> coordinates
        RetrofitClient.apiService.getData().enqueue(object : Callback<DirectionModel> {
            override fun onResponse(
                call: Call<DirectionModel>,
                response: Response<DirectionModel>
            ) {
                Log.e("Logger", response.body()!!.code)
                directionModel = response.body()!!
            }

            override fun onFailure(call: Call<DirectionModel>, t: Throwable) {

            }

        })
    }

    private fun getLocationUser() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Check Permission
        if (ContextCompat.checkSelfPermission(this , android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
                myPermissionRequest)
        }

        // Get Location
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Log.e("Logger", "Lat: ${it.latitude}, Lon: ${it.longitude}")
            lat = it.latitude
            lon = it.longitude
        }
    }


}