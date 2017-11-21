package com.getroadmap.lib.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by jan on 21/11/2017.
 */
data class ForExRates(
        @SerializedName("base") @Expose val base: String,
        @SerializedName("date") @Expose val date: String,
        @SerializedName("rates") @Expose val rates: Rates) {
}