package com.example.myapplication.newPurchase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewPurchaseProduct")
class NewPurchaseProduct
    (@ColumnInfo(name = "ProductName")val productName :String,

     @ColumnInfo(name = "CategoryName") val categoryName :String,
     @ColumnInfo(name = "PurchaseDate")val purchaseDate :String,
     @ColumnInfo(name = "ExpiryDate")val expiryDate :String,
     @ColumnInfo(name = "PurchaseQty")val purchaseQty :String,
     @ColumnInfo(name = "PurchasedPerson")val purchasedPerson :String,
     @ColumnInfo(name = "PurchaseAmount")val purchaseAmount :String)

{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
