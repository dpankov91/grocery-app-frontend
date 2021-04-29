package com.example.grocery_finder_frontend


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.grocery_finder_frontend.model.Shop
import com.example.grocery_finder_frontend.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var shops: List <Shop> = emptyList()
    var isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllShopsFromApi()
        Log.d("RSP", "list state" + " " + shops.toString()
        )
        val asStrings = shops.map { p -> "${p.id}, ${p.name}" }

        val adapter: ListAdapter = ArrayAdapter(
                this, android.R.layout.simple_list_item_1, asStrings.toTypedArray()
        )
        shopList.adapter = adapter
        shopList.onItemClickListener = AdapterView.OnItemClickListener{_, _, pos, _ -> onListItemClick(pos)}
    }

    private fun getAllShopsFromApi(){
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        var viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllShops()
        viewModel.allShopsResponse.observe(this, Observer { response ->
            Log.d("RSP", response.toString())
            shops = response
            isFinished = true
            Log.d("RSP", "shops list" + " " + shops.toString())
        })
    }

    private fun onListItemClick(pos: Int) {
        Log.d("abc", "clicked")
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
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
}