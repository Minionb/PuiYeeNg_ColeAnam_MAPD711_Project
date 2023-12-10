package com.example.puiyeeng_coleanam_mapd711_project.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.puiyeeng_coleanam_mapd711_project.dao.OrderDao
import com.example.puiyeeng_coleanam_mapd711_project.model.Order

@Database(entities = [Order::class], version = 1)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        private var INSTANCE: OrderDatabase? = null

        fun getDatabaseInstance(context: Context): OrderDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderDatabase::class.java,
                    "customer_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}