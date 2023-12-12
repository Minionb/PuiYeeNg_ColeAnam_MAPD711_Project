package com.example.puiyeeng_coleanam_mapd711_project


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityOrderBinding
import com.example.puiyeeng_coleanam_mapd711_project.db.CustomerDatabase
import com.example.puiyeeng_coleanam_mapd711_project.db.OrderDatabase
import com.example.puiyeeng_coleanam_mapd711_project.model.Customer
import com.example.puiyeeng_coleanam_mapd711_project.model.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    lateinit var sharedPreferences: SharedPreferences

    var customer: Customer? = null
    var checkedinUsername: String = ""
    var productName: String = ""
    var productPrice: Double = 0.0
    var quantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        checkedinUsername = sharedPreferences.getString("customer_username", "") ?: ""
        productName = sharedPreferences.getString("product_name", "") ?: ""
        val productPriceString = sharedPreferences.getString("product_price", "") ?: ""
        productPrice = productPriceString.toDouble()
        CoroutineScope(Dispatchers.IO).launch {
            val customerDao = CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            println(checkedinUsername)
            customer = customerDao.getCustomerByUsername(checkedinUsername)
            println("Hi")
            println(customer)
            println("HiHi")

            binding.customerNameInput.text = customer?.firstName + " " + customer?.lastName
            binding.productName.text = productName
            binding.productPriceInput.text = productPriceString
            binding.customerAddressInput.text = customer?.address
            binding.cityInput.text = customer?.city
            binding.postalCodeInput.text = customer?.postalCode
            binding.telephoneNumberInput.text = customer?.phoneNumber
        }


        binding.orderButton.setOnClickListener() {
            val quantityString = binding.quantitySpinner.getSelectedItem().toString()
            quantity = quantityString.toInt()

            val totalPrice = quantity * productPrice

            customer?.let {
                makeOrder(
                    it.customerId,
                    productName,
                    productPrice,
                    quantity,
                    totalPrice
                )
                startActivity(Intent(this, OrderInfoActivity::class.java))
            }
        }
    }

    fun makeOrder(customerId: Long, productName: String, productPrice: Double, quantity: Int, totalPrice: Double) {
        val date = getCurrentDate()

        CoroutineScope(Dispatchers.IO).launch {

            val orderDao = OrderDatabase.getDatabaseInstance(applicationContext).orderDao()

            val order = Order(customerId = customerId, productName = productName, productPrice = productPrice, orderDate = date, quantity = quantity,totalPrice = totalPrice)
            orderDao.insertOrder(order)

        }

    }

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }
}
