package com.ilyaeremin.fastms

import android.os.Parcel
import android.os.Parcelable
import com.ilyaeremin.fastms.utils.creator

/**
 * Created by Ilya Eremin on 12.02.2016.
 */
data class DetailsScreen(val id: String) : Parcelable {
    companion object {
        @JvmField val CREATOR = creator { DetailsScreen(it.readString()) }
    }

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int): Unit = dest.writeString(this.id)

    override fun equals(other: Any?): Boolean {
        return other != null && other is DetailsScreen && other.id.equals(id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
