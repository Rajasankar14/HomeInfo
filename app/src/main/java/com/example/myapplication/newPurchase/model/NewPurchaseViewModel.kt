package com.example.myapplication.newPurchase.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HomeProductDatabase
import com.example.myapplication.newPurchase.NewPurchaseProduct
import com.example.myapplication.newPurchase.rep.NewPurchaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPurchaseViewModel(application: Application) : AndroidViewModel(application) {

   private val newPurchaseRepository : NewPurchaseRepository

   init {
     val newProductPurchaseDao = HomeProductDatabase.getDatabase(application).getNewPurchaseDao()
       newPurchaseRepository =NewPurchaseRepository(newProductPurchaseDao)
   }

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    fun addNewPurchaseProduct(newPurchaseProduct: NewPurchaseProduct) = viewModelScope.launch(Dispatchers.IO) {
        newPurchaseRepository.insertPurchaseProduct(newPurchaseProduct)
    }
}