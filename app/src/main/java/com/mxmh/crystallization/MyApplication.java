package com.mxmh.crystallization;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.mxmh.crystallization.utils.ImageUtils;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initData();
    }

    private void initData() {
        queue = Volley.newRequestQueue(this);
        ImageUtils.initImageLoader(this);
    }

    public static synchronized MyApplication getInstance() {
        return myApplication;
    }


    public static RequestQueue getRequestQuery(Context context) {

        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }
        return queue;
    }

}
