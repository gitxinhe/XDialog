package com.kiloway.xdialog.xloadingdialog;

import android.content.Context;

/**
 * @ClassName: XLoadingDialogUtil
 * @Description: java类作用描述
 * @Author: 何鑫
 * @CreateDate: 2021/6/18 16:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/18 16:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class XLoadingDialogUtil {
    private static XLoadingDialog loadingDailog;
    private static XLoadingDialog.Builder loadBuilder;
    public static void dismissLoading() {
        if (loadingDailog != null && loadingDailog.isShowing()) {
            loadingDailog.dismiss();
            loadingDailog = null;
        }
    }
    public static void setDialogContent(String content){
        if (loadingDailog != null && loadingDailog.isShowing()) {
            if (loadBuilder!=null){
                loadBuilder.setAppendMessage(content);
            }
        }
    }
    public static void showLoading(Context context, String tip){
        loadBuilder = new XLoadingDialog.Builder(context)
                .setMessage(tip)
                .setCancelable(true)
                .setCancelOutside(false);
        loadingDailog = loadBuilder.create();
        loadingDailog.show();
    }
}
