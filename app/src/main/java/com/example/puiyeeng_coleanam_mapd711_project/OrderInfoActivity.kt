package com.example.puiyeeng_coleanam_mapd711_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOrderBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOrderInfoBinding

class OrderInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}