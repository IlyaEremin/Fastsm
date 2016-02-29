package com.ilyaeremin.fastms

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import flow.Flow

/**
 * Created by Ilya Eremin on 20.02.2016.
 */
class DetailsView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val detailScreen : DetailsScreen = Flow.getKey(this)!!
        (findViewById(R.id.text) as TextView).setText(detailScreen.id)
    }

}