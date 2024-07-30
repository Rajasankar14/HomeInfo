package com.example.myapplication.Compose.NewPurchase

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HomeProductDatabase
import com.example.myapplication.newPurchase.NewPurchaseProduct
import com.example.myapplication.newPurchase.db.NewProductPurchaseDao
import com.example.myapplication.newPurchase.rep.NewPurchaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewPurchaseViewModel(val context: Context, application: Application) : ViewModel() {
    private val newPurchaseRepository : NewPurchaseRepository

    private var  newProductPurchaseDao : NewProductPurchaseDao =
        HomeProductDatabase.getDatabase(application).getNewPurchaseDao()

     private val _eventFinishScreen = MutableStateFlow(false)
     val triggerFinishNewDesignScreen : StateFlow<Boolean>  get() = _eventFinishScreen
    init {
        newPurchaseRepository =NewPurchaseRepository(newProductPurchaseDao)
    }

    fun addNewPurchaseProduct(newPurchaseProduct: NewPurchaseProduct) = viewModelScope.launch(
        Dispatchers.IO) {
        try {
            newPurchaseRepository.insertPurchaseProduct(newPurchaseProduct)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Insertion successful", Toast.LENGTH_SHORT).show()
                _eventFinishScreen.value = true

            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Insertion failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun resetFinishScreenValue() {
        _eventFinishScreen.value = false
    }
}

class NewPurchaseViewModelFactory(private var context : Context, var application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(NewPurchaseViewModel::class.java)){
           return NewPurchaseViewModel(context,application) as T
       }
       throw  IllegalArgumentException("unknown view model class")
    }

}



enum class NewPurchaseEntryType{
     PRODUCT_NAME,
     PRODUCT_QTY,
    PRODUCT_PRICE,
    PRODUCT_CATEGORY,
    PRODUCT_PURCHASED_BY,
    PRODUCT_PURCHASE_DATE,
    PRODUCT_EXPIRY_DATE
}