package com.kiloway.XLoadinDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.kiloway.xdialog.xbottomdialog.XBottomDialog;
import com.kiloway.xdialog.xenabledialog.XEnsureDialog;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*XLoadingDialogUtil.showLoading(this, "加载中...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                XLoadingDialogUtil.setDialogContent("提交中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        XLoadingDialogUtil.dismissLoading();
                    }
                }, 2000);
            }
        }, 2000);*/
        showSureDialog(this, "提示", "这是一个确认提示框");
        //showBottomDialog();
    }
    private void showBottomDialog(){
        new XBottomDialog.Builder(this)
                .setTitle("你确认删除这张照片吗?")
                .setCancelOutside(true)
                .addSheetItem(new XBottomDialog.SheetItem("删除",R.color.red, new XBottomDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                })).create().show();
    }
    private void showSureDialog(Context context, String title, String content) {
         new XEnsureDialog.Builder(context)
                .setMessage(content)
                .setTitle(title)
                .setNegativeClick(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveClick(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }
}