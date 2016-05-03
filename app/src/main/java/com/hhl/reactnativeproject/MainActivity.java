package com.hhl.reactnativeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hhl.rn.BuildConfig;
import com.hhl.rn.RNTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("BuildConfig类型：", BuildConfig.DEBUG ? "Library是debug" : "Library不是debug");
    }

    public void clickMe(View v) {
        Intent intent = new Intent(MainActivity.this, RNTestActivity.class);
        startActivity(intent);
        finish();
    }
}
