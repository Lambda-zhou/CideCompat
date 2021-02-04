package com.xiaohan.seven.cide;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaohan.seven.cide.api.CideOpenApi;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;
import com.xiaohan.seven.cide.tools.ActivitiesManager;
import com.xiaohan.seven.cide.tools.ApplicationUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import com.exceptions.ExceptionHandler;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import java.io.IOException;
import com.xiaohan.seven.cide.view.AppCompatToast;

public class CideRunJavaScriptActivity extends AppCompatActivity {

	public static CideRunJavaScriptActivity _this;

    private String code;

    private String file;
	
	private String name;

    private TextView log;

    private String threadname;

    private Boolean isErr = false;
    
    public static TextView getLog() {
        return _this.log;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
        Boolean isRun = true;
        
        if(ApplicationGlobalSettings.getOrientation()){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
            isRun = false;
        }
        
        setContentView(R.layout.runmodpe);

        Toolbar toolbar = findViewById(R.id.runmcjslogtoolbar);
        setSupportActionBar(toolbar);
        
        
        
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

		_this = this;
		com.mojang.minecraftpe.MainActivity.currentMainActivity.set(this);
		ActivitiesManager.addActivity(this);
		if(isRun){
		new Handler().postDelayed(new Runnable(){

				@Override
				public void run() {
					runJavaScript(code, name, 1024);
				}
				
		}, 10);
        }
			
	}

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
		new Handler().postDelayed(new Runnable(){

				@Override
				public void run() {
					runJavaScript(code, name, 1024);
				}

			}, 10);
    }
	
	public void runJavaScript(final String code, final String name, long size) {
		new Thread(Thread.currentThread().getThreadGroup(), new Runnable(){

                @Override
                public void run() {
                    //ApplicationUtils.saveFileToSD(, code);
                    switch(ApplicationGlobalSettings.getEngine()){
						case Rhino:
							Context ctx = Context.enter();
							Scriptable scope = ctx.initStandardObjects();
							ctx.setOptimizationLevel(-1);
							ctx.setLanguageVersion(Context.VERSION_ES6);
							ScriptableObject.putConstProperty(scope, "__javaContext__", Context.javaToJS(_this, scope));
							ScriptableObject.putConstProperty(scope, "__javaLoader__", Context.javaToJS(_this.getClass().getClassLoader(), scope));
                            ctx.evaluateString(scope, ApplicationUtils.getAssetsFileText("functions/Modpe.js", CideRunJavaScriptActivity.this), name, 1, null);
                            
							try {
								ctx.getCurrentContext().evaluateString(scope, code, name, 1, null);
							}  catch (final Throwable e) {
                                try {
                                    AppCompatToast.makeText(CideRunJavaScriptActivity.this, new String(ApplicationUtils.decodeString(file)), 1, 1);
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
								ctx.exit();
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
            super.onBackPressed();
	}

}
