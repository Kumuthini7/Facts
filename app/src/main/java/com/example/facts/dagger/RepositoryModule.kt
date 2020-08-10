package com.example.facts.dagger

import com.example.facts.dao.FactsDao
import com.example.facts.api.Api
import com.example.facts.db.FactsDatabase
import com.example.facts.repo.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Kumuthini.N on 08-08-2020
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(factsDao: FactsDao, api: Api): MainRepository {
        return MainRepository(factsDao, api)
    }

    @Provides
    @Singleton
    fun provideFactsDao(): FactsDao = FactsDatabase.db_instance.factsDao
}

