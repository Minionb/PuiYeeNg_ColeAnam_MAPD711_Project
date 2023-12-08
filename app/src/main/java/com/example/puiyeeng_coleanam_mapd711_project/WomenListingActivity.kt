package com.example.puiyeeng_coleanam_mapd711_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityMainBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityWomenListingBinding

class WomenListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWomenListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWomenListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}