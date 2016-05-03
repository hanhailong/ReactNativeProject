package com.hhl.rn.component;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by HanHailong on 16/5/3.
 */
public class ToastModule2 extends ReactContextBaseJavaModule {

    private static final String NAME = "ToastAndroid2";
    private static final String DURATION_SHORT_KEY = "short";
    private static final String DURATION_LONG_KEY = "long";

    public ToastModule2(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> map = new HashMap<>();
        map.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        map.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return map;
    }

    /**
     * 提示Toast
     *
     * @param message
     * @param duration
     */
    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }
}
