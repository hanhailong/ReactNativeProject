package com.hhl.reactnativeproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hhl.rn.BuildConfig;
import com.hhl.rn.RNTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("BuildConfig类型：", BuildConfig.DEBUG ? "Library是debug" : "Library不是debug");

        //TODO
        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button.setOnClickListener(this);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //TODO
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "同意", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clickMe(View v) {

//        RxPermissions.getInstance(this).request(
////                Manifest.permission.CAMERA,
////                Manifest.permission.ACCESS_FINE_LOCATION
////                ,
//                Manifest.permission.CALL_PHONE
////                , Manifest.permission.RECORD_AUDIO
////                , Manifest.permission.READ_CONTACTS
////                , Manifest.permission.READ_SMS
//        )
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean isGranted) {
//                        Toast.makeText(MainActivity.this, isGranted ? "授权" : "没有授权", Toast.LENGTH_SHORT).show();
//                        if (isGranted) {
//
//                        } else {
//
//                        }
//                    }
//                });

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED) {
////            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
////                Toast.makeText(this, "你拒绝授权，请去设置手动打开", Toast.LENGTH_SHORT).show();
////            } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);
////            }
//        } else {
//            Toast.makeText(this, "你已经有权限了", Toast.LENGTH_SHORT).show();
//        }
        Intent intent = new Intent(MainActivity.this, RNTestActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
