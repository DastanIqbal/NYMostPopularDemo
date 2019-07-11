package com.nyt.mostpopular

import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

class Res {
    @JsonAdapter(ListFromObjectAdapter::class)
    @SerializedName("des_facet")
    @Expose
    var des_facet: List<String>? = null

    @JsonAdapter(ListFromObjectAdapter::class)
    @SerializedName("org_facet")
    @Expose
    var orgFacet: List<String>? = null

    @JsonAdapter(ListFromObjectAdapter::class)
    @SerializedName("per_facet")
    @Expose
    var perFacet: List<String>? = null

    @JsonAdapter(ListFromObjectAdapter::class)
    @SerializedName("geo_facet")
    @Expose
    var geoFacet: List<String>? = null

}