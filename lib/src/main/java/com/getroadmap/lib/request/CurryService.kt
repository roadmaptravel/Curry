package com.getroadmap.lib.request

import com.getroadmap.lib.models.ForExRates

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by jan on 21/11/17.
 *
 * for documentation: see http://fixer.io/
 */

interface CurryService {
    /**
     * Rates are quoted against the Euro by default.
     * Quote against a different currency by setting the base parameter in your request.
     * https://api.fixer.io/latest?base=EUR
     */
    @GET("latest")
    fun getLatestRates(@Query("base") currencyCode: String = "EUR"): Single<ForExRates>

    /**
     * Get historical rates for any day since 1999.
     * https://api.fixer.io/2000-01-03
     */
    @GET("{date}")
    fun getHistoricalRates(@Path("date") date: String): Single<ForExRates>


    /**
     * Request specific exchange rates by setting the symbols parameter.
     * https://api.fixer.io/latest?symbols=USD,GBP
     */
    @GET("latest")
    fun getSpecificRates(@Query("symbols") symbols: String): Single<ForExRates>
}
