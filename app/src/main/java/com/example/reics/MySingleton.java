package com.example.reics;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class MySingleton {
    private static MySingleton ourInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    public static synchronized MySingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MySingleton(context);
        }
        return ourInstance;
    }

    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = new Volley().newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
