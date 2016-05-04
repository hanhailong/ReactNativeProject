package com.hhl.rn.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.hhl.rn.RNApplication;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
        ImageView imageView = new ImageView(reactContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
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

    /**
     * 设置source，仿{@link com.facebook.react.views.webview.ReactWebViewManager}
     *
     * @param view
     * @param source
     */
    @ReactProp(name = "source")
    public void setSource(final ImageView view, @Nullable ReadableMap source) {
        if (source != null) {
            if (source.hasKey("uri")) {
                String url = source.getString("uri");
                //TODO
                Observable.just(url)
                        .subscribeOn(Schedulers.io())
                        .map(new Func1<String, Bitmap>() {
                            @Override
                            public Bitmap call(String s) {
                                return getBitmapFromNet(s);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Bitmap>() {
                            @Override
                            public void call(Bitmap bitmap) {
                                view.setImageBitmap(bitmap);
                            }
                        });
            }
        }
    }

    /**
     * 网络加载图片
     *
     * @param url
     * @return
     */
    private Bitmap getBitmapFromNet(String url) {
        Bitmap bitmap = null;
        //这里通过OKHttp来下载图片
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            bitmap = BitmapFactory.decodeStream(response.body().byteStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
