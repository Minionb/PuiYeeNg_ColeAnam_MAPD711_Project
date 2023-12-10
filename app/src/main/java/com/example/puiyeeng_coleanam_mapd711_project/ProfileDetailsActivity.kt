package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityLoginBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityProfileOrderHistoryBinding
import com.example.puiyeeng_coleanam_mapd711_project.db.CustomerDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileOrderHistoryBinding

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        val customerUsername = sharedPreferences.getString("customer_username", "") ?: ""

        loadCustomerInfo(customerUsername)

        binding.editProfile.setOnClickListener{
            startActivity(Intent(this@ProfileDetailsActivity, EditCustomerActivity::class.java))
        }
    }

    private fun loadCustomerInfo(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val customerDao = CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            val customer = customerDao.getCustomerByUsername(username)

            if (customer != null) {
                binding.username.text = customer.username
                binding.firstName.text = customer.firstName
                binding.lastName.text = customer.lastName
                binding.telephoneNumber.text = customer.phoneNumber
                binding.address.text = customer.address
                binding.city.text = customer.city
                binding.postalCode.text = customer.postalCode
            }
        }
    }
}