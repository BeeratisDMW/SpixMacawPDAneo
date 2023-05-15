package com.example.mytutorial.main.ui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Food::class, CartItem::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FoodDatabase: RoomDatabase() {

    abstract val foodDao: FoodDao


    companion object {

        @Volatile
        private var INSTANCE: FoodDatabase? = null


        fun getDatabase(context: Context): FoodDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java,
                        "food_database"
                    ).build()
//                INSTANCE = instance

                }
                return instance

            }
        }
    }
}





