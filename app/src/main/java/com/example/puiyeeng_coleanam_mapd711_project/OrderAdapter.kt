package com.example.puiyeeng_coleanam_mapd711_project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.puiyeeng_coleanam_mapd711_project.model.Order

class OrderAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderAdapter.OrderViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewModel {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_layout, parent, false)
        return OrderViewModel(view)
    }

    override fun onBindViewHolder(holder: OrderViewModel, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount() = orders.size

    inner class OrderViewModel(orderview: View) : ViewHolder(orderview) {

        private val productText: TextView = orderview.findViewById(R.id.productText)
        private val priceText: TextView = orderview.findViewById(R.id.priceText)
        private val orderDateText: TextView = orderview.findViewById(R.id.orderDateText)
        private val quantityText: TextView = orderview.findViewById(R.id.quantityText)

        @SuppressLint("SetTextI18n")
        fun bind(order: Order) {
            productText.text = productText.text.toString() + order.productName
            priceText.text = priceText.text.toString() + "$" + order.productPrice.toString()
            orderDateText.text = orderDateText.text.toString() + order.orderDate
            quantityText.text = quantityText.text.toString() + order.quantity.toString()
        }
    }
}