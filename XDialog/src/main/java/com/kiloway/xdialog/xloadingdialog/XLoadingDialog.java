package com.kiloway.xdialog.xloadingdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.kiloway.xdialog.R;

/**
 * @ClassName: XLoadingDialog
 * @Description: java类作用描述
 * @Author: 何鑫
 * @CreateDate: 2021/6/18 14:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/18 14:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class XLoadingDialog extends Dialog {
    public XLoadingDialog(Context context) {
        super(context);
    }

    public XLoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    public static class Builder{
        public static TextView msgText;
        private Context context;
        private String message;
        private boolean isShowMessage=true;
        private boolean isCancelable=false;
        private boolean isCancelOutside=false;
        private int textColor;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         * @param message
         * @return
         */

        public Builder setMessage(String message){
            this.message=message;
            return this;
        }

        /**
         * 设置是否显示提示信息
         * @param isShowMessage
         * @return
         */
        public Builder setShowMessage(boolean isShowMessage){
            this.isShowMessage=isShowMessage;
            return this;
        }
        public void setAppendMessage(String content) {
            if (msgText != null)
                msgText.setText(content);
        }
        /**
         * 设置是否可以按返回键取消
         * @param isCancelable
         * @return
         */

        public Builder setCancelable(boolean isCancelable){
            this.isCancelable=isCancelable;
            return this;
        }

        /**
         * 设置是否可以取消
         * @param isCancelOutside
         * @return
         */
        public Builder setCancelOutside(boolean isCancelOutside){
            this.isCancelOutside=isCancelOutside;
            return this;
        }
        /**
         * 字体颜色
         * @param textColor
         * @return
         */

        public Builder setTextColor(int textColor){
            this.textColor=textColor;
            return this;
        }
        public XLoadingDialog create(){
            LayoutInflater inflater = LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.dialog_loading,null);
            XLoadingDialog loadingDailog=new XLoadingDialog(context,R.style.Custom_Dialog_Style);
            msgText= (TextView) view.findViewById(R.id.tipTextView);
            if (textColor!=0){
                msgText.setTextColor(textColor);
            }
            if(isShowMessage){
                msgText.setText(message);
            }else{
                msgText.setVisibility(View.GONE);
            }
            loadingDailog.setContentView(view);
            loadingDailog.setCancelable(isCancelable);
            loadingDailog.setCanceledOnTouchOutside(isCancelOutside);
            return  loadingDailog;

        }
    }
}
