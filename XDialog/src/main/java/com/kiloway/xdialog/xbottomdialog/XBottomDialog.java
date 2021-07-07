package com.kiloway.xdialog.xbottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kiloway.xdialog.R;
import com.kiloway.xdialog.util.DensityUtil;
import com.kiloway.xdialog.xenabledialog.XEnsureDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: XBottomDialog
 * @Description: java类作用描述
 * @Author: 何鑫
 * @CreateDate: 2021/6/22 9:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/22 9:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class XBottomDialog extends Dialog{
    private Context context;
    private TextView tvTitle;
    private TextView tvCancel;
    private LinearLayout llContent;
    private ScrollView svContent;
    private Display display;
    //content
    private String mTitle;
    private int mTitleColor;
    private List<SheetItem> sheetItemList;
    private XBottomDialog(@NonNull Context context) {
        super(context, R.style.ActionGeneralDialog);
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_layout);
        initView();
    }

    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        tvCancel = findViewById(R.id.tv_cancel);
        llContent = findViewById(R.id.ll_content);
        svContent = findViewById(R.id.sv_content);
    }
    public void show(final XBottomDialog xBottomDialog){
        setSheetItems(xBottomDialog);
        if (TextUtils.isEmpty(mTitle)){
            tvTitle.setText(mTitle);
        }
        if (mTitleColor!=0){
            tvTitle.setTextColor(mTitleColor);
        }
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xBottomDialog.dismiss();
            }
        });
        Window dialogWindow = xBottomDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = display.getWidth();
        dialogWindow.setAttributes(lp);
    }
    @Override
    public void show() {
        super.show();
        show(this);
    }
    private void setSheetItems(final XBottomDialog xBottomDialog) {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }
        int size = sheetItemList.size();
        if (size >= 7) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) svContent.getLayoutParams();
            params.height = display.getHeight() / 2;
            svContent.setLayoutParams(params);
        }
        for (int i = 0; i <size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i);
            String strItem = sheetItem.name;
            int textSize = sheetItem.textSize;
            int color = sheetItem.color;
            final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;
            TextView textView = new TextView(context);
            textView.setText(strItem);

            textView.setGravity(Gravity.CENTER);
            if (color==0) {
                textView.setTextColor(context.getResources().getColor(R.color.black));
            } else {
                textView.setTextColor(context.getResources().getColor(color));
            }
            if (textSize==0){
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            }else {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
            }
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, height));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    xBottomDialog.dismiss();
                }
            });
            llContent.addView(textView);
            if (i < size-1) {//此if语句是我加的使每一小项之间有分割线
                Log.e("tag",i+"---"+size);
                View v = new View(context);
                v.setBackgroundColor(Color.parseColor("#c6c6c6"));
                v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
                llContent.addView(v);
            }
        }
    }
    public static class Builder{
        private XBottomDialog xBottomDialog;

        public Builder(Context context){
            xBottomDialog = new XBottomDialog(context);
        }
        /**
         * 设置标题文字
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title){
           xBottomDialog.mTitle = title;
           return this;
        }
        /**
         * 设置标题颜色
         *
         * @param color
         * @return
         */
        public Builder setTitleColor(int color) {
            xBottomDialog.mTitleColor = color;
            return this;
        }
        public Builder addSheetItem(SheetItem sheetItem){
            if (xBottomDialog.sheetItemList == null) {
                xBottomDialog.sheetItemList = new ArrayList<SheetItem>();
            }
            xBottomDialog.sheetItemList.add(sheetItem);
            return this;
        }
        /**
         * 通过Builder类设置完属性后构造对话框的方法
         */
        public XBottomDialog create() {
            return xBottomDialog;
        }
        /**
         * 设置是否可以按返回键取消
         * @param isCancelable
         * @return
         */

        public XBottomDialog.Builder setCancelable(boolean isCancelable){
            xBottomDialog.setCancelable(isCancelable);
            return this;
        }

        /**
         * 设置是否点击外部可以取消
         * @param isCancelOutside
         * @return
         */
        public XBottomDialog.Builder setCancelOutside(boolean isCancelOutside){
            xBottomDialog.setCanceledOnTouchOutside(isCancelOutside);
            return this;
        }
    }
    public static class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        int color;
        int textSize;
        public SheetItem(String name, OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.itemClickListener = itemClickListener;
        }
        public SheetItem(String name, int color, OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
        public SheetItem(String name,int color,int textSize,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.textSize = textSize;
            this.itemClickListener = itemClickListener;
        }

    }
    public interface OnSheetItemClickListener {
        void onClick(int which);
    }
}
