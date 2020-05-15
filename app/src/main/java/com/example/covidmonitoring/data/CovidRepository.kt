package com.example.covidmonitoring.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.coroutines.CoroutineContext

class CovidRepository: CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    private val covidApi = Retrofit.Builder()
        .baseUrl(CovidAPI.HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CovidAPI::class.java)

    fun getSummary() = async {
        covidApi.getSummary()
            .execute()
            .body()
    }

    fun getRussiaLiveData() = async {
        covidApi.getByCountryLive("russia")
            .execute()
            .body()
            ?.last()
    }

    fun getUKLiveData() = async {
        covidApi.getByCountryLive("united-kingdom")
            .execute()
            .body()
            ?.last()
    }

    fun getSpainLiveData() = async {
        covidApi.getByCountryLive("spain")
            .execute()
            .body()
            ?.last()
    }
}