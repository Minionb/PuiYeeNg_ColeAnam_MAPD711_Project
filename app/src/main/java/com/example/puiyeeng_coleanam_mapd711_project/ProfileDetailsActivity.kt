package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puiyeeng_coleanam_mapd711_project.dao.OrderRepository
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityLoginBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityProfileOrderHistoryBinding
import com.example.puiyeeng_coleanam_mapd711_project.db.CustomerDatabase
import com.example.puiyeeng_coleanam_mapd711_project.db.OrderDatabase
import com.example.puiyeeng_coleanam_mapd711_project.viewmodel.OrderViewModel
import com.example.puiyeeng_coleanam_mapd711_project.viewmodel.ViewModelFactoryOrder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileOrderHistoryBinding
    private lateinit var recycleView: RecyclerView
    private lateinit var viewModel: OrderViewModel

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        val customerUsername = sharedPreferences.getString("customer_username", "") ?: ""

        // Register order view model
        val repository =
            OrderRepository(OrderDatabase.getDatabaseInstance(applicationContext).orderDao())
        val viewModelFactoryOrder = ViewModelFactoryOrder(repository)
        viewModel = ViewModelProvider(this, viewModelFactoryOrder)[OrderViewModel::class.java]
        // Register recycler view
        recycleView = binding.orderRecyclerView

        loadCustomerInfo(customerUsername)

        binding.editProfile.setOnClickListener{
            startActivity(Intent(this@ProfileDetailsActivity, EditCustomerActivity::class.java))
        }

        binding.logoutButton.setOnClickListener{
            sharedPreferences.edit().putString("customer_username", null).apply()
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    this@ProfileDetailsActivity,
                    "Logout successful",
                    Toast.LENGTH_LONG
                ).show()
            }
            startActivity(Intent(this@ProfileDetailsActivity, MainActivity::class.java))
        }
    }

    private fun initRecyclerview() {
        recycleView.layoutManager = LinearLayoutManager(this@ProfileDetailsActivity)
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

                val orders = viewModel.getOrderListByCustomer(customer.customerId)
                lifecycleScope.launch(Dispatchers.Main) {
                    initRecyclerview()
                    val adapter = OrderAdapter(orders)
                    recycleView.adapter = adapter
                }
            }
        }
    }
}