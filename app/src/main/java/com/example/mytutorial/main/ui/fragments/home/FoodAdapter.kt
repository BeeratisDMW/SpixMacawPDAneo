package com.example.mytutorial.main.ui.fragments.home


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.mytutorial.main.ui.R
import com.example.mytutorial.main.ui.databinding.FoodItemBinding
import com.example.mytutorial.main.ui.db.AppRepository
import com.example.mytutorial.main.ui.db.CartItem
import com.example.mytutorial.main.ui.db.Food
import com.example.mytutorial.main.ui.db.FoodDatabase
import com.example.mytutorial.main.ui.db.FoodViewModel
import com.example.mytutorial.main.ui.db.FoodViewModelProviderFactory
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.food_item.*
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList



class FoodAdapter(
    private val context: Context

) :RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    @Inject
     lateinit var mFoodViewModel: FoodViewModel
     @Inject
    lateinit var viewModelProviderFactory: FoodViewModelProviderFactory

    @Inject
    lateinit var repository: AppRepository
    private var userList : List<Food> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)

        val foodDao = context.applicationContext?.let { FoodDatabase.getDatabase(it).foodDao }
        repository = AppRepository(foodDao!!)



        return FoodViewHolder(view)


    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        val currentItem = userList[position]
        holder.itemView.food_title_tv.text = currentItem.title
        holder.itemView.food_desc_tv.text = currentItem.desc
        holder.itemView.food_price_tv.text = currentItem.price.toString()
        val x: String = holder.itemView.food_title_tv.text.toString()
        val y: String = holder.itemView.food_desc_tv.text.toString()
        val z: String = holder.itemView.food_price_tv.text.toString()


        holder.itemView.buttonAddToCart.setOnClickListener {
            val date = Calendar.getInstance()
            val food= Food(0, x, y, z.toFloat())
            val cartItem = CartItem(0,food,1, date.time.toString())
           insert(cartItem)

            Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show()


        }


        holder.itemView.cardView.setOnLongClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditFoodFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
            return@setOnLongClickListener true
        }

    }
    override fun getItemCount(): Int {
        return userList.size
    }


    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val title: TextView = itemView.findViewById(R.id.food_title_tv)
//        val desc: TextView = itemView.findViewById(R.id.food_desc_tv)
//        val price: TextView = itemView.findViewById(R.id.food_price_tv)
//        val foodLayout: Co = itemView.findViewById(R.id.cardView)
    }


        @SuppressLint("NotifyDataSetChanged")
        fun setData(foodList: List<Food>) {

            userList = foodList
            notifyDataSetChanged()

        }
    private fun insert(cartItem: CartItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.addCartOrder(cartItem)

    }




    }


