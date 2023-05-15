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
import androidx.navigation.fragment.navArgs
import com.example.mytutorial.main.ui.R
import com.example.mytutorial.main.ui.db.AppRepository
import com.example.mytutorial.main.ui.db.Food
import com.example.mytutorial.main.ui.db.FoodViewModel
import com.example.mytutorial.main.ui.db.FoodViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit_food.*
import kotlinx.android.synthetic.main.fragment_edit_food.view.*
import javax.inject.Inject

@AndroidEntryPoint
class EditFoodFragment : Fragment() {


    private val args: EditFoodFragmentArgs by navArgs()
    private lateinit var mFoodViewModel: FoodViewModel



    lateinit var viewModelProviderFactory: FoodViewModelProviderFactory

    @Inject
    lateinit var repository: AppRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_edit_food, container, false)

        //arxikopoihsh viewModel
        mFoodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        viewModelProviderFactory = FoodViewModelProviderFactory(repository,this, arguments)

        view.edit_food_title_et.setText(args.currentItem.title)
        view.edit_food_desc_et.setText(args.currentItem.desc)
        view.edit_food_price_et.setText(args.currentItem.price.toString())


        view.updateItemButton.setOnClickListener {
            updateItem()
        }
        view.deleteItemButton.setOnClickListener {
            deleteItem()
        }



        return view
    }



    private fun updateItem() {
        val foodTitleNew = edit_food_title_et.text.toString()
        val foodDescNew = edit_food_desc_et.text.toString()
        val foodPriceNew = edit_food_price_et.text.toString()

        if(inputCheck(foodTitleNew, foodDescNew, foodPriceNew)) {
            val updateFood = Food(args.currentItem.id, foodTitleNew, foodDescNew, foodPriceNew.toFloat())
            mFoodViewModel.editFood(updateFood)
            Toast.makeText(requireContext(), "Data Updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editFoodFragment_to_homeFragment)

            } else {
            Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_LONG).show()
        }
    }
    //function gia na mhn afhnoume keno
    private fun inputCheck(foodTitleNew: String, foodDescNew: String, foodPriceNew: String): Boolean {
        return !(TextUtils.isEmpty(foodTitleNew) && TextUtils.isEmpty(foodDescNew) && TextUtils.isEmpty(foodPriceNew))

    }
    private fun deleteItem() {

            mFoodViewModel.deleteFood(args.currentItem)
            Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editFoodFragment_to_homeFragment)




    }


}

