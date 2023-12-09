package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityMainBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityWomenListingBinding

class WomenListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWomenListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWomenListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        //return super.onOptionsItemSelected(item)
    }
}