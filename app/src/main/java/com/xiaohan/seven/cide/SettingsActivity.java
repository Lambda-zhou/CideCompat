package com.xiaohan.seven.cide;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.xiaohan.seven.cide.dialog.AppCompatDialog;
import com.xiaohan.seven.cide.interfaces.OnHttpPostFinishListener;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;
import com.xiaohan.seven.cide.tools.HttpUtils;
import com.xiaohan.seven.cide.view.AppCompatToast;
import java.util.HashMap;
import java.util.Map;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.content.res.Resources;
import android.content.res.ColorStateList;
import com.xiaohan.seven.cide.dialog.AppCompatRadioButtonDialog;
import com.xiaohan.seven.cide.interfaces.OnItemCheckListener;

public class SettingsActivity extends BaseActivity {
    
	private NavigationView itemView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
        
        Toolbar toolbar = findViewById(R.id.settings_toolbar);
		setSupportActionBar(toolbar);
		
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		itemView = findViewById(R.id.activity_settings_NavigationView);
        if(ApplicationGlobalSettings.app_theme){
            
            Resources res = getBaseContext().getResources();
            ColorStateList csl = res.getColorStateList(R.color.selector);
            itemView.setItemTextColor(csl);
            itemView.setBackgroundColor(Color.parseColor("#303131"));
            itemView.setItemIconTintList(null);
            
        }
		itemView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

				@Override
				public boolean onNavigationItemSelected(MenuItem item) {
					switch(item.getItemId()){
						case R.id.app_result:
						AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
						alert.setTitle("反馈");
						alert.setCancelable(false);
						
						LinearLayoutCompat linearLayout = new LinearLayoutCompat(SettingsActivity.this);
						linearLayout.setOrientation(1);
						
						final AppCompatEditText title = new AppCompatEditText(SettingsActivity.this);
						title.setText("");
						title.setHint("反馈标题..");
						linearLayout.addView(title);
						
						final AppCompatEditText message = new AppCompatEditText(SettingsActivity.this);
						message.setText("");
						message.setHint("内容..");
						linearLayout.addView(message);
						
						alert.setView(linearLayout);
						alert.setPositiveButton("取消", null);
						alert.setNegativeButton("发送", new DialogInterface.OnClickListener(){

									@Override
									public void onClick(DialogInterface p1, int p2) {
										Map<String,String> body = new HashMap<>();
										body.put("admin", "2632588830");
										body.put("api", "code");
										body.put("title", title.getText().toString());
										body.put("content", message.getText().toString());
										HttpUtils.POST("http://1101dsw.top/yhfk.php", body, new OnHttpPostFinishListener(){

												@Override
												public void onPostFinish(String message, int code) {
													AppCompatToast.makeText(SettingsActivity.this, message, 1);
												}

												@Override
												public void onPostError(String error) {
													AppCompatToast.makeText(SettingsActivity.this, error, 1, 1);
												}
											});
									}
						});
						alert.create();
						alert.show();
						break;
                        
                        case R.id.project_path:
                        
                        break;
                        
                        case R.id.html_settings:
                        final String[] datas = new String[]{
                            "是",
                            "否"
                        };
                        new AppCompatRadioButtonDialog(SettingsActivity.this)
                        .setItems(datas)
                        .setTitle("Html - 设置")
                        .setMessage("使用localhost")
                        .setOnItemCheckListener(new OnItemCheckListener() {

                            @Override
                            public void onItemCheck(RadioButton radioButton) {
                                switch(radioButton.getText().toString()){
                                    case "是":
                                    ApplicationGlobalSettings.isUseLocalhost = true;
                                    ApplicationGlobalSettings.saveData("use_localhost", true);
                                    break;
                                    
                                    case "否":
                                    ApplicationGlobalSettings.isUseLocalhost = false;
                                    ApplicationGlobalSettings.saveData("use_localhost", false);
                                    break;
                                }
                            }
                            
                         })
                        .show();
                        break;
                        
                        case R.id.project_orientation:
                        final AppCompatDialog dialog = new AppCompatDialog(SettingsActivity.this);
                        //dialog.setTitle("屏幕方向");
                        LinearLayoutCompat layout = new LinearLayoutCompat(SettingsActivity.this);
                        layout.setOrientation(1);
                        RadioGroup group = new RadioGroup(SettingsActivity.this);
                        layout.addView(group);
                        
                        final RadioButton horizontalButton = new RadioButton(SettingsActivity.this);
                        horizontalButton.setText("横屏");
                        group.addView(horizontalButton);
                        
                        final RadioButton verticalButton = new RadioButton(SettingsActivity.this);
                        verticalButton.setText("竖屏");
                        group.addView(verticalButton);
                        
                        SharedPreferences sp =  MainActivity.getSharedPreferences();
                        Boolean isCheck = sp.getBoolean("runtime_orientation", false);
                        if(isCheck){
                        horizontalButton.setChecked(isCheck);
                        }else{
                            verticalButton.setChecked(true);
                            
                        }
                        
                            dialog.setPostiveButton("确定", new OnClickListener(){

                                    @Override
                                    public void onClick(View p1) {
                                        Boolean isCheck = horizontalButton.isChecked();
                                        ApplicationGlobalSettings.setOrientation(isCheck);
                                        SharedPreferences sp =  MainActivity.getSharedPreferences();
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putBoolean("runtime_orientation", isCheck);
                                        editor.commit();
                                        AppCompatToast.makeText(SettingsActivity.this, "设置成功");
                                        dialog.dismiss();
                                    }
                                    
                            });
                        dialog.setContentView(layout);
                        dialog.setHeight((int)(dialog.getHeight() * 0.85));
                        dialog.show();
                        break;
						
						case R.id.app_theme:
					    final AppCompatDialog theme_dialog = new AppCompatDialog(SettingsActivity.this);
						theme_dialog.setTitle("主题");
						LinearLayoutCompat theme_layout = new LinearLayoutCompat(SettingsActivity.this);
						theme_layout.setOrientation(1);
						final SwitchCompat sc = new SwitchCompat(SettingsActivity.this);
						sc.setText("夜间模式");
                        sc.setChecked(ApplicationGlobalSettings.app_theme);
						theme_layout.addView(sc);
						theme_dialog.setPostiveButton("确定", new OnClickListener(){

									@Override
									public void onClick(View p1) {
										Boolean isCheck = sc.isChecked();
										ApplicationGlobalSettings.saveData("app_theme", isCheck);
                                        ApplicationGlobalSettings.app_theme = isCheck;
                                        theme_dialog.dismiss();
									}
									
						});
						theme_dialog.setContentView(theme_layout);
                        theme_dialog.setHeight((int)(theme_dialog.getHeight() * 0.85));
                        theme_dialog.show();
						break;
					}
					return true;
				}
		});
		
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
    public void onBackPressed() {
        finish();
    }
    
}
