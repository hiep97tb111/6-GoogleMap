package com.example.googlemapdemo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class ShowPolylineAct : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
    GoogleMap.OnPolygonClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_get_location_move)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add polyline to the map.
        // Polyline are useful to show a route or some other connection between points.
        val polylineOptionOne = PolylineOptions()
            .clickable(true)
            .add(
                LatLng(-35.016, 143.321),
                LatLng(-34.747, 145.592),
                LatLng(-34.364, 147.891),
                LatLng(-33.501, 150.217),
                LatLng(-32.306, 149.248),
                LatLng(-32.491, 147.309),
                LatLng(-35.016, 143.321))
            .color(Color.BLACK)
            .width(5F)
        val polylineOne = mMap.addPolyline(polylineOptionOne)
        polylineOne.tag = "A"
        stylePolyline(polylineOne)

        val polylineOptionTwo = PolylineOptions()
            .clickable(true)
            .add(
                LatLng(-29.501, 119.700),
                LatLng(-27.456, 119.672),
                LatLng(-25.971, 124.187),
                LatLng(-28.081, 126.555),
                LatLng(-28.848, 124.229),
                LatLng(-28.215, 123.938),
                LatLng(-29.501, 119.700))
            .color(Color.BLACK)
            .width(5F)
        val polylineTwo = mMap.addPolyline(polylineOptionTwo)
        polylineTwo.tag = "B"
        stylePolyline(polylineTwo)

        // Add polygons to indicate areas on the map.
        val polygonOptionOne = PolygonOptions()
            .clickable(true)
            .add(LatLng(-27.457, 153.040),
                LatLng(-33.852, 151.211),
                LatLng(-37.813, 144.962),
                LatLng(-34.928, 138.599))
            .strokeColor(Color.YELLOW)
            .fillColor(Color.GREEN)
        val polygonOne = mMap.addPolygon(polygonOptionOne)
        polygonOne.tag = "alpha"

        val polygonOptionTwo = PolygonOptions()
            .clickable(true)
            .add(LatLng(-31.673, 128.892),
                LatLng(-31.952, 115.857),
                LatLng(-17.785, 122.258),
                LatLng(-12.4258, 130.7932))
            .strokeColor(Color.YELLOW)
            .fillColor(Color.GREEN)
        val polygonTwo = mMap.addPolygon(polygonOptionTwo)
        polygonTwo.tag = "beta"

        // Set listeners for click events.
        mMap.setOnPolylineClickListener(this)
        mMap.setOnPolygonClickListener(this)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(-29.501, 119.700)))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(3f))
    }

    private fun stylePolyline(polyline: Polyline) {
        val tagPolyline = polyline.tag?.toString()
        when (tagPolyline) {
            "A" -> {
                polyline.startCap = RoundCap()
                polyline.endCap = RoundCap()
                polyline.width = 9f
                polyline.color = Color.RED
                polyline.jointType = JointType.ROUND
            }
            "B" -> {
                polyline.startCap = RoundCap()
                polyline.endCap = RoundCap()
                polyline.width = 5f
                polyline.color = Color.BLUE
                polyline.jointType = JointType.ROUND
            }
        }

    }



    private val PATTERN_GAP_LENGTH_PX = 10
    private val DOT: PatternItem = Dot()
    private val GAP: PatternItem = Gap(PATTERN_GAP_LENGTH_PX.toFloat())
    // Create a stroke pattern of a gap followed by a dot.
    private val PATTERN_POLYLINE_DOTTED = listOf(GAP, DOT)
    override fun onPolylineClick(polyline: Polyline) {
        if (polyline.pattern == null || !polyline.pattern!!.contains(DOT)) {
            polyline.pattern = PATTERN_POLYLINE_DOTTED
        } else {
            // The default pattern is a solid stroke.
            polyline.pattern = null
        }
        Toast.makeText(this, "Route type " + polyline.tag.toString(), Toast.LENGTH_SHORT).show()
    }


    override fun onPolygonClick(polygon: Polygon) {
        // Flip the values of the red, green, and blue components of the polygon's color.
        var color = polygon.strokeColor xor 0x00ffffff
        polygon.strokeColor = color
        color = polygon.fillColor xor 0x00ffffff
        polygon.fillColor = color
        Toast.makeText(this, "Area type ${polygon.tag?.toString()}", Toast.LENGTH_SHORT).show()
    }

}

