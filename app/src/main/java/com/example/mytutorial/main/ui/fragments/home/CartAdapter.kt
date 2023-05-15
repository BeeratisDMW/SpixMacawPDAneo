 package com.example.mytutorial.main.ui.fragments.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytutorial.main.ui.R
import com.example.mytutorial.main.ui.db.AppRepository
import com.example.mytutorial.main.ui.db.CartItem
import com.example.mytutorial.main.ui.db.Food
import com.example.mytutorial.main.ui.db.FoodDatabase
import com.example.mytutorial.main.ui.db.FoodViewModel
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


 class CartAdapter(
     private val context: Context
 ): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

     @Inject
     lateinit var repository: AppRepository

     var orderList = mutableListOf<CartItem>()



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)

        val foodDao = context.applicationContext?.let { FoodDatabase.getDatabase(it).foodDao }
        repository = AppRepository(foodDao!!)


        return ViewHolder(view)

    }


    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val newOrderList = orderList[position]
        holder.itemView.order_food_title_tv.text = newOrderList.order.title
        holder.itemView.order_food_desc_tv.text = newOrderList.order.desc
        holder.itemView.order_food_price_tv.text = newOrderList.order.price.toString()
        holder.itemView.tvQuantity.text = newOrderList.quantity.toString()
        holder.itemView.tvDay.text = newOrderList.dateAndTime.toString()


        holder.itemView.btnPlus.setOnClickListener {
            var inc = newOrderList.quantity
            inc++
            holder.itemView.tvQuantity.text = inc.toString()
        }
        holder.itemView.btnMinus.setOnClickListener {
            var dec = newOrderList.quantity
            dec--
            if(dec<=0){
                deleteFoodFromCart(newOrderList)
            } else {

                holder.itemView.tvQuantity.text = dec.toString()
            }
        }



    }
     @SuppressLint("NotifyDataSetChanged")
     fun setCartData(foodList: MutableList<CartItem>) {
         orderList = foodList
        notifyDataSetChanged()



     }
     private fun deleteFoodFromCart(cartItem: CartItem) = CoroutineScope(Dispatchers.IO).launch {
         repository.deleteFoodFromCart(cartItem)
     }






 }