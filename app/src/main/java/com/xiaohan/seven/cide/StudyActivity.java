package com.xiaohan.seven.cide;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import io.github.kbiakov.codeview.CodeView;
import io.github.kbiakov.codeview.adapters.Options;
import com.xiaohan.seven.cide.tools.ActivitiesManager;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.widget.Toast;
import android.content.Context;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;
import io.github.kbiakov.codeview.highlight.ColorTheme;

public class StudyActivity extends AppCompatActivity {

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_code);
        
        ActivitiesManager.addActivity(this);
        
        Toolbar toolbar = findViewById(R.id.activity_study_code_Toolbar);
        setSupportActionBar(toolbar);
        
        CodeView codeView = findViewById(R.id.code_view);
        Options options = Options.Default.get(this);
        options.withLanguage(getIntent().getStringExtra("study_language"));
        options.withCode(getIntent().getStringExtra("study_code"));
        if(ApplicationGlobalSettings.app_theme){
            options.withTheme(ColorTheme.MONOKAI);
        }
        codeView.setOptions(options);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       getMenuInflater().inflate(R.menu.menu_study_code, menu);
       return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.run2:
            Intent intent = new Intent(this, CideRunJavaScriptActivity.class);
            intent.putExtra("project_file", "Study.js");
            intent.putExtra("project_name", "Study.js");
            intent.putExtra("code", getIntent().getStringExtra("study_code"));
            startActivity(intent);
            break;
            
            case R.id.copycode2:
            
                copyData(getIntent().getStringExtra("study_code"));//复制代码
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                
            break;
            
            case R.id.selector_settings2:
            startActivity(new Intent(this, SettingsActivity.class));
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void copyData(String str) {
        //获取剪切板管理器
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //创建普通字符型ClipData
        ClipData cd = ClipData.newPlainText("title", str);
        //将ClipData内容复制到剪切板
        cm.setPrimaryClip(cd);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesManager.removeActivity(this);
    }
    
}
