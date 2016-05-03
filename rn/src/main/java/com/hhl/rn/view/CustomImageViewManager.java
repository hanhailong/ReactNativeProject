package com.hhl.rn.view;

import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.hhl.rn.RNApplication;

/**
 * Created by HanHailong on 16/5/3.
 */
public class CustomImageViewManager extends SimpleViewManager<ImageView> {

    private static final String NAME = "CustomImageView";

    /**
     * @return the name of this view manager. This will be the name used to reference this view
     * manager from JavaScript in createReactNativeComponentClass.
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Subclasses should return a new View instance of the proper type.
     *
     * @param reactContext
     */
    @Override
    protected ImageView createViewInstance(ThemedReactContext reactContext) {
        return new ImageView(reactContext);
    }

    /**
     * JS调用Native的方法
     *
     * @param imageView
     * @param toast
     */
    @ReactProp(name = "imageToast")
    public void setImageToast(ImageView imageView, String toast) {
        Toast.makeText(RNApplication.getInstance(), toast, Toast.LENGTH_SHORT).show();
    }
}
