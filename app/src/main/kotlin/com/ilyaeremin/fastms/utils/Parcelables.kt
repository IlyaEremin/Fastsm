package com.ilyaeremin.fastms.utils

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Ilya Eremin on 12.02.2016.
 */

inline fun <reified T : Parcelable> creator(crossinline f: (Parcel) -> T) =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T = f(source)
            override fun newArray(size: Int): Array<T?> = arrayOfNulls(size)
        }

