package com.example.covidmonitoring.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidAPI {

    companion object {
        const val HOST = "https://api.covid19api.com/"
    }

    @GET("summary")
    fun getSummary(): Call<Summary>

    @GET("country/{countryName}/status/confirmed")
    fun getByCountryLive(@Path("countryName") countryName: String): Call<List<CountryCases>>

}