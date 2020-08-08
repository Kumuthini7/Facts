package com.example.facts.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.facts.entity.Facts
import com.example.facts.entity.FactsRelations
import com.example.facts.entity.RowsItem

/**
 * Created by Kumuthini.N on 08-08-2020
 */
@Dao
interface FactsDao {

    @Query("SELECT * from Facts")
    fun getFacts(): LiveData<FactsRelations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFacts(facts: Facts)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRowsItem(facts: List<RowsItem>)

    @Query("DELETE FROM Facts")
    fun deleteAllFacts()
    
    @Query("DELETE FROM RowsItem")
    fun deleteAllRowsItem()
}


