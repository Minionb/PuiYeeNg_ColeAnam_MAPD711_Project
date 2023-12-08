package com.example.puiyeeng_coleanam_mapd711_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOrderBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOurLocationsBinding

class OurLocationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOurLocationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOurLocationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}