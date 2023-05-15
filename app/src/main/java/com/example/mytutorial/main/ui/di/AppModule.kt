package com.example.mytutorial.main.ui.di

import android.content.Context
import androidx.room.Room
import com.example.mytutorial.main.ui.db.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): FoodDatabase {
        return Room.databaseBuilder(
            app, FoodDatabase::class.java, "food_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: FoodDatabase) = db.foodDao

}