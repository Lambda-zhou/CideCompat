package com.xiaohan.seven.cide;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;
import com.exceptions.ExceptionHandler;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;
import com.xiaohan.seven.cide.tools.ActivitiesManager;
import com.xiaohan.seven.cide.tools.ApplicationUtils;
import com.xiaohan.seven.cide.view.AppCompatToast;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import java.io.File;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import com.xiaohan.seven.cide.data.AndroidData;
import com.google.gson.Gson;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import com.activity.Activity;
import com.activity.BundleData;

public class CideRunAndJSActivity extends BaseActivity {
    
    public static CideRunAndJSActivity _this;

    private String code;

    private String file;

    private String name;

    private TextView log;

    private String threadname;

    private Boolean isErr = false;
	
	private Toolbar toolbar;

    public static TextView getLog() {
        return _this.log;
    }
	
	private CoordinatorLayout cl;
	
	private LinearLayout lay;
	
	private Boolean isSetContentView = false;
	
	public Context ctx;
	
	public Context currentCtx;
	
	public Scriptable scope;
	
	private Boolean isStudy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean isRun = true;

        if(ApplicationGlobalSettings.getOrientation()){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
            isRun = false;
        }

        setContentView(R.layout.runmodpe);

        toolbar = findViewById(R.id.runmcjslogtoolbar);
        setSupportActionBar(toolbar);

        cl = findViewById(R.id.runmodpe_CoordinatorLayout);
		
		lay = findViewById(R.id.runmodpe_addview_LinearLayout);
		
        threadname = Thread.currentThread().getName();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    if (t.getName().equals(threadname)) {
                        isErr = true;
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        String error = sw.toString();
                        ApplicationUtils.saveFileToSD(ApplicationUtils.getSD() + "/" + "error.txt", error);
                        onBackPressed();
                    } else {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        String error = sw.toString();
                        print(-1, error);
                    }
                }
            });
			
        this.code = getIntent().getStringExtra("code");

        this.file = getIntent().getStringExtra("project_file");

        this.name = getIntent().getStringExtra("project_name");

        log = findViewById(R.id.runmodpeTextView);
		
		if(new File(new File(file).getParent() + "/build.json").isFile()){
			try{
			AndroidData datas = new Gson().fromJson(ApplicationUtils.getTextFromSD(new File(new File(file).getParent() + "/build.json")), AndroidData.class);
			this.code = ApplicationUtils.getTextFromSD(new File(datas.getPaths()[0]));
			}catch(Throwable e){
				
			}
		}else{
			isStudy = true;
		}

        _this = this;
        ActivitiesManager.addActivity(this);
        if(isRun){
            new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        runJavaScript(code, name, 2048);
                    }

                }, 1);
        }

    }

	@Override
	public void setContentView(View view) {
		if(!isSetContentView){
		isSetContentView = true;
		super.setContentView(view);
		}
	}
	
	public void addView(View view) {
		lay.removeView(findViewById(R.id.runmodpeRelativeLayout));
		lay.addView(view);
	}

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    runJavaScript(code, name, 2048);
                }

            }, 1);
    }

    public void runJavaScript(final String code, final String name, long size) {
        new Thread(Thread.currentThread().getThreadGroup(), new Runnable(){

                @Override
                public void run() {
                    //ApplicationUtils.saveFileToSD(, code);
                    switch(ApplicationGlobalSettings.getEngine()){
                        case Rhino:
                            ctx = Context.enter();
							_this.ctx = ctx;
                            scope = ctx.initStandardObjects();
							_this.scope = scope;
                            ctx.setOptimizationLevel(-1);
                            ctx.setLanguageVersion(Context.VERSION_ES6);
                            ScriptableObject.putProperty(scope, "__javaContext__", Context.javaToJS(_this, scope));
                            ScriptableObject.putConstProperty(scope, "__javaLoader__", Context.javaToJS(_this.getClass().getClassLoader(), scope));
							ScriptableObject.putConstProperty(scope, "__javaPath__", Context.javaToJS(file, scope));
							ScriptableObject.putConstProperty(scope, "__params__", Context.javaToJS(null, scope));
							ctx.evaluateString(scope, "", name, 1, null);
							currentCtx = ctx.getCurrentContext();
							if(!isStudy){
							currentCtx.evaluateString(scope, ApplicationUtils.getTextFromSD(new File(new File(file).getParent() + "/CideCompat.as")), name, 1, null);
							}else{
								currentCtx.evaluateString(scope, ApplicationUtils.getAssetsFileText("CideCompat.as", CideRunAndJSActivity.this), name, 1, null);
								
							}
                            try {
								
                                currentCtx.getCurrentContext().evaluateString(scope, code, name, 1, null);
                            }  catch (final Throwable e) {
                                try {
                                    AppCompatToast.makeText(CideRunAndJSActivity.this, new String(ApplicationUtils.decodeString(file)), 1, 1);
                                    ctx.getCurrentContext().evaluateString(scope, new String(ApplicationUtils.decodeString(file)), name, 1, null);
                                } catch (Throwable e2) {
                                    _this.runOnUiThread(new Runnable(){

                                            @Override
                                            public void run() {
                                                StringWriter sw = new StringWriter();
                                                PrintWriter pw = new PrintWriter(sw);
                                                e.printStackTrace(pw);
                                                String error = sw.toString();
                                                print(-1, error);
                                                try{
                                                    Toast.makeText(getApplication(), new String(ApplicationUtils.decodeString(file)), Toast.LENGTH_SHORT).show();
                                                }catch(Throwable e){
                                                    Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                                }

                            } finally {
                                //ctx.exit();
                            }
                            break;

                        case Javax:

                            break;

                        case Nashorn:
                            //ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
                            break;
                    }

                }

            }, ApplicationUtils.getWorkName(this), size * size).start();

    }
	
	public void newActivity(String activityPath, String activityName, Object ... objs) {
		Intent intent = new Intent(this, Activity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("data", new BundleData(objs));
		bundle.putString("path", activityPath);
		bundle.putString("activity", activityName);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public Toolbar getToolbar() {
		
		return this.toolbar;
	}
	
    public void print(int type, String msg) {
        switch (type) {
            case -1:
                log.setTextColor(Color.RED);
                break;

            case 0:
                log.setTextColor(Color.BLACK);
                break;

            case 2:
                log.setTextColor(Color.BLUE);
                break;
        }
        log.append(msg);
    }

    public static void sendMessage(String tag, String msg, String color) {
        _this.log.setTextColor(Color.parseColor(color));
        _this.log.append("<" + tag + ">" + " " + msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesManager.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler.getInstance());
        if (isErr)
            android.os.Process.killProcess(android.os.Process.myPid());
        else
            finish();
	}
    
}
