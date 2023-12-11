package com.example.puiyeeng_coleanam_mapd711_project.dao

import androidx.room.*
import com.example.puiyeeng_coleanam_mapd711_project.model.Order

@Dao
interface OrderDao {

    // Insert order
    @Insert
    suspend fun insertOrder(order: Order)

    // get all orders
    @Query("SELECT * FROM orders")
    fun getAllOrders(): List<Order>

    //get the most recent order by their customerId
    @Query("SELECT * FROM orders WHERE orderId = (SELECT MAX(orderId) FROM orders where customerId = :customerId )")
    suspend fun getOrderByCustomerId(customerId: Long): Order?
//    @Query("SELECT * FROM orders WHERE customerId = :customerId LIMIT 1")
//    suspend fun getOrderByCustomerId(customerId: Long): Order?

    // get all the orders by customerId
    @Query("SELECT * FROM orders WHERE customerId = :customerId")
    fun getOrderListByCustomer(customerId: Int): List<Order>

    // get a order by their orderId
    @Query("SELECT * FROM orders WHERE orderId = :orderId LIMIT 1")
    suspend fun getOrderByOrderId(orderId: Long): Order?

    //update order
    @Update
    suspend fun updateOrder(order: Order)

    //delete order
    @Delete
    suspend fun deleteCustomer(order: Order)

    //deleteAll
    @Query("DELETE FROM orders")
    suspend fun deleteAll()
}
