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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.grocery_finder_frontend.model.Shop
import com.example.grocery_finder_frontend.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    var shopid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //For the main activity, the first thing the user sees is the list of shops available from the backend.
        //The shops are retrieved through an api link, which has our published backend.

        getAllShopsFromApi(Observer{ response ->

            Log.d("RSP", "list state:" + " " + response.toString())
            //We're waiting for response to come through.
            val shops = response as List<Shop>

            val asStrings = shops.map { p -> "${p.id}. ${p.name} : ${p.address}" }
            //We're returning a list of shops using map, which is put into a value.
            val adapter: ListAdapter = ArrayAdapter(
                //We're using ArrayAdapter to apply the elements of our list into the listview.
                    //To display elements in a listView, you need adapters.
                    this, android.R.layout.simple_list_item_1, asStrings.toTypedArray()
                )
            //We set the data behind the listView here, seeing it to what data is to be represented with the equals sign.
                shopList.adapter = adapter
            shopList.onItemClickListener = AdapterView.OnItemClickListener{_, _, pos, _ -> onListItemClick(pos)}})
    }

    private fun getAllShopsFromApi(x: Observer<List<Shop>>){
        //We're using this function as the first one upon opening this activity.
        //Observer's used to get callback from LiveData
        val repository = Repository() //Initializing the repository.
        val viewModelFactory = MainViewModelFactory(repository) // Initializing  the viewModelFactory
        var viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java) // we provide the viewModel.
        viewModel.getAllShops()
        viewModel.allShopsResponse.observe(this, x)//Getting the resposne, getting the data.
    }

    private fun onListItemClick(pos: Int) {
        val intent = Intent(this, DetailActivity::class.java)//Initializes where we want to go when this function's invoked.
        val id = pos+1
        intent.putExtra("shopid", id)//Adds the id of the selected element from the list into intent.
        Log.d("pos", id.toString())
        startActivity(intent)//Starts the activity.
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
                setResult(5)
                startActivity(intent)}
        }
        return super.onOptionsItemSelected(item)
    }
}