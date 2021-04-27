package com.example.grocery_finder_frontend


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shops = arrayOf("Bilka", "Fakta", "Rema 1000", "Lidl", "Netto")

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                this, android.R.layout.simple_list_item_1, shops
        )
        shopList.adapter = arrayAdapter
        shopList.onItemClickListener = AdapterView.OnItemClickListener{_, _, pos, _ -> onListItemClick(pos)}
    }

    private fun onListItemClick(pos: Int) {
        Toast.makeText(this, "You have clicked this one at position $pos", Toast.LENGTH_LONG).show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu1, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        when (id){
            R.id.map -> Toast.makeText(this, "Show MapActivity", Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)


}
}