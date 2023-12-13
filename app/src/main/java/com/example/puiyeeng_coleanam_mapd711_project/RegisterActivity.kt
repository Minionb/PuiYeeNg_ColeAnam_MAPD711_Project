package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityRegisterBinding
import com.example.puiyeeng_coleanam_mapd711_project.db.CustomerDatabase
import com.example.puiyeeng_coleanam_mapd711_project.model.Customer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val newUsername = binding.username.text.toString()
            val newPassword = binding.password.text.toString()
            val newFirstName = binding.firstName.text.toString()
            val newLastName = binding.lastName.text.toString()
            val newPhoneNum = binding.telephoneNumber.text.toString()
            val newAddress = binding.address.text.toString()
            val newCity = binding.city.text.toString()
            val newPostalCode = binding.postalCode.text.toString()

            CoroutineScope(Dispatchers.IO).launch {

                val customerDao = CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
                val checkCustomer = customerDao.getCustomerByUsername(newUsername)
                if (checkCustomer != null) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Username '$newUsername' already exists, please register with another Username",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else if(newUsername.isEmpty() || newPassword.isEmpty() || newFirstName.isEmpty() || newLastName.isEmpty() || newPhoneNum.isEmpty() || newAddress.isEmpty() || newCity.isEmpty() || newPostalCode.isEmpty()) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "One or more fields are empty",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else if(newPhoneNum.length != 10) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Phone number is invalid",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else if(newPostalCode.length != 7) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Postal Code is invalid",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else {
                    val newCustomer = Customer(username = newUsername, password = newPassword, firstName = newFirstName, lastName = newLastName, phoneNumber = newPhoneNum, address = newAddress, city = newCity, postalCode = newPostalCode)
                    customerDao.insertCustomer(newCustomer)
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Successfully Registered Account '$newUsername'!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    println("New customer created: " + customerDao.getCustomerByUsername(newUsername))
                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                }
            }
        }
    }
}