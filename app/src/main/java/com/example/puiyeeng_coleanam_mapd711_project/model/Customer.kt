package com.example.puiyeeng_coleanam_mapd711_project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val customerId: Long = 0,
    var username: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var address: String,
    var city: String,
    var postalCode: String
)
