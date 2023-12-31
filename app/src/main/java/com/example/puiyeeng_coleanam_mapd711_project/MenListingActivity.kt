package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityMenListingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MenListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenListingBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        val checkedinUsername = sharedPreferences.getString("customer_username", "") ?: ""

        binding.customerInfoFab.setOnClickListener{
            if (checkedinUsername == "") {
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@MenListingActivity,
                        "Please login first to access profile details",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                startActivity(Intent(this@MenListingActivity, ProfileDetailsActivity::class.java))
            }
        }

        // Submit Men Sweater order
        binding.button1.setOnClickListener {
            if (checkedinUsername == "") {
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@MenListingActivity,
                        "User must be logged in to order",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                editor.putString("product_name", "LONG SLEEVE CREW NECK SWEATER").apply()
                editor.putString("product_price", "59.90").apply()
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }

        // Submit Men Coat order
        binding.button2.setOnClickListener {
            if (checkedinUsername == "") {
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@MenListingActivity,
                        "User must be logged in to order",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                editor.putString("product_name", "SEAMLESS DOWN COAT").apply()
                editor.putString("product_price", "249.90").apply()
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }

        // Submit Men Pants order
        binding.button3.setOnClickListener {
            if (checkedinUsername == "") {
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@MenListingActivity,
                        "User must be logged in to order",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                editor.putString("product_name", "AirSense PANTS WOOL LIKE").apply()
                editor.putString("product_price", "59.90").apply()
                startActivity(Intent(this, OrderActivity::class.java))
            }
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

