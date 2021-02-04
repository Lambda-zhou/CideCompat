package com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.xiaohan.seven.cide.R;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.view.View;
import com.xiaohan.seven.cide.CideRunAndJSActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Scriptable;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import com.xiaohan.seven.cide.tools.ApplicationUtils;
import java.io.File;
import com.xiaohan.seven.cide.view.AppCompatToast;
import java.io.StringWriter;
import java.io.PrintWriter;
import android.widget.Toast;

public class Activity extends AppCompatActivity {
	
	private Toolbar toolbar;
	
	private LinearLayout layout;
	
	private static Activity _this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity);
		_this = this;
		Bundle bundle = getIntent().getExtras();
		final Object[] objects = ((BundleData)bundle.getSerializable("data")).getObjects();
		final String path = bundle.getString("path");
		final String name = bundle.getString("activity");
		final String code = ApplicationUtils.getTextFromSD(new File(path));
		toolbar = findViewById(R.id.activity_Toolbar);
		setSupportActionBar(toolbar);
		
		layout = findViewById(R.id.activity_activityLinearLayout);
		
		new Thread(Thread.currentThread().getThreadGroup(), new Runnable(){

                @Override
                public void run() {
                    //ApplicationUtils.saveFileToSD(, code);
                            Context ctx = Context.enter();
                            Scriptable scope = ctx.initStandardObjects();
                            ctx.setOptimizationLevel(-1);
                            ctx.setLanguageVersion(Context.VERSION_ES6);
                            ScriptableObject.putProperty(scope, "__javaContext__", Context.javaToJS(_this, scope));
                            ScriptableObject.putConstProperty(scope, "__javaLoader__", Context.javaToJS(_this.getClass().getClassLoader(), scope));
							ScriptableObject.putConstProperty(scope, "__javaPath__", Context.javaToJS(path, scope));
							ScriptableObject.putProperty(scope, "__params__", Context.javaToJS(objects, scope));
							ctx.evaluateString(scope, "", name, 1, null);
							ctx.getCurrentContext().evaluateString(scope, ApplicationUtils.getTextFromSD(new File(new File(path).getParent() + "/CideCompat.as")), name, 1, null);

                            try {

                                ctx.getCurrentContext().evaluateString(scope, code, name, 1, null);
                            }  catch (final Throwable e) {
                                try {
                                    AppCompatToast.makeText(Activity.this, new String(ApplicationUtils.decodeString(path)), 1, 1);
                                    ctx.getCurrentContext().evaluateString(scope, new String(ApplicationUtils.decodeString(path)), name, 1, null);
                                } catch (Throwable e2) {
                                    _this.runOnUiThread(new Runnable(){

                                            @Override
                                            public void run() {
                                                StringWriter sw = new StringWriter();
                                                PrintWriter pw = new PrintWriter(sw);
                                                e.printStackTrace(pw);
                                                String error = sw.toString();
                                                //print(-1, error);
                                                try{
                                                    Toast.makeText(getApplication(), new String(ApplicationUtils.decodeString(path)), Toast.LENGTH_SHORT).show();
                                                }catch(Throwable e){
                                                    Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                                }

                            } finally {
                                //ctx.exit();
                            }
                            
                }

            }, ApplicationUtils.getWorkName(this), 1024 * 1024).start();
		

	}
    
	public Toolbar getToolbar() {
		return toolbar;
	}
	
	public void addView(View view) {
		layout.addView(view);
	}
	
}
