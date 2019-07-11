package com.nyt.mostpopular.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by dastaniqbal on 11/07/2019.
 * 11/07/2019 5:09
 */
data class CanStringList(
    @SerializedName("geo_facet")
    @Expose
    var geoFacet: List<String>? = null,
    @SerializedName("geo_facet")
    @Expose
    var geoFacet1: String? = null
)