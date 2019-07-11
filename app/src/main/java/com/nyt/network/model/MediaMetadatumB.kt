package com.nyt.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class MediaMetadatumB : Parcelable {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("format")
    @Expose
    var format: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

    constructor(`in`: Parcel) {
        this.url = `in`.readValue(String::class.java.classLoader) as String
        this.format = `in`.readValue(String::class.java.classLoader) as String
        this.height = `in`.readValue(Int::class.java.classLoader) as Int
        this.width = `in`.readValue(Int::class.java.classLoader) as Int
    }

    constructor() {}

    override fun toString(): String {
        return ToStringBuilder(this)
            .append("url", url)
            .append("format", format)
            .append("height", height)
            .append("width", width).toString()
    }

    override fun hashCode(): Int {
        return HashCodeBuilder().append(height).append(width).append(format).append(url).toHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is MediaMetadatumB) {
            return false
        }
        val rhs = other as MediaMetadatumB?
        return EqualsBuilder()
            .append(height, rhs?.height)
            .append(width, rhs?.width)
            .append(format, rhs?.format)
            .append(url, rhs?.url).isEquals
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(url)
        dest.writeValue(format)
        dest.writeValue(height)
        dest.writeValue(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaMetadatumB> {
        override fun createFromParcel(parcel: Parcel): MediaMetadatumB {
            return MediaMetadatumB(parcel)
        }

        override fun newArray(size: Int): Array<MediaMetadatumB?> {
            return arrayOfNulls(size)
        }
    }
}
