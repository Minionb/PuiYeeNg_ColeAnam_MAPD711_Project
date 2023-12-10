package com.example.puiyeeng_coleanam_mapd711_project.dao

import androidx.room.*
import com.example.puiyeeng_coleanam_mapd711_project.model.Customer

@Dao
interface CustomerDao {

    //insert customer
    @Insert
    suspend fun insertCustomer(customer: Customer)

    //get all customers
    @Query("SELECT * FROM customers")
    fun getAllCustomers(): List<Customer>

    //get a customer by their username
    @Query("SELECT * FROM customers WHERE username = :username LIMIT 1")
    suspend fun getCustomerByUsername(username: String): Customer?

    //get a customer by their customerId
    @Query("SELECT * FROM customers WHERE customerId = :customerId LIMIT 1")
    suspend fun getCustomerByCustomerId(customerId: Long): Customer?

    //update customer
    @Update
    suspend fun updateCustomer(customer: Customer)

    //delete customer
    @Delete
    suspend fun deleteCustomer(customer: Customer)

    //deleteAll
    @Query("DELETE FROM customers")
    suspend fun deleteAll()
}