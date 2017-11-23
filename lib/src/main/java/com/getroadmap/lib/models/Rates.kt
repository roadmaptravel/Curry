package com.getroadmap.lib.models

/**
 * Created by jan on 21/11/2017.
 */
data class Rates(val map: Map<String, Double>) {


    /**
     * return the conversion rate for the given currency code
     * return null if currency code is not found
     * @param currency ISO 4217 currency code
     */
    fun getRate(currency: String): Double? {
        if (map.containsKey(currency)) {
            return map.get(currency)
        }
        return null
    }

    /**
     * return a list with all rates
     */
    fun getRates() : List<Rate> {
        return map.toList().map {
            return@map Rate(it.first, it.second)
        }
    }
}