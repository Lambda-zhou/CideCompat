package com.xiaohan.seven.cide;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.xiaohan.seven.cide.tools.ActivitiesManager;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Build;
import android.text.TextUtils;
import android.content.Intent;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;



public class BaseActivity extends AppCompatActivity {
    
    public Activity THIS_ACTIVITY = this;
    public Context THIS_CONTEXT = this;
    private Boolean isExit = false;
    private final String STORAGE = android.os.Environment.getExternalStorageDirectory().getAbsoluteFile().toString() + "/";
    
    private Thread wiftProxyThread;
    private Boolean wiftProxyThreadOpen = true;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        ActivitiesManager.addActivity(this);
        
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
        ActivitiesManager.removeActivity(this);
        if(wiftProxyThread != null){
            wiftProxyThreadOpen = false;
        }
	}
    
    protected ViewGroup getSnackView() {
        return findViewById(R.id.DrawerLayout);
    }

    @Override
    public void onBackPressed() {
		
        if(!isExit){
            isExit = true;
            
            Snackbar.make(getSnackView(), "再点一次退出", Snackbar.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask(){

                    @Override
                    public void run() {
                        isExit = false;
                    }
                    
                }, 2000);
        }else{
        super.onBackPressed();
        }
		
    }
    
}
