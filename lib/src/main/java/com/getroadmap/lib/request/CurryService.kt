package com.getroadmap.lib.request

import com.getroadmap.lib.models.ForExRates

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jan on 21/11/17.
 */

interface CurryService {
    @GET("latest")
    fun getLatest(@Query("base") currencyCode: String = "EUR"): Single<ForExRates>
}
