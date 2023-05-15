package com.example.mytutorial.main.ui.db

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: AppRepository

) : ViewModel()   {



     val readAllMenuData: LiveData<List<Food>> = repository.readAllData
     var readCartItems: LiveData<MutableList<CartItem>> = repository.readCartOrder
     var getAllOrder: LiveData<MutableList<OrderEntity>> = repository.getAllOrder


    init {

    }

    fun addFood(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFood(food)
        }
    }
    fun editFood(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            repository.editFood(food)
        }
    }
    fun insertComplOrder(orderEntity: OrderEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertComplOrder(orderEntity)
        }
    }

    fun editCartFood(cartItem: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.editFood(cartItem)
        }
    }
    fun deleteFood(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFood(food)
        }
    }
    fun addItemToCart(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCartOrder(cartItem)
        }
    }
    fun deleteItemFromCart(cartItem: CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFoodFromCart(cartItem)
        }
    }

    fun deleteCart(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCart()
        }
    }
    fun deleteAllOrders(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllOrders()
        }
    }


}