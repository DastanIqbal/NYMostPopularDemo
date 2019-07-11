package com.nyt.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

open class ResultB : Parcelable {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("adx_keywords")
    @Expose
    var adxKeywords: String? = null
    @SerializedName("column")
    @Expose
    var column: Any? = null
    @SerializedName("section")
    @Expose
    var section: String? = null
    @SerializedName("byline")
    @Expose
    var byline: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("abstract")
    @Expose
    var abstract: String? = null
    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("id")
    @Expose
    var id: Long? = null
    @SerializedName("asset_id")
    @Expose
    var assetId: Long? = null
    @SerializedName("views")
    @Expose
    var views: Long? = null
    @SerializedName("des_facet")
    @Expose
    var desFacet: Any? = null
    @SerializedName("org_facet")
    @Expose
    var orgFacet: Any? = null
    @SerializedName("per_facet")
    @Expose
    var perFacet: Any? = null
    @SerializedName("geo_facet")
    @Expose
    var geoFacet: Any? = null
    @SerializedName("media")
    @Expose
    var media: List<MediumB> = emptyList()
    @SerializedName("uri")
    @Expose
    var uri: String? = null

    constructor(`in`: Parcel) {
        this.url = `in`.readValue(String::class.java.classLoader) as String
        this.adxKeywords = `in`.readValue(String::class.java.classLoader) as String
        this.column = `in`.readValue(Any::class.java.classLoader) as Any
        this.section = `in`.readValue(String::class.java.classLoader) as String
        this.byline = `in`.readValue(String::class.java.classLoader) as String
        this.type = `in`.readValue(String::class.java.classLoader) as String
        this.title = `in`.readValue(String::class.java.classLoader) as String
        this.abstract = `in`.readValue(String::class.java.classLoader) as String
        this.publishedDate = `in`.readValue(String::class.java.classLoader) as String
        this.source = `in`.readValue(String::class.java.classLoader) as String
        this.id = `in`.readValue(Long::class.java.classLoader) as Long
        this.assetId = `in`.readValue(Long::class.java.classLoader) as Long
        this.views = `in`.readValue(Long::class.java.classLoader) as Long
        //`in`.readList(this.desFacet!!, String::class.java.classLoader)
       // `in`.readList(this.orgFacet!!, String::class.java.classLoader)
       // `in`.readList(this.perFacet!!, String::class.java.classLoader)
        // `in`.readList(this.geoFacet!!, String::class.java.classLoader)
        `in`.readList(this.media!!, MediumB::class.java.classLoader)
        this.uri = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor() {}

    override fun toString(): String {
        return ToStringBuilder(this)
            .append("url", url)
            .append("adxKeywords", adxKeywords)
            .append("column", column)
            .append("section", section)
            .append("byline", byline)
            .append("type", type)
            .append("title", title)
            .append("_abstract", abstract)
            .append("publishedDate", publishedDate)
            .append("source", source)
            .append("id", id)
            .append("assetId", assetId)
            .append("views", views)
            .append("desFacet", desFacet)
            .append("orgFacet", orgFacet)
            .append("perFacet", perFacet)
            .append("geoFacet", geoFacet)
            .append("media", media)
            .append("uri", uri)
            .toString()
    }

    override fun hashCode(): Int {
        return HashCodeBuilder()
            .append(adxKeywords)
            .append(perFacet)
            .append(orgFacet)
            .append(geoFacet)
            .append(desFacet)
            .append(type)
            .append(uri)
            .append(section)
            .append(url).append(id)
            .append(title).append(byline)
            .append(assetId)
            .append(source)
            .append(views)
            .append(column)
            .append(abstract)
            .append(publishedDate)
            .append(media)
            .toHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is ResultB) {
            return false
        }
        val rhs = other as ResultB?
        return EqualsBuilder()
            .append(adxKeywords, rhs?.adxKeywords)
            .append(perFacet, rhs?.perFacet)
            .append(orgFacet, rhs?.orgFacet)
            .append(geoFacet, rhs?.geoFacet)
            .append(desFacet, rhs?.desFacet)
            .append(type, rhs?.type)
            .append(uri, rhs?.uri)
            .append(section, rhs?.section)
            .append(url, rhs?.url)
            .append(id, rhs?.id)
            .append(title, rhs?.title)
            .append(byline, rhs?.byline)
            .append(assetId, rhs?.assetId)
            .append(source, rhs?.source)
            .append(views, rhs?.views)
            .append(column, rhs?.column)
            .append(abstract, rhs?.abstract)
            .append(publishedDate, rhs?.publishedDate)
            .append(media, rhs?.media)
            .isEquals
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(url)
        dest.writeValue(adxKeywords)
        dest.writeValue(column)
        dest.writeValue(section)
        dest.writeValue(byline)
        dest.writeValue(type)
        dest.writeValue(title)
        dest.writeValue(abstract)
        dest.writeValue(publishedDate)
        dest.writeValue(source)
        dest.writeValue(id)
        dest.writeValue(assetId)
        dest.writeValue(views)
       // dest.writeList(desFacet)
       // dest.writeList(orgFacet)
       // dest.writeList(perFacet)
        //   dest.writeList(geoFacet)
        dest.writeList(media)
        dest.writeValue(uri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultB> {
        override fun createFromParcel(parcel: Parcel): ResultB {
            return ResultB(parcel)
        }

        override fun newArray(size: Int): Array<ResultB?> {
            return arrayOfNulls(size)
        }
    }

}
