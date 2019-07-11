package com.nyt.mostpopular

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by dastaniqbal on 11/07/2019.
 * 11/07/2019 4:18
 */
class JsonDeserilizeTest {
    private val TAG = this::class.java.simpleName
    val inputType1 =
        "{\"des_facet\":[\"AIRLINES AND AIRPLANES\",\"BLACKS\",\"DISCRIMINATION\",\"RACE AND ETHNICITY\"],\"org_facet\":[\"WOMEN AND GIRLS\",\"FLIGHT ATTENDANTS\",\"NATIONAL ASSN FOR THE ADVANCEMENT OF COLORED PEOPLE\",\"AMERICAN AIRLINES\"],\"per_facet\":[\"JOHNSON, DERRICK (1968- )\"],\"geo_facet\":[\"HOUSTON (TEX)\",\"JAMAICA (WEST INDIES)\",\"MIAMI (FLA)\"]}"

    val inputType2 =
        "{\"des_facet\":[\"AIRLINES AND AIRPLANES\",\"BLACKS\",\"DISCRIMINATION\",\"RACE AND ETHNICITY\"],\"org_facet\":[\"WOMEN AND GIRLS\",\"FLIGHT ATTENDANTS\",\"NATIONAL ASSN FOR THE ADVANCEMENT OF COLORED PEOPLE\",\"AMERICAN AIRLINES\"],\"per_facet\":[\"JOHNSON, DERRICK (1968- )\"],\"geo_facet\":\"\"}"

    var jsonCorrect = "{\"des_facet\":[\"hey!\"]}"
    var jsonBroken = "{\"des_facet\":\"hey!\"}"

    @Test
    fun brokenJson() {
        val response = extract(jsonBroken)
        assertTrue(response.des_facet!![0] == "hey!")
    }

    @Test
    fun type2Response_hasPercentType1Items() {
        val response = extract(jsonCorrect)
        assertTrue(response.des_facet!![0] == "hey!")
    }

    private val gson: Gson = GsonBuilder()
        .create()

    private fun extract(input: String): Res {
        return gson.fromJson(input, Res::class.java)
    }

    private fun extractArray(input: String): List<Res> {
        val type = object : TypeToken<List<Res>>() {}.type
        return gson.fromJson(input, type)
    }

}