package com.example.puiyeeng_coleanam_mapd711_project.dao

import com.example.puiyeeng_coleanam_mapd711_project.model.Order

class OrderRepository(private val orderDao: OrderDao) {

    suspend fun insertOrder(order: Order){
        orderDao.insertOrder(order)
    }

    suspend fun getAllOrders(): List<Order>{
        return orderDao.getAllOrders()
    }

    suspend fun getOrderByCustomerId(customerId: Long): Order? {
        return orderDao.getOrderByCustomerId(customerId)
    }

    fun getOrderListByCustomer(customerId: Long): List<Order> {
        return orderDao.getOrderListByCustomer(customerId)
    }
}