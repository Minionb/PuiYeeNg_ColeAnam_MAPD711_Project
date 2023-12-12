package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
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

//        binding.stopButton.setOnClickListener {
//            stopMyService()
//        }

        binding.webButton.setOnClickListener {

            binding.loadAWebSite.settings.setSupportZoom(true)
            binding.loadAWebSite.settings.builtInZoomControls
            binding.loadAWebSite.settings.useWideViewPort
            binding.loadAWebSite.loadUrl("https://www.vogue.com/fashion")
            val webClient: WebViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    title = binding.loadAWebSite.title
                }
            }

            // ... and listen for changes
            val webChrome: WebChromeClient = object : WebChromeClient() {
                override fun onReceivedIcon(view: WebView, icon: Bitmap) {
                    super.onReceivedIcon(view, icon)
                    //favImage.setImageDrawable(new BitmapDrawable(icon));
                }

                override fun onReceivedTitle(view: WebView, title: String) {
                    super.onReceivedTitle(view, title)
                }
            }
            binding.loadAWebSite.webViewClient = webClient
            // independent browser, set web view as browser
            binding.loadAWebSite.webChromeClient = webChrome
            binding.loadAWebSite.setInitialScale(100)
        }
    }

    private fun startMyService() {
        val serviceIntent = Intent(this, FashionGuideService::class.java)
        startService(serviceIntent)
    }

    private fun stopMyService() {
        val serviceIntent = Intent(this, FashionGuideService::class.java)
        stopService(serviceIntent)
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