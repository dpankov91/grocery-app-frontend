package com.example.grocery_finder_frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDataObserver()
    }

    private fun setupDataObserver() {

    }

    fun OnCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu1, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.img_logo -> Toast.makeText(this, "Show MapActivity", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }


}