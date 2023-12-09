package com.example.puiyeeng_coleanam_mapd711_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityEditCustomerBinding
import com.example.puiyeeng_coleanam_mapd711_project.databinding.ActivityMenListingBinding
import com.example.puiyeeng_coleanam_mapd711_project.db.CustomerDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditCustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCustomerBinding

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("SharedLoginPref", Context.MODE_PRIVATE)

        val customerUsername = sharedPreferences.getString("customer_username", "") ?: ""

//        loadCustomerInfo(customerUsername)

        binding.confirm.setOnClickListener {
            saveCustomerChanges(customerUsername)
        }

        binding.goBack.setOnClickListener {
            startActivity(Intent(this@EditCustomerActivity, ProfileDetailsActivity::class.java))
        }

    }

    private fun saveCustomerChanges(username: String) {

        val newPassword = binding.newPassword.text.toString()
        val newFirstName = binding.newFirstName.text.toString()
        val newLastName = binding.newLastName.text.toString()
        val newPhoneNum = binding.newTelephoneNumber.text.toString()
        val newAddress = binding.newAddress.text.toString()
        val newCity = binding.newCity.text.toString()
        val newPostalCode = binding.newPostalCode.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val customerDao = CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
            var customer = customerDao.getCustomerByUsername(username)

            if (customer != null) {
                var updateFields = mutableListOf<String>()

                // store username to the updateFields
                customer.username = username

                // store newPassword to the updateFields, change the password of user record to newPassword
                if (newPassword != "") {
                    updateFields.add("Password: ******")
                    customer.password = newPassword
                }

                // store newFirstName to the updateFields, change the firstname of user record to newFirstName
                if (newFirstName != "") {
                    updateFields.add("First Name: $newFirstName")
                    customer.firstName = newFirstName
                }

                // store newLastName to the updateFields, change the lastName of user record to newLastName
                if (newLastName != "") {
                    updateFields.add("Last Name: $newLastName")
                    customer.lastName = newLastName
                }

                // store newPhoneNum to the updateFields, change the telephone number of user record to newPhoneNum
                if (newPhoneNum != "") {
                    updateFields.add("Telephone Number: $newPhoneNum")
                    customer.phoneNumber = newPhoneNum
                }

                // store newAddress to the updateFields, change the address of user record to newAddress
                if (newAddress != "") {
                    updateFields.add("Address: $newAddress")
                    customer.address = newAddress
                }

                // store newAddress to the updateFields, change the city of user record to newCity
                if (newCity != "") {
                    updateFields.add("City: $newCity")
                    customer.city = newCity
                }

                // store newPostalCode to the updateFields, change the Postal Code of user record to newPostalCode
                if (newPostalCode != "") {
                    updateFields.add("Postal Code: $newPostalCode")
                    customer.postalCode = newPostalCode
                }

                println(customer)
                println(updateFields)

                // Alert when no updated fields are entered
                if (updateFields.isEmpty()) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@EditCustomerActivity,
                            "No input for update!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else {
                    // update customer record
                    CoroutineScope(Dispatchers.IO).launch {
                        customerDao.updateCustomer(customer)
                    }
                    // Alert box for Successfully Updated Profile
                    val updateFieldString = updateFields.joinToString("\n")
                    lifecycleScope.launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@EditCustomerActivity,
                            "Successfully Updated Profile:\n$updateFieldString",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    startActivity(Intent(this@EditCustomerActivity, ProfileDetailsActivity::class.java))
                }
            }
        }
    }

//    private fun loadCustomerInfo(username: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val customerDao = CustomerDatabase.getDatabaseInstance(applicationContext).customerDao()
//            val customer = customerDao.getCustomerByUsername(username)
//
//            if (customer != null) {
//                binding.newPassword.text = customer.password
//                binding.newFirstName.text = customer.firstName
//                binding.lastName.text = customer.lastName
//                binding.telephoneNumber.text = customer.phoneNumber
//                binding.address.text = customer.address
//                binding.city.text = customer.city
//                binding.postalCode.text = customer.postalCode
//            }
//        }
//    }
}