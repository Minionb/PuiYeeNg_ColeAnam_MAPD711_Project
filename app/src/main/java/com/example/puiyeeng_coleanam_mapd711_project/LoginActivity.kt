package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityLoginBinding
import com.example.puiyeeng_coleanam_mapd711_project.db.CustomerDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        binding.buttonLogin.setOnClickListener {
            val usernameField = binding.editTextUsername.text.toString()
            val passwordField = binding.editTextPassword.text.toString()

            if (binding.editTextUsername.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Username is empty", Toast.LENGTH_SHORT).show()
            }
            else if (binding.editTextPassword.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            else {
                login(usernameField, passwordField)
            }
        }
    }

    fun login(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val customerDao = CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            val customer = customerDao.getCustomerByUsername(username)

            if (customer != null && customer.password == password) {
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login successful, welcome ${customer.firstName}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                // pass cusotmer username to shared preference for later usage
                sharedPreferences.edit().putString("customer_username", username).apply()
                startActivity(Intent(this@LoginActivity, FashionGuideActivity::class.java))
            }
            else {
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login unsuccessful, username or password is incorrect",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}