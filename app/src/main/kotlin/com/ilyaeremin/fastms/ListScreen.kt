package com.ilyaeremin.fastms

import android.os.Parcel
import android.os.Parcelable
import com.ilyaeremin.fastms.utils.creator

/**
 * Created by Ilya Eremin on 12.02.2016.
 */

object ListScreen : Parcelable {

    @JvmField val CREATOR = creator { this }
    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel?, flags: Int) = Unit

    override fun equals(other: Any?): Boolean {
        return other != null && other is ListScreen
    }

}
