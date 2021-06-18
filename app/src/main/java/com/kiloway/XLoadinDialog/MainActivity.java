package com.kiloway.XLoadinDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.kiloway.xloadingdialog.XLoadingDialog;
import com.kiloway.xloadingdialog.XLoadingDialogUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XLoadingDialogUtil.showLoading(this,"加载中...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                XLoadingDialogUtil.setDialogContent("提交中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        XLoadingDialogUtil.dismissLoading();
                    }
                },2000);
            }
        },2000);
    }
}