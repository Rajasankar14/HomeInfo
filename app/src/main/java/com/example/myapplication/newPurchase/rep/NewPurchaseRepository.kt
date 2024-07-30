package com.example.myapplication.newPurchase.rep

import androidx.lifecycle.LiveData
import com.example.myapplication.newPurchase.NewPurchaseProduct
import com.example.myapplication.newPurchase.db.NewProductPurchaseDao
import kotlinx.coroutines.flow.Flow

class NewPurchaseRepository(private val newProductPurchaseDao: NewProductPurchaseDao) {


//    val getAllPurchaseProducts: List<NewPurchaseProduct> = newProductPurchaseDao.getAllPurchases()

    fun insertPurchaseProduct(newPurchaseProduct: NewPurchaseProduct){
          newProductPurchaseDao.insert(newPurchaseProduct)
    }

    fun getAllPurchaseProducts(): Flow<List<NewPurchaseProduct>> {
        return newProductPurchaseDao.getAllPurchases()
    }

    fun getTotalPurchases() : NewProductPurchaseDao.TotalPurchases {
        return newProductPurchaseDao.getTotalPurchases()
    }
}