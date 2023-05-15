package com.example.mytutorial.main.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytutorial.main.ui.R
import com.example.mytutorial.main.ui.db.AppRepository
import com.example.mytutorial.main.ui.db.CartItem
import com.example.mytutorial.main.ui.db.Food
import com.example.mytutorial.main.ui.db.FoodViewModel
import com.example.mytutorial.main.ui.db.FoodViewModelProviderFactory
import com.example.mytutorial.main.ui.db.OrderEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.spinnerTypeOforder
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.fragment_home.view.recyclerViewHome
import java.util.Calendar
import java.util.Collections
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {


    lateinit var mFoodViewModel: FoodViewModel
    lateinit var cartAdapter: CartAdapter

    lateinit var viewModelProviderFactory: FoodViewModelProviderFactory

    @Inject
    lateinit var repository: AppRepository



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_cart, container, false)

        view.orderCartcRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.orderCartcRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        cartAdapter = CartAdapter(requireContext())
        view.orderCartcRecyclerView.adapter = cartAdapter
        setupViemodel()




        view.btnClearCart.setOnClickListener {
            mFoodViewModel.deleteCart()

        }
        view.btnSendOrder.setOnClickListener {
            mFoodViewModel.readCartItems
            val foodlist= ArrayList<CartItem>()


            val date = Calendar.getInstance()

            val orderEntity= OrderEntity(0, foodlist, 0.0, "Devilery",date.time.toString())
            mFoodViewModel.insertComplOrder(orderEntity)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
    private fun setupViemodel() {
        viewModelProviderFactory = FoodViewModelProviderFactory(repository, this, arguments)
        mFoodViewModel = ViewModelProvider(this,viewModelProviderFactory).get(FoodViewModel::class.java)
        mFoodViewModel.readCartItems.observe(this as LifecycleOwner) {
            renderData(it)
        }




    }
    private fun renderData(foodlist: MutableList<CartItem>) {
        cartAdapter.run {
            setCartData(foodlist)
            notifyDataSetChanged()
        }
    }





}