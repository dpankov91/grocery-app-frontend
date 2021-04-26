package com.example.grocery_finder_frontend


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       gotomap()
    }
    private fun gotomap(){
        var intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }


}