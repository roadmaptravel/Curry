package com.getroadmap.lib.request

import com.getroadmap.lib.models.ForExRates

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by jan on 21/11/17.
 *
 * for documentation: see https://exchangeratesapi.io/
 */

interface CurryService {
    /**
     * Rates are quoted against the Euro by default.
     * Quote against a different currency by setting the base parameter in your request.
     * https://api.exchangeratesapi.io/latest?base=EUR
     */
    @GET("latest")
    fun getLatestRates(@Query("base") currencyCode: String = "EUR"): Single<ForExRates>

    /**
     * Get historical rates for any day since 1999.
     * Rates are quoted against the Euro by default.
     * https://api.exchangeratesapi.io/2000-01-03
     */
    @GET("{date}")
    fun getHistoricalRates(@Path("date") date: String, @Query("base") currencyCode: String = "EUR"): Single<ForExRates>


    /**
     * Request specific exchange rates by setting the symbols parameter.
     * Rates are quoted against the Euro by default.
     * https://api.exchangeratesapi.io/latest?symbols=USD,GBP
     */
    @GET("latest")
    fun getSpecificRates(@Query("symbols") symbols: String, @Query("base") currencyCode: String = "EUR"): Single<ForExRates>
}
