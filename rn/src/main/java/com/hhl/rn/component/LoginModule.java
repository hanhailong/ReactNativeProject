package com.hhl.rn.component;

import android.os.Handler;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by HanHailong on 16/5/3.
 */
public class LoginModule extends ReactContextBaseJavaModule {

    private static final String NAME = "LoginModule";

    public LoginModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from javascript.
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * 登录系统
     *
     * @param name            姓名
     * @param password        密码
     * @param successCallback 登录成功回调
     * @param errorCallback   登录异常回调
     */
    @ReactMethod
    public void loginSystem(final String name, final String password, final Callback successCallback, final Callback errorCallback) {
        //模拟登录成功和失败场景
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("admin".equals(name) && "1234".equals(password)) {
                    successCallback.invoke("登录成功");
                } else {
                    errorCallback.invoke("调用失败");
                }
            }
        }, 2000);
    }
}
