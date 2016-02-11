package com.ilyaeremin.fastms

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

/**
 * Created by Ilya Eremin on 12.02.2016.
 */
class ListView(context: Context?, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        layoutManager = LinearLayoutManager(context)
        val items = ArrayList<String>()
        items.add("first")
        items.add("second")
        items.add("third")
        this.adapter = SmsListAdapter(items)
    }

}

class SmsListAdapter(val array: ArrayList<String>) : RecyclerView.Adapter<SmsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsHolder? {
        return SmsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sms, parent, false))
    }

    override fun onBindViewHolder(holder: SmsHolder, position: Int) {
        holder.draw(array[position])
    }

    override fun getItemCount() = array.size

}

class SmsHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun draw(str: String){
        (itemView as TextView).text = str
    }
}
