/*
package com.nyt.mostpopular

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

open class ResDeserializer : JsonDeserializer<GeoFact> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): GeoFact {
        checkNotNull(json)
        checkNotNull(typeOfT)
        checkNotNull(context)

        val jsonObject = json.asJsonObject

        val type = jsonObject.get("geo_facet")

        return if (type is JsonArray) {
            val values = type.asJsonArray.toMutableList()
            GeoFact(values as List<String>)
        } else {
            GeoFact(emptyList())
        }
    }
}*/
