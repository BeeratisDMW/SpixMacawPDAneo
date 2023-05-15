package com.example.mytutorial.main.ui.db


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*


@Entity(tableName = "cart_table")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    var order: Food,
    var quantity: Int = 1,
    var dateAndTime : String

) {


}
