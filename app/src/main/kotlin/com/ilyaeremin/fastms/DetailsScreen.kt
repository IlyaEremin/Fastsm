package com.ilyaeremin.fastms

import android.os.Parcel
import android.os.Parcelable
import com.ilyaeremin.fastms.utils.creator

/**
 * Created by Ilya Eremin on 12.02.2016.
 */
data class DetailsScreen(val id: Long) : Parcelable {
    companion object {
        @JvmField val CREATOR = creator { DetailsScreen(it.readLong()) }
    }

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int): Unit = dest.writeLong(this.id)

}
