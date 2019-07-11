package com.nyt.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class MediumB : Parcelable {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("subtype")
    @Expose
    var subtype: String? = null
    @SerializedName("caption")
    @Expose
    var caption: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("approved_for_syndication")
    @Expose
    var approvedForSyndication: Int? = null
    @SerializedName("media-metadata")
    @Expose
    var mediaMetadata: List<MediaMetadatumB>? = null

    constructor(`in`: Parcel) {
        this.type = `in`.readValue(String::class.java.classLoader) as String
        this.subtype = `in`.readValue(String::class.java.classLoader) as String
        this.caption = `in`.readValue(String::class.java.classLoader) as String
        this.copyright = `in`.readValue(String::class.java.classLoader) as String
        this.approvedForSyndication = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.mediaMetadata!!, MediaMetadatumB::class.java.classLoader)
    }

    constructor() {}

    override fun toString(): String {
        return ToStringBuilder(this)
            .append("type", type)
            .append("subtype", subtype)
            .append("caption", caption)
            .append("copyright", copyright)
            .append("approvedForSyndication", approvedForSyndication)
            .append("mediaMetadata", mediaMetadata)
            .toString()
    }

    override fun hashCode(): Int {
        return HashCodeBuilder()
            .append(subtype)
            .append(mediaMetadata)
            .append(caption)
            .append(copyright)
            .append(type)
            .append(approvedForSyndication)
            .toHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is MediumB) {
            return false
        }
        val rhs = other as MediumB?
        return EqualsBuilder()
            .append(subtype, rhs?.subtype)
            .append(mediaMetadata, rhs?.mediaMetadata)
            .append(caption, rhs?.caption)
            .append(copyright, rhs?.copyright)
            .append(type, rhs?.type)
            .append(approvedForSyndication, rhs?.approvedForSyndication)
            .isEquals
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(type)
        dest.writeValue(subtype)
        dest.writeValue(caption)
        dest.writeValue(copyright)
        dest.writeValue(approvedForSyndication)
        dest.writeList(mediaMetadata)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediumB> {
        override fun createFromParcel(`in`: Parcel): MediumB {
            return MediumB(`in`)
        }

        override fun newArray(size: Int): Array<MediumB?> {
            return arrayOfNulls(size)
        }

    }
}
