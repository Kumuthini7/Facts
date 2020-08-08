package com.example.facts.repo

import androidx.lifecycle.LiveData
import com.example.facts.dao.FactsDao
import com.example.facts.entity.Facts
import com.example.facts.entity.FactsRelations
import com.example.facts.entity.RowsItem
import com.example.facts.api.Api
import com.example.facts.network.ApiResponse
import com.example.facts.network.NetworkBoundResource
import com.example.facts.network.Resource
import javax.inject.Inject

/**
 * Created by Kumuthini.N on 08-08-2020
 */
class MainRepository @Inject constructor(val factsDao: FactsDao, private val api: Api) {

    fun getFacts(): LiveData<Resource<FactsRelations>> {
        return object : NetworkBoundResource<FactsRelations, Facts>() {
            override fun shouldFetch(data: FactsRelations?): Boolean {
                return true
            }

            override fun saveCallResult(item: Facts) {
                factsDao.deleteAllFacts()
                factsDao.deleteAllRowsItem()
                item.id = 1
                item.rows?.indices?.forEach { i ->
                    item.rows?.let { it[i]?.factsId =1 }
                }
                factsDao.insertRowsItem(item.rows as List<RowsItem>)
                factsDao.insertFacts(item)
            }


            override fun loadFromDb(): LiveData<FactsRelations> {
                return factsDao.getFacts()
            }

            override fun createCall(): LiveData<ApiResponse<Facts>> {
                return api.getFacts()
            }
        }.asLiveData()
    }
}
