package com.example.mytutorial.main.ui.db

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


class FoodViewModelProviderFactory @Inject constructor(
    private val repository: AppRepository,
    private val savedStateRegistryOwner: SavedStateRegistryOwner,
    private val defaultArgs: Bundle?
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            return FoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}