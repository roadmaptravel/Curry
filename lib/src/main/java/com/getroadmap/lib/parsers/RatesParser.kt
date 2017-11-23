package com.getroadmap.lib.parsers

import com.getroadmap.lib.models.Rates
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


/**
 * Created by jan on 21/11/2017.
 * convert json to a map with currencies and their corresponding rate
 */
class RatesParser : JsonDeserializer<Rates> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Rates {
        val gson = Gson()
        var map: Map<String, Double> = HashMap()
        map = gson.fromJson(json, map.javaClass) as Map<String, Double>
        return Rates(map)
    }
}