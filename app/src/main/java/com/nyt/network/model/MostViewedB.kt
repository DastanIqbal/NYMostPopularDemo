package com.nyt.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class MostViewedB : Parcelable {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<ResultB> = emptyList()

    constructor(`in`: Parcel) {
        this.status = `in`.readValue(String::class.java.classLoader) as String
        this.copyright = `in`.readValue(String::class.java.classLoader) as String
        this.numResults = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.results, ResultB::class.java.classLoader)
    }

    constructor() {}

    override fun toString(): String {
        return ToStringBuilder(this)
            .append("status", status)
            .append("copyright", copyright)
            .append("numResults", numResults)
            .append("results", results)
            .toString()
    }

    override fun hashCode(): Int {
        return HashCodeBuilder()
            .append(results)
            .append(status)
            .append(numResults)
            .append(copyright).toHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is MostViewedB) {
            return false
        }
        val rhs = other as MostViewedB?
        return EqualsBuilder()
            .append(results, rhs?.results)
            .append(status, rhs?.status)
            .append(numResults, rhs?.numResults)
            .append(copyright, rhs?.copyright)
            .isEquals
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(status)
        dest.writeValue(copyright)
        dest.writeValue(numResults)
        dest.writeList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MostViewedB> {
        override fun createFromParcel(parcel: Parcel): MostViewedB {
            return MostViewedB(parcel)
        }

        override fun newArray(size: Int): Array<MostViewedB?> {
            return arrayOfNulls(size)
        }
    }

}
