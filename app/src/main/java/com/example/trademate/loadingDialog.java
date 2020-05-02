package com.example.trademate;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class loadingDialog {
    Activity activity;
    AlertDialog dialog;
    loadingDialog(Activity a)
    {
        activity=a;
    }
    void startLoad()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dailog,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }
    void endload()
    {
        dialog.dismiss();
    }
}
