package com.example.myapplication.Compose.Home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HomeProductDatabase
import com.example.myapplication.newPurchase.rep.NewPurchaseRepository
import java.lang.Appendable
import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.newPurchase.NewPurchaseProduct
import com.example.myapplication.newPurchase.db.NewProductPurchaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel(application : Application) : AndroidViewModel(application) {

    private val newPurchaseRepository : NewPurchaseRepository

    private var  newProductPurchaseDao : NewProductPurchaseDao =
        HomeProductDatabase.getDatabase(application).getNewPurchaseDao()

     val _prchaseListData = MutableLiveData<List<NewPurchaseProduct>>()

    val  prchaseListData1 :  LiveData<List<NewPurchaseProduct>>  get() = _prchaseListData



      val totalPurchasesCount : MutableLiveData<NewProductPurchaseDao.TotalPurchases> get() =  _totalPurchaseCount

    private val _totalPurchaseCount  = MutableLiveData<NewProductPurchaseDao.TotalPurchases>()





    init {
        newPurchaseRepository =NewPurchaseRepository(newProductPurchaseDao)
    }

    fun addNewPurchaseProduct(newPurchaseProduct: NewPurchaseProduct) = viewModelScope.launch(
        Dispatchers.IO) {
        newPurchaseRepository.insertPurchaseProduct(newPurchaseProduct)
    }

   // val getAllPurchaseProducts: LiveData<List<NewPurchaseProduct>> = newProductPurchaseDao.getAllPurchases()

     /*fun getAllPurchaseProducts() {
         viewModelScope.launch(Dispatchers.IO) {
           // val purchaseddProducts = newPurchaseRepository.getAllPurchaseProducts()

             withContext(Dispatchers.Main){
                 _prchaseListData.value = newPurchaseRepository.getAllPurchaseProducts()
             }


        }
    }*/

   /* fun getAllPurchaseProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val purchasedProducts = newPurchaseRepository.getAllPurchaseProducts()

            // Update MutableLiveData on the main thread
            withContext(Dispatchers.Main) {
                _prchaseListData.value = purchasedProducts            }
        }
    }*/

    private val _items = MutableStateFlow<List<NewPurchaseProduct>>(emptyList())
    val items: StateFlow<List<NewPurchaseProduct>> = _items.asStateFlow()

    fun getAllPurchaseProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            newPurchaseRepository.getAllPurchaseProducts().collect { listOfItems ->
                _items.value = listOfItems
            }
        }
    }

    fun getTotalPurchaseCount(){
        viewModelScope.launch(Dispatchers.IO) {
            val totalPurchaseCount = newPurchaseRepository.getTotalPurchases()
            withContext(Dispatchers.Main){
                _totalPurchaseCount.value = totalPurchaseCount
            }
        }
    }
}