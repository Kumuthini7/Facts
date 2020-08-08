package com.example.facts.api

import androidx.lifecycle.LiveData
import com.example.facts.entity.Facts
import com.example.facts.network.ApiResponse
import retrofit2.http.GET

/**
 * Created by Kumuthini.N on 08-08-2020
 */
interface Api {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFacts(): LiveData<ApiResponse<Facts>>

}
