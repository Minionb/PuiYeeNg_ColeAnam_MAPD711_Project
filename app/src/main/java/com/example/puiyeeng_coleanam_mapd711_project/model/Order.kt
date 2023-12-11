package com.example.puiyeeng_coleanam_mapd711_project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val orderId: Long = 0,
    val customerId: Long,
    val productName: String,
    val productPrice: Double,
    val orderDate: String,
    val quantity: Int,
    val totalPrice: Double
)
