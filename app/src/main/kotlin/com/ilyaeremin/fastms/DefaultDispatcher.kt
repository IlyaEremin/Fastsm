package com.ilyaeremin.fastms

import android.support.annotation.LayoutRes
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import flow.Dispatcher
import flow.History
import flow.Traversal
import flow.TraversalCallback

class DefaultDispatcher(val activity: MainActivity) : Dispatcher {
    override fun dispatch(traversal: Traversal, callback: TraversalCallback) {
        Log.d("BasicDispatcher", "dispatching " + traversal);
        val dest: Any = traversal.destination.top();
        val frame = activity.findViewById(R.id.container) as ViewGroup;
        if (traversal.origin != null) {
            if (frame.getChildCount() > 0) {
                traversal.getState((traversal.origin as History).top()).save(frame.getChildAt(0));
                frame.removeAllViews();
            }
        }
        @LayoutRes val layout: Int;
        if (dest is ListScreenKt) {
            layout = R.layout.screen_list;
        } else if (dest is DetailsScreen) {
            layout = R.layout.screen_details;
        } else {
            throw AssertionError("Unrecognized screen " + dest);
        }

        val incomingView = LayoutInflater.from(traversal.createContext(dest, activity)) //
                .inflate(layout, frame, false);
        frame.addView(incomingView);
        traversal.getState(traversal.destination.top()).restore(incomingView);
        callback.onTraversalCompleted();
    }

}