package com.example.mytutorial.main.ui.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.room.FtsOptions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val foodDao: FoodDao
) {

    //emfanizei ta dedomena apo to menu
    val readAllData : LiveData<List<Food>> = foodDao.readAllData()
    //emfanizei ta dedomena apo to kalathi
    val readCartOrder: LiveData<MutableList<CartItem>> = foodDao.readCartOrder().asLiveData()

    val getAllOrder: LiveData<MutableList<OrderEntity>> = foodDao.getAllOrder()


    //prosthetei dedomena apo to + koumpi
    suspend fun addFood(food: Food) {
        foodDao.insertFood(food)
    }
    //onlongclick mesa apo to fragment kanei edit (food)
    suspend fun editFood(food: Food) {
        foodDao.editFood(food)
    }
    suspend fun editFood(cartItem: CartItem) {
        foodDao.editFood(cartItem)
    }
    //onlongclick mesa apo to fragment kanei delete (food)
    suspend fun deleteFood(food: Food) {
        foodDao.deleteFood(food)
    }

    //prosthetei dedomena sto kalathi
    suspend fun addCartOrder(cartItem: CartItem) {
        foodDao.addCartOrder(cartItem)
    }
    //diagrafei 1 item apo to kalathi
    suspend fun deleteFoodFromCart(cartItem: CartItem) {
        foodDao.deleteFoodFromCart(cartItem)
    }
    //diagrafei ola ta dedomena thw vashs
    suspend fun deleteCart() {
        foodDao.deleteCart()
    }

    suspend fun insertComplOrder(orderEntity: OrderEntity) {
        foodDao.insertComplOrder(orderEntity)
    }

    suspend fun deleteAllOrders() {
        foodDao.deleteAllOrders()
    }




}