package com.example.puiyeeng_coleanam_mapd711_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityEditCustomerBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityMenListingBinding

class EditCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}