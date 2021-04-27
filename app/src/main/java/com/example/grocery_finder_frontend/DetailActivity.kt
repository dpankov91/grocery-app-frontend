package com.example.grocery_finder_frontend

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val LOG = "abc"
    private var lat = 0.0
    private var long = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        when (id){
            R.id.map -> {
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)}
        }
        return super.onOptionsItemSelected(item)
    }

    fun onClickGoBack(view: View) {
        finish()
    }

    // fun onClickGoToMap(view: View) {}

    fun onClickOpenWebsite(view: View) {
        val url = "http://www.netto.dk"
        Log.d(LOG, url)
        val browserIntent = Intent(Intent.ACTION_VIEW)
        browserIntent.data = Uri.parse(url)
        startActivity(browserIntent)
    }

    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    @SuppressLint("MissingPermission")
    fun onClickGoToMap(view: View) {
        requestPermissions()
        if(!isPermissionGiven()){
            Toast.makeText(this, "Permission to use GPS is denied", Toast.LENGTH_LONG).show()
            return
        }
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if(location != null){
            tvCoordinates.text = "${location.latitude}, ${location.longitude}"
            lat = location.latitude
            long = location.longitude
            Log.d(LOG, lat.toString() + " " + long.toString())
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("lat",lat)
            intent.putExtra("long", long)
            startActivity(intent)
        } else{
            Toast.makeText(this, "The Location is unfortunately null", Toast.LENGTH_LONG).show()
        }
    }

    private fun isPermissionGiven(): Boolean{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return permissions.all {f -> checkSelfPermission(f) == PackageManager.PERMISSION_GRANTED}
        }
        return true
    }
    private fun requestPermissions(){
        if(!isPermissionGiven()){
            Log.d("TAG", "Permission denied to use GPS - requesting it")
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(permissions, 1)
            } else
                Log.d("TAG", "Permission to use GPS Granted")
        }
    }
}