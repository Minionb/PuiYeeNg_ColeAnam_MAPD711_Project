package com.example.puiyeeng_coleanam_mapd711_project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Long = 0,
    val username: String,
    val password: String,
    var firstname: String,
    var lastName: String,
    var phoneNumber: String,
    var address: String,
    var city: String,
    var postalCode: String
)