package com.ilyaeremin.fastms

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import flow.Flow

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(Flow
                .configure(newBase, this)
                .dispatcher(DefaultDispatcher(this))
                .defaultKey(ListScreen)
                .install())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }
}

