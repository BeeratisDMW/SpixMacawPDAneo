package com.example.mytutorial.main.ui


import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mytutorial.main.ui.databinding.ActivityHomeBinding
import com.example.mytutorial.main.ui.db.*
import com.example.mytutorial.main.ui.fragments.home.HomeFragment

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.bottomNavigationView
import kotlinx.android.synthetic.main.activity_home.newsNavHostFragment


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


//    lateinit var viewModel: FoodViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        if(savedInstanceState == null){
//            supportFragmentManager.beginTransaction()
//                .add(R.id.newsNavHostFragment, HomeFragment.newInstance())
//                .commit()
//        }



//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

//        val appRepository = AppRepository(FoodDatabase(this))
//        val viewModelProviderFactory = FoodViewModelProviderFactory(application, appRepository)
//        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FoodViewModel::class.java)






    }

}