package com.example.puiyeeng_coleanam_mapd711_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityMenListingBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityWomenListingBinding

class MenListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}