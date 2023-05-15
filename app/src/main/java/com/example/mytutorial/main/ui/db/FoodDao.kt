package com.example.mytutorial.main.ui.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFood(food: Food) //menu list add single item

    @Upsert
    suspend fun addCartOrder(cartItem: CartItem) // cart list add single item

    @Update
    suspend fun editFood(food: Food) //menu single

    @Update
    suspend fun editFood(cartItem: CartItem)



    @Delete
    suspend fun deleteFood(food: Food) //menu single

    @Delete
    suspend fun deleteFoodFromCart(cartItem: CartItem) //cart single

    @Query("DELETE FROM cart_table")
    suspend fun deleteCart() //cart clear

    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Food>>

    @Query("SELECT * FROM cart_table ORDER BY orderId ASC")
    fun readCartOrder(): Flow<MutableList<CartItem>>


    @Upsert
    suspend fun insertComplOrder(orderEntity: OrderEntity)

    @Query("DELETE FROM order_table")
    suspend fun deleteAllOrders()

    @Query("SELECT * FROM order_table ORDER BY date DESC")
    fun getAllOrder(): LiveData<MutableList<OrderEntity>>








}







