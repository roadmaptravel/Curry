package com.getroadmap.lib.parsers

import android.util.Log
import com.getroadmap.lib.models.Rates
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Created by jan on 21/11/2017.
 */
class RatesParser : JsonDeserializer<Rates> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Rates {
        //TODO convert json to a hashmap with currencies and their corresponding rate
        Log.d("DEBUG", json.toString())
        return Rates()
    }
}