package com.xiaohan.seven.cide;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import com.tencent.smtt.sdk.WebView;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;
import com.xiaohan.seven.cide.view.AppCompatWebView;
import com.xiaohan.seven.cide.server.HtmlServer;
import java.io.IOException;

public class CideRunHtmlActivity extends BaseActivity {
    
	private WebView brower;
	
	private String code;
	
	private Boolean isRun = true;
    
    private Boolean isUseLocalhost = false;
    
    private HtmlServer server;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_run_html);
		
		Toolbar toolbar = findViewById(R.id.html_toolbar);
		setSupportActionBar(toolbar);
		
		LinearLayout layout = findViewById(R.id.activity_run_html_LinearLayout);
		brower = new AppCompatWebView.Builder(this).newInstance();
		layout.addView(brower);
		brower.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		
        isUseLocalhost = ApplicationGlobalSettings.isUseLocalhost;
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		if(savedInstanceState != null){
            code = savedInstanceState.getString("code");
        }else{
			code = getIntent().getStringExtra("code");
		}
		
        if(ApplicationGlobalSettings.getOrientation()){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
			isRun = false;
        }
		
		if(isRun){
			run();
		}
		
    }
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
		run();
    }
    
	private void run() {
        if(isUseLocalhost){
            server = new HtmlServer(8010);
            server.setData(code);
            try {
                server.start();
                brower.loadUrl("http://localhost:8010/");
            } catch (IOException e) {
                brower.loadDataWithBaseURL(null, "<!DOCTYPE html><html><head><title>Error</title></head><body><h1>Error !</h1><h4>" + e.toString() + "</h4></body></html>", "text/html", "UTF-8", null);
            }
            
            return;
        }
		brower.loadDataWithBaseURL(null, code, "text/html", "UTF-8", null);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isUseLocalhost){
            server.stop();
        }
    }

    @Override
    public void onBackPressed() {
        
        finish();
    }
	
}
