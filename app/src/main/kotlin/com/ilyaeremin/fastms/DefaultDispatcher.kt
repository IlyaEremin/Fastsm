package com.ilyaeremin.fastms

import android.support.annotation.LayoutRes
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import flow.Flow

class DefaultDispatcher(val activity: MainActivity) : Flow.Dispatcher {

    override fun dispatch(traversal: Flow.Traversal, callback: Flow.TraversalCallback) {
        Log.d("BasicDispatcher", "dispatching " + traversal);
        val dest: Any = traversal.destination.top();
        val frame = activity.findViewById(R.id.container) as ViewGroup;
        if (traversal.origin != null) {
            if (frame.getChildCount() > 0) {
                traversal.origin.topSaveState().save(frame.getChildAt(0));
                frame.removeAllViews();
            }
        }
        @LayoutRes val layout: Int;
        if (dest is ListScreen) {
            layout = R.layout.screen_list;
        } else if (dest is DetailsScreen) {
            layout = R.layout.screen_details;
        } else {
            throw AssertionError("Unrecognized screen " + dest);
        }

        val incomingView = LayoutInflater.from(traversal.createContext(dest, activity)) //
                .inflate(layout, frame, false);
        frame.addView(incomingView);
        traversal.destination.topSaveState().restore(incomingView);
        callback.onTraversalCompleted();
    }

}