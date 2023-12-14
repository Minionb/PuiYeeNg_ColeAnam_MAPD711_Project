package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOrderBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOrderInfoBinding
import com.example.puiyeeng_coleanam_mapd711_project.db.CustomerDatabase
import com.example.puiyeeng_coleanam_mapd711_project.db.OrderDatabase
import com.example.puiyeeng_coleanam_mapd711_project.model.Customer
import com.example.puiyeeng_coleanam_mapd711_project.model.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderInfoBinding
    lateinit var sharedPreferences: SharedPreferences

    var customer: Customer? = null
    var order: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        // Get Customer username from shared preference
        val customerUsername = sharedPreferences.getString("customer_username", "") ?: ""

        CoroutineScope(Dispatchers.IO).launch {

            val customerDao = CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            customer = customerDao.getCustomerByUsername(customerUsername)

            val orderDao = OrderDatabase.getDatabaseInstance(applicationContext).orderDao()

            // get customer orders
            order = customer?.customerId?.let { orderDao.getOrderByCustomerId(it) }
            println(order?.orderId)

            // Display customer info
            binding.usernameLabel.text = customer?.username
            binding.firstnameLabel.text = customer?.firstName
            binding.lastNameLabel.text = customer?.lastName
            binding.telephoneNumberLabel.text = customer?.phoneNumber
            binding.addressLabel.text = customer?.address
            binding.cityLabel.text = customer?.city
            binding.postalCodeLabel.text = customer?.postalCode

            // display order info
            binding.orderIdLabel.text = order?.orderId.toString()
            binding.customerIdLabel.text = order?.customerId.toString()
            binding.productNameLabel.text = order?.productName.toString()
            binding.productPriceLabel.text = order?.productPrice.toString()
            binding.orderDateLabel.text = order?.orderDate
            binding.quantityLabel.text = order?.quantity.toString()
            binding.totalPriceLabel.text = order?.totalPrice.toString()

            binding.goBackbutton.setOnClickListener {
                startActivity(Intent(this@OrderInfoActivity, WomenListingActivity::class.java))
            }
        }

    }
}