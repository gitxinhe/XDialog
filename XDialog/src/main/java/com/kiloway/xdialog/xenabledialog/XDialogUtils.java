package com.kiloway.xdialog.xenabledialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kiloway.xdialog.R;

public class XDialogUtils {
    public static void showSureDialog(final Context context, String title, String content,boolean showCancel){
        XEnsureDialog ensureDialog = new XEnsureDialog.Builder(context)
                .setMessage(content)
                .setTitle(title)
                .setNegativeShow(showCancel)
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
                }).create();
        ensureDialog.show();
    }
}