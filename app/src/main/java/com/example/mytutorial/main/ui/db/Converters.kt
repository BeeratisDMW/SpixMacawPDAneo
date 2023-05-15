package com.example.mytutorial.main.ui.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    var gson = Gson()


    @TypeConverter
    fun foodItemToString(food: Food): String {
        return gson.toJson(food)

    }

    @TypeConverter
    fun stringToFoodItem(data: String): Food {
        val listType = object : TypeToken<Food>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun cartListToString(cartOrder: ArrayList<CartItem>): String {
        return gson.toJson(cartOrder)

    }

    @TypeConverter
    fun stringToCartList(dataOrder: String): ArrayList<CartItem> {
        val listTypeOrder = object : TypeToken<ArrayList<CartItem>>() {}.type
        return gson.fromJson(dataOrder, listTypeOrder)
    }
}