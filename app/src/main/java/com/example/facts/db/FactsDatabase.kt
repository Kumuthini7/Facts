package com.example.facts.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.facts.App
import com.example.facts.dao.FactsDao
import com.example.facts.entity.Facts
import com.example.facts.entity.RowsItem

/**
 * Created by Kumuthini.N on 08-08-2020
 */
@Database(entities = arrayOf(Facts::class, RowsItem::class), version = 1, exportSchema = false)
abstract class FactsDatabase : RoomDatabase() {

    abstract val factsDao: FactsDao

    companion object {
        val db_instance: FactsDatabase by lazy {
            Room.databaseBuilder(App.app, FactsDatabase::class.java, "facts.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
