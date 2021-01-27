package com.xiaohan.seven.cide.tools;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.xiaohan.seven.cide.R;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/15.
 */

public class Permission {
    Activity context;
    String[] p;
	private String msg;
    public Permission(Activity context){this.context = context;}
    public void Call(String[] str){
        ArrayList<String> perm = new ArrayList<String>();

        for(int a = 0 ;a < str.length ; a++) {
            System.out.println(str[a]);
            int permissionCheck = ContextCompat.checkSelfPermission(context,
                    str[a]);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                perm.add(str[a]);
            }
        }
        if(perm.size()==0){
            ViewGroup lay =  (ViewGroup)context.getWindow().getDecorView();
            Snackbar.make(lay, "已同意全部权限", Snackbar.LENGTH_LONG)
                .setAction("确定", new OnClickListener(){

                    @Override
                    public void onClick(View p1) {
                        
                    }
                
            })
            .show();
        }
        if(perm.size()>0&&perm.get(0)!=null){
         p = new String[perm.size()];
        for(int a = 0 ;a <p.length;a++){
            p[a]=perm.get(a);
        }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(context, p,123);
            }else{
              show();
            }
        }
    }



    public void CheckPermission(){
        AlertDialog ad = new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_launcher)
                .setTitle(String.format("申请权限(共%s个)", AutoString.Permission.length))
				
                .setMessage(String.format("一共%s个权限,您确定申请吗?\n申请权限可以避免" + context.getString(R.string.app_name) + "引起的权限异常\n包括必要的SD卡读写",AutoString.Permission.length))
				
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call(AutoString.Permission);
                    }
                })
                .setNegativeButton("",null)
                .show();
        ad.setCanceledOnTouchOutside(false);
        ad.setCancelable(false);
    }
	
	public void setMessage(String msg) {
		this.msg = msg;
	}

    public void show(){
        if(p!=null&&p.length>0) {
            AlertDialog ad = new AlertDialog.Builder(context)
                    .setTitle("权限申请")
                    .setMessage("检测到是Android6.0以下的系统,无法使用运行时权限,请点击确定按钮进入设置打开权限")
                    .setIcon(R.drawable.ic_launcher)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + context.getPackageName())); // 根据包名打开对应的设置界面
                                context.startActivity(intent);
                            } catch (Exception e) {
                                
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
            ad.setCancelable(false);
            ad.setCanceledOnTouchOutside(false);
        }
    }
}
