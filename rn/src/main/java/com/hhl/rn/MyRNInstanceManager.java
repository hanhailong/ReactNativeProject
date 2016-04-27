package com.hhl.rn;

import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.shell.MainReactPackage;

/**
 * Created by HanHailong on 16/4/27.
 */
public class MyRNInstanceManager {

    private MyRNInstanceManager() {
    }

    /**
     * 返回{@link MyRNInstanceManager}单例
     *
     * @return
     */
    public static ReactInstanceManager getInstance() {
        return Holder.mInstance;
    }

    private static class Holder {
        private static final ReactInstanceManager mInstance = ReactInstanceManager.builder()
                .setApplication(RNApplication.getInstance())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
    }
}
