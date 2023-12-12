package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityMainBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityWomenListingBinding

class WomenListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWomenListingBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWomenListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()


        binding.customerInfoFab.setOnClickListener{
            startActivity(Intent(this@WomenListingActivity, ProfileDetailsActivity::class.java))
        }

        // Submit Women Sweater order
        binding.button1.setOnClickListener {
            editor.putString("product_name", "LONG SLEEVE CREW NECK SWEATER").apply()
            editor.putString("product_price", "59.90").apply()
            startActivity(Intent(this, OrderActivity::class.java))
        }

        // Submit Women Coat order
        binding.button2.setOnClickListener {
            editor.putString("product_name", "TRENCH COAT SPECIAL EDITION").apply()
            editor.putString("product_price", "249.90").apply()
            startActivity(Intent(this, OrderActivity::class.java))
        }

        // Submit Women Pants order
        binding.button3.setOnClickListener {
            editor.putString("product_name", "RIBBED LONG SLEEVE KNIT DRESS").apply()
            editor.putString("product_price", "49.90").apply()
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.women -> {
                startActivity(Intent(this, WomenListingActivity::class.java))
                true
            }
            R.id.men -> {
                startActivity(Intent(this, MenListingActivity::class.java))
                true
            }
            R.id.ourLocations -> {
                startActivity(Intent(this, OurLocationsActivity::class.java))
                true
            }
            R.id.fashionGuide -> {
                startActivity(Intent(this, FashionGuideActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }
}