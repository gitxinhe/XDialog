package com.kiloway.xdialog.xenabledialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiloway.xdialog.R;
import com.kiloway.xdialog.xloadingdialog.XLoadingDialog;

public class XEnsureDialog extends Dialog {
    private Display display;
    private Context context;
    private TextView mTvTitle;
    private TextView mTvMsg;
    private TextView mTvSure;
    private TextView mTvCancel;
    private Dialog dialog;
    private Window dialogWindow;
    private LinearLayout mLinearBottom;
    private FrameLayout flCustom;
    private View mView;
    //text
    private String mTitle;
    private int mTitleColor;
    private String mMessage;
    private int mMsgColor;
    private String mNegativeText;
    private int mNegativeTextColor;
    private String mPositiveText;
    private int mPositiveTextColor;
    private boolean isNegativeShow = true;
    private float dialogWidth;
    private float dialogHeight;
    private View line;
    private DialogInterface.OnClickListener onPositiveListener;
    private DialogInterface.OnClickListener onNegativeListener;
    private XEnsureDialog(Context context) {
        super(context, R.style.Custom_Dialog_Style);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ensure_layout);
        mLinearBottom = ((LinearLayout) findViewById(R.id.linear_bottom));
        mTvTitle = ((TextView) findViewById(R.id.tv_title));
        mTvMsg = ((TextView) findViewById(R.id.tv_msg));
        mTvSure = ((TextView) findViewById(R.id.tv_sure));
        mTvCancel = ((TextView) findViewById(R.id.tv_cancel));
        flCustom = findViewById(R.id.fl_dialog_content);
        line = findViewById(R.id.line);

    }

    @Override
    public void show() {
        super.show();
        show(this);
    }

    private void show(final XEnsureDialog mDialog) {
        if (!TextUtils.isEmpty(mDialog.mTitle)) {
            mDialog.mTvTitle.setText(mDialog.mTitle);
        }
        if (mDialog.mView != null) {
            mDialog.flCustom.addView(mDialog.mView);
            mDialog.mTvMsg.setVisibility(View.GONE);
        } else {
            if (!TextUtils.isEmpty(mDialog.mMessage)) {
                mDialog.mTvMsg.setText(mDialog.mMessage);
                mDialog.mTvMsg.setVisibility(View.VISIBLE);
            }
        }
        if (!mDialog.isNegativeShow) {
            mDialog.mTvCancel.setVisibility(View.GONE);
            mDialog.line.setVisibility(View.GONE);
        } else {
            mDialog.mTvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNegativeListener != null)
                        onNegativeListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);
                }
            });
            if (!TextUtils.isEmpty(mDialog.mNegativeText)) {
                mDialog.mTvCancel.setText(mDialog.mNegativeText);
            }
        }
        mDialog.mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null)
                    onPositiveListener.onClick(mDialog, DialogInterface.BUTTON_POSITIVE);
            }
        });
        if (!TextUtils.isEmpty(mDialog.mPositiveText)) {
            mDialog.mTvSure.setText(mDialog.mPositiveText);
        }
        if (mDialog.mPositiveTextColor != 0)
            mDialog.mTvSure.setTextColor(mDialog.mPositiveTextColor);
        if (mDialog.mNegativeTextColor != 0)
            mDialog.mTvCancel.setTextColor(mDialog.mNegativeTextColor);
        if (mDialog.mMsgColor != 0)
            mDialog.mTvMsg.setTextColor(mMsgColor);
        if (mTitleColor != 0) {
            mDialog.mTvTitle.setTextColor(mDialog.mTitleColor);
        }

        Window dialogWindow = getWindow();
        WindowManager windowManager = ((Activity) this.context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = mDialog.dialogWidth == 0 ? (int) (display.getWidth() * 0.8) : (int) (display.getWidth() * mDialog.dialogWidth);
        params.height = mDialog.dialogHeight == 0 ? WindowManager.LayoutParams.WRAP_CONTENT : (int) (display.getHeight() * mDialog.dialogHeight);
        dialogWindow.setAttributes(params);
    }

    public static class Builder {

        private XEnsureDialog mDialog;

        public Builder(Context context) {
            mDialog = new XEnsureDialog(context);
        }

        public Builder setWidth(float width) {
            mDialog.dialogWidth = width;
            return this;
        }

        public Builder setHeight(float width) {
            mDialog.dialogHeight = width;
            return this;
        }

        /**
         * 设置弹窗标题
         *
         * @param title 标题
         * @return
         */
        public Builder setTitle(String title) {
            mDialog.mTitle = title;
            return this;
        }

        /**
         * 设置标题颜色
         *
         * @param color
         * @return
         */
        public Builder setTitleColor(int color) {
            mDialog.mTitleColor = color;
            return this;
        }

        /**
         * 设置对话框文本内容,如果调用了setView()方法，该项失效
         *
         * @param msg
         */
        public Builder setMessage(String msg) {
            mDialog.mMessage = msg;
            return this;
        }

        /**
         * 设置对话框文本内容,如果调用了setView()方法，该项失效
         *
         * @param color
         */
        public Builder setMessageColor(int color) {
            mDialog.mMsgColor = color;
            return this;
        }

        public Builder setNegativeText(String text) {
            mDialog.mNegativeText = text;
            return this;
        }

        public Builder setNegativeTextColor(int color) {
            mDialog.mNegativeTextColor = color;
            return this;
        }

        public Builder setPositiveText(String text) {
            mDialog.mPositiveText = text;
            return this;
        }

        public Builder setPositiveTextColor(int color) {
            mDialog.mPositiveTextColor = color;
            return this;
        }

        public Builder setView(View view) {
            mDialog.mView = view;
            return this;
        }

        /**
         * 设置是否显示取消按钮，默认显示
         *
         * @param isNegativeShow
         */
        public Builder setNegativeShow(boolean isNegativeShow) {
            mDialog.isNegativeShow = isNegativeShow;
            return this;
        }

        /**
         * 设置取消按钮的回调
         *
         * @param onClickListener
         */
        public Builder setNegativeClick(DialogInterface.OnClickListener onClickListener) {
            mDialog.onNegativeListener = onClickListener;
            return this;
        }

        /**
         * 设置确认按钮的回调
         *
         * @param onClickListener
         */
        public Builder setPositiveClick(DialogInterface.OnClickListener onClickListener) {
            mDialog.onPositiveListener = onClickListener;
            return this;
        }
        /**
         * 设置是否可以按返回键取消
         * @param isCancelable
         * @return
         */

        public Builder setCancelable(boolean isCancelable){
            mDialog.setCancelable(isCancelable);
            return this;
        }

        /**
         * 设置是否点击外部可以取消
         * @param isCancelOutside
         * @return
         */
        public Builder setCancelOutside(boolean isCancelOutside){
            mDialog.setCanceledOnTouchOutside(isCancelOutside);
            return this;
        }
        /**
         * 通过Builder类设置完属性后构造对话框的方法
         */
        public XEnsureDialog create() {
            return mDialog;
        }

    }
}