package com.example.mytutorial.main.ui.fragments.home

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mytutorial.main.ui.R
import com.example.mytutorial.main.ui.db.AppRepository
import com.example.mytutorial.main.ui.db.Food
import com.example.mytutorial.main.ui.db.FoodViewModel
import com.example.mytutorial.main.ui.db.FoodViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.fragment_add_food.view.*
import javax.inject.Inject

@AndroidEntryPoint
class AddFoodFragment : Fragment() {

    private lateinit var mFoodViewModel: FoodViewModel


    lateinit var viewModelProviderFactory: FoodViewModelProviderFactory

    @Inject
    lateinit var repository: AppRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_food, container, false)


        //arxikopoihsh viewModel
        mFoodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        viewModelProviderFactory = FoodViewModelProviderFactory(repository,this, arguments)


        //add button gia na vazw to edittext sthn vasi
        view.addItemButton.setOnClickListener {
            insertDataToDatabase()

        }


        return view

    }
    //vazw  data sthn database, oti leei to function
    private fun insertDataToDatabase() {
        val foodTitle = food_title_et.text.toString()
        val foodDesc = food_desc_et.text.toString()
        val foodPrice = food_price_et.text.toString()

        if(inputCheck(foodTitle, foodDesc, foodPrice.toFloat())) {

            //create food object
            val food = Food(0, foodTitle, foodDesc, foodPrice.toFloat())

            //add data to database
            mFoodViewModel.addFood(food)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            //Navigate home
            findNavController().navigate(R.id.action_addFoodFragment_to_homeFragment)

        } else {
            Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    //function gia na mhn afhnoume keno
    private fun inputCheck(foodTitle: String, foodDesc: String, foodPrice: Float): Boolean {
        return !(TextUtils.isEmpty(foodTitle) && TextUtils.isEmpty(foodDesc) && TextUtils.isEmpty(foodPrice.toString()))

    }


}