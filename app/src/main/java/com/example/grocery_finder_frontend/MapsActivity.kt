package com.example.grocery_finder_frontend

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var marker: Marker
    var mylocation = LatLng(0.0, 0.0);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        requestPermissions()
        startListening()
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        marker = mMap.addMarker(MarkerOptions().position(mylocation).title("Me").icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)))

    }
    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)

    private fun requestPermissions() {
        if (!isPermissionGiven()) {
            Log.d("TAG", "permission denied to USE GPS - requesting it")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermissions(permissions, 1)
        } else
            Log.d("TAG", "permission to USE GPS granted!")
    }

    private fun isPermissionGiven(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return permissions.all { p -> checkSelfPermission(p) == PackageManager.PERMISSION_GRANTED }
        }
        return true
    }

    var myLocationListener: LocationListener? = null


    private fun startListening() {
        if (!isPermissionGiven())
            return

        if (myLocationListener == null)
            myLocationListener = object : LocationListener {
                var count: Int = 0

                override fun onLocationChanged(location: Location) {
                    count++
                    Log.d("TAG", "Location changed")
                    mylocation = LatLng(location.latitude, location.longitude)
                    Log.d("TAG", "" + mylocation)
                    marker.position = mylocation
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation))
                    mMap.setMinZoomPreference(12F)
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                }
            }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0,
                0.0F,
                myLocationListener!!)

    }

    private fun stopListening() {

        if (myLocationListener == null) return

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.removeUpdates(myLocationListener!!)
    }

    override fun onStop() {
        stopListening()
        Log.d("TAG","Stop listening")
        super.onStop()
    }

    fun onClickBack(view: View) {
        stopListening()
        finish()
    }
    fun onClickHome(view: View) {
        stopListening()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}