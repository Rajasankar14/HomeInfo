package com.example.myapplication.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.newPurchase.NewPurchaseProduct
import com.example.myapplication.newPurchase.db.NewProductPurchaseDao
import java.util.concurrent.Executors


@Database(entities = [NewPurchaseProduct::class], version = 1, exportSchema = true)
abstract class HomeProductDatabase : RoomDatabase() {

    abstract fun getNewPurchaseDao() :NewProductPurchaseDao

    companion object{
        @Volatile
        private var INSTANCE: HomeProductDatabase? = null

        fun getDatabase(context: Context): HomeProductDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HomeProductDatabase::class.java,
                    "Home_database2"
                ).setQueryCallback(RoomDatabase.QueryCallback{sqlQuery, bindArgs ->
                    println("Print SQL Query: $sqlQuery SQL Args: $bindArgs")
                }, Executors.newSingleThreadExecutor()).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}