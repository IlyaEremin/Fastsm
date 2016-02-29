package com.ilyaeremin.fastms

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import flow.Flow
import java.util.*

/**
 * Created by Ilya Eremin on 12.02.2016.
 */
class ListView(context: Context?, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Dexter.checkPermission(object : PermissionListener {
            override fun onPermissionGranted(respone: PermissionGrantedResponse?) {
                fillSms()
            }

            override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?,
                                                            p1: PermissionToken?) {
                throw UnsupportedOperationException()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                throw UnsupportedOperationException()
            }
        }, Manifest.permission.READ_SMS);
    }

    fun fillSms(){
        val cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        val items = ArrayList<String>()

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                items.add(cursor.getString(cursor.getColumnIndexOrThrow("body")).toString())
            } while (cursor.moveToNext());
            cursor.close()
        }
        layoutManager = LinearLayoutManager(context)
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
        itemView.setOnClickListener { Flow.get(itemView).set(DetailsScreen(str)) }
    }
}
