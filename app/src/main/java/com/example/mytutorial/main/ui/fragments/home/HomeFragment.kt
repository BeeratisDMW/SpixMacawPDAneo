package com.example.mytutorial.main.ui.fragments.home


import android.app.Application
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytutorial.main.ui.HomeActivity
import com.example.mytutorial.main.ui.R
import com.example.mytutorial.main.ui.databinding.FragmentHomeBinding
import com.example.mytutorial.main.ui.db.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.food_item.view.*
import kotlinx.android.synthetic.main.fragment_cart.view.*
import kotlinx.android.synthetic.main.fragment_home.recyclerViewHome
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {


     lateinit var mFoodViewModel : FoodViewModel




    lateinit var viewModelProviderFactory: FoodViewModelProviderFactory

    @Inject
    lateinit var repository: AppRepository

    lateinit var foodAdapter :FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        view.recyclerViewHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.recyclerViewHome.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        foodAdapter = FoodAdapter(requireContext())
        view.recyclerViewHome.adapter = foodAdapter
        setupViemodel()


        //floatingbutton gia add item
        view.add_itemFLbtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFoodFragment)
        }


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
//    companion object {
//        @JvmStatic
//        fun newInstance() =
//            HomeFragment().apply { }
//    }


    private fun setupViemodel() {
        viewModelProviderFactory = FoodViewModelProviderFactory(repository, this, arguments)
        mFoodViewModel = ViewModelProvider(this,viewModelProviderFactory).get(FoodViewModel::class.java)
        mFoodViewModel.readAllMenuData.observe(this as LifecycleOwner) {
            renderData(it)
        }


    }
    private fun renderData(foodlist: List<Food>) {
        foodAdapter.setData(foodlist)
    }

}


