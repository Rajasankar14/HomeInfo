package com.example.myapplication.newPurchase.db

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.newPurchase.NewPurchaseProduct
import kotlinx.coroutines.flow.Flow
import java.time.temporal.TemporalAmount

@Dao
interface NewProductPurchaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(newPurchaseProduct :NewPurchaseProduct)

    @Update
    fun update(newPurchaseProduct: NewPurchaseProduct)

    @Delete
    fun delete(newPurchaseProduct: NewPurchaseProduct)

    @Query("Select * from newPurchaseProduct order by id ASC")
    fun getAllPurchases(): Flow<List<NewPurchaseProduct>>

    @Query("SELECT sum(PurchaseQty) as totalPurchaseQty, sum(PurchaseAmount) as totalPurchaseAmount from NewPurchaseProduct")
    fun getTotalPurchases(): TotalPurchases


    data class TotalPurchases(
        val totalPurchaseQty: Int,
        val totalPurchaseAmount: Int
    )

}