package com.example.mytutorial.main.ui.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "order_table")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val comOrderId: Int = 0,
    val cartOrder: ArrayList<CartItem>,
    val total: Double,
    val kindOfOrder: String,
    val date: String

)