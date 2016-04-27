package com.hhl.rn;

import android.app.Application;

/**
 * Created by HanHailong on 16/4/27.
 */
public class RNApplication extends Application {

    private static RNApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static RNApplication getInstance() {
        return mInstance;
    }
}
