package com.example.applicationex.lab10.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.applicationex.MainApplication
import com.example.applicationex.lab10.ui.home.HomeViewModel
import com.example.applicationex.lab10.ui.item.ItemDetailsViewModel
import com.example.applicationex.lab10.ui.item.ItemEditViewModel
import com.example.applicationex.lab10.ui.item.ItemEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().lab10Container.itemsRepository
            )
        }
        initializer {
            ItemEntryViewModel(inventoryApplication().lab10Container.itemsRepository)
        }
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().lab10Container.itemsRepository
            )
        }
        initializer {
            HomeViewModel(inventoryApplication().lab10Container.itemsRepository)
        }
    }
}

fun CreationExtras.inventoryApplication(): MainApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
