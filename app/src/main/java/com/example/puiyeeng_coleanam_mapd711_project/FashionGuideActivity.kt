package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityFashionGuideBinding

class FashionGuideActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFashionGuideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFashionGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.downloadButton.setOnClickListener {
            startMyService()
        }

        binding.stopButton.setOnClickListener {
            stopMyService()
        }

        // register
        val webSettings: WebSettings = binding.loadAWebSite.settings
        webSettings.javaScriptEnabled = true

        // Load a web page of fashion guide
        binding.loadAWebSite.loadUrl("https://www.vogue.com/fashion")
    }

    private fun startMyService() {
        val serviceIntent = Intent(this, FashionGuideService::class.java)
        startService(serviceIntent)
    }

    private fun stopMyService() {
        val serviceIntent = Intent(this, FashionGuideService::class.java)
        stopService(serviceIntent)
    }
}