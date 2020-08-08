package com.example.facts.dagger

import com.example.facts.App
import com.example.facts.api.Api
import com.example.facts.network.LiveDataCallAdapterFactory
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Kumuthini.N on 08-08-2020
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): LiveDataCallAdapterFactory = LiveDataCallAdapterFactory()

    @Provides
    @Singleton
    fun providesRetrofit(liveDataCallAdapterFactory: LiveDataCallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dl.dropboxusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(liveDataCallAdapterFactory)

            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun providePicasso(app: App): Picasso = Picasso.Builder(app).build()

}


