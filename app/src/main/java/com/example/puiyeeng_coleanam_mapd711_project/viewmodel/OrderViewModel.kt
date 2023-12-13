package com.example.puiyeeng_coleanam_mapd711_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puiyeeng_coleanam_mapd711_project.dao.OrderRepository
import com.example.puiyeeng_coleanam_mapd711_project.model.Order
import kotlinx.coroutines.launch

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    private val _orders = MutableLiveData<List<Order>>()

    val orders: LiveData<List<Order>>
        get() = _orders

    suspend fun getAllOrders(): List<Order> {
        return orderRepository.getAllOrders()
    }

    fun insertOrder(order: Order) {
        viewModelScope.launch {
            orderRepository.insertOrder(order)
        }
    }

    suspend fun getOrderByCustomerId(customerId: Long): Order? {
        return orderRepository.getOrderByCustomerId(customerId)
    }

    fun getOrderListByCustomer(customerId: Long): List<Order> {
        return orderRepository.getOrderListByCustomer(customerId)
    }
}