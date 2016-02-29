package com.ilyaeremin.fastms

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import flow.*

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        val baseContext = Flow
                .configure(newBase, this)
                .dispatcher(KeyDispatcher.configure(this, Changer(this)).build())
                .install()
        super.attachBaseContext(baseContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            Flow.get(this).set(ListScreenKt())
        }
    }

    override fun onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }

}

class Changer(val activity: MainActivity) : KeyChanger() {

    override fun changeKey(outgoingState: State?, incomingState: State,
                           direction: Direction, incomingContexts: MutableMap<Any, Context>,
                           callback: TraversalCallback) {

        val key = incomingState.getKey<Any>();
        val context = incomingContexts.get(key);

        @LayoutRes val layout: Int;
        when (key) {
            is ListScreenKt -> layout = R.layout.screen_list
            is DetailsScreen -> layout = R.layout.screen_details
            else -> throw AssertionError("Unrecognized screen " + key)
        }
        val frame = activity.findViewById(R.id.container) as ViewGroup;
        val incomingView = LayoutInflater.from(context).inflate(layout, frame, false);
        frame.addView(incomingView);
        callback.onTraversalCompleted();
    }
}

