package com.xiaohan.seven.cide;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.myopicmobile.textwarrior.common.LanguageJavascript;
import com.myopicmobile.textwarrior.language.AndroidLanguage;
import com.myopicmobile.textwarrior3.common.LanguageLua;
import com.xiaohan.seven.cide.language.ProjectLanguage;
import com.xiaohan.seven.cide.tools.ApplicationUtils;
import com.xiaohan.seven.cide.view.AppCompatToast;
import com.xiaohan.seven.cide.view.TextEditor;
import tiiehenry.code.view.CodeEditor;
import tiiehenry.code.language.objectivec.ObjectiveCLanguage;
import tiiehenry.code.language.csharp.CSharpLanguage;
import tiiehenry.code.language.php.PHPLanguage;
import com.myopicmobile.textwarrior4.common.LanguageHtml;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;
import android.graphics.Color;
import com.xiaohan.seven.cide.view.BottomColumnView;



/*
 项目名：代码笔记
 包名：com.error.codenote
 文件名：EdiCodeFragment
 创建者：梦雪
 创建者QQ：2487686673
 创建时间：2018-11-09 12:07
 描述：编辑代码 
 */
public class EditActivity extends BaseActivity {
    private TextEditor content_edit;//内容编辑框
    private com.dream.highlighteditor.editor.TextEditor content_edit2;
	com.muc.wide.Widget.TextEditor htmlEditor;
    private Toolbar toolbar;
    private Intent intent;
    private ProjectLanguage language;
    private LinearLayout layout;
    private AlertDialog dialog;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Implement this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcode);

        if(savedInstanceState != null){
            code = savedInstanceState.getString("code");
        }

        layout = findViewById(R.id.activity_editcodeLinearLayout);
        
        intent = getIntent();
        //ProjectLanguage language = intent.getSerializableExtra();
        content_edit = findViewById(R.id.jseditor);
		
        if(code == null){
            content_edit.setText(intent.getStringExtra("project_text"));
        }else {
            content_edit.setText(code);
        }
		
        content_edit2 = new com.dream.highlighteditor.editor.TextEditor(this);
        if(code == null){
            content_edit2.setText(intent.getStringExtra("project_text"));
        }else {
            content_edit2.setText(code);
        }
		
		htmlEditor = new com.muc.wide.Widget.TextEditor(this);
		if(ApplicationGlobalSettings.app_theme){
            layout.setBackgroundColor(Color.parseColor("#303131"));
            htmlEditor.setDark(false);
            content_edit2.setDark(false);

            content_edit.setDark(true);
        }
		
        content_edit2.setDark(true);

        content_edit.setDark(true);



        this.language = ProjectLanguage.valueOf(((ProjectLanguage)intent.getSerializableExtra("project_language")).name());
        setAutoLanguage();
        toolbar = findViewById(R.id.fragment_editor_Toolbar);
        //初始化Toolbar

        initToolbar();
        //初始化编辑器
        initEditor();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("code", content_edit.getText().toString());
    }

    private void setAutoLanguage() {

        switch(this.language){
            case AndJS:
                content_edit.setLanguage(LanguageJavascript.getInstance());
                break;

            case JavaScript:
                content_edit.setLanguage(LanguageJavascript.getInstance());
                break;

            case Java:
                layout.removeView(content_edit);
                layout.addView(content_edit2);
                break;

            case C:
                layout.removeView(content_edit);
                com.luoye.simpleC.view.TextEditor cEditor = new com.luoye.simpleC.view.TextEditor(this);
                cEditor.setLanguage(com.myopicmobile.textwarrior3.common.LanguageC.getInstance());
                layout.addView(cEditor);
                break;

            case C_Enhance:
                layout.removeView(content_edit);
                com.luoye.simpleC.view.TextEditor cppEditor = new com.luoye.simpleC.view.TextEditor(this);
                layout.addView(cppEditor);
                break;

            case Objective_C:
                layout.removeView(content_edit);
                CodeEditor objCEditor = new CodeEditor(this);
                objCEditor.setLanguage(ObjectiveCLanguage.getInstance());
                layout.addView(objCEditor);
                break;

            case Csharp:
                layout.removeView(content_edit);
                CodeEditor csharpEditor = new CodeEditor(this);
                csharpEditor.setLanguage(CSharpLanguage.getInstance());
                layout.addView(csharpEditor);
                break;

            case Php:
                layout.removeView(content_edit);
                CodeEditor phpEditor = new CodeEditor(this);
                phpEditor.setLanguage(PHPLanguage.getInstance());
                layout.addView(phpEditor);
                break;

            case Lua:
                layout.removeView(content_edit);
                com.luoye.simpleC.view.TextEditor luaEditot = new com.luoye.simpleC.view.TextEditor(this);
                luaEditot.setLanguage(LanguageLua.getInstance());
                layout.addView(luaEditot);
                break;

            case Python:

                break;
	         
		    case HTML:
				layout.removeView(content_edit);
                if(code == null){
                    htmlEditor.setText(intent.getStringExtra("project_text"));
                }else {
                    htmlEditor.setText(code);
                }
                layout.addView(htmlEditor);
				break;

        }

    }

    private void initToolbar() {
        // TODO: Implement this method
        toolbar.setTitle(String.format(getString(R.string.activity_editting), intent.getStringExtra("project_name")));
        setSupportActionBar(toolbar);
    }

    private void initEditor() {
        // TODO: Implement this method
        //把焦点放到editor
        content_edit.requestFocus();
        //获取root布局
		BottomColumnView bcv = new BottomColumnView(this, new String[]{"{", "}"});
		bcv.setOnTextClickListener(new BottomColumnView.OnTextClickListener(){

				@Override
				public void onTextClick(View p1, String p2) {
					content_edit.paste(p2);
				}
				
		});
		bcv.setAdapter();
    }


    //菜单事件监听
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TODO: Implement this method
        //获取编辑框内容
        String content = content_edit.getText().toString();
        switch (item.getItemId()) {
                //撤销
            case R.id.undo:
                content_edit.undo();
                break;
                //重做
            case R.id.redo:
                content_edit.redo();
                break;
            case R.id.selector_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.savecode:
                new Thread(new Runnable(){

                        @Override
                        public void run() {
                            ApplicationUtils.saveFileToSD(intent.getStringExtra("project_file"), content_edit.getText().toString());
                            Looper.prepare();
                            Toast.makeText(EditActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }).start();
                break;
            case R.id.run:
                ApplicationUtils.saveFileToSD(intent.getStringExtra("project_file"),content_edit.getText().toString());

                autoRunCode();
                break;
                //复制代码片段
            case R.id.copycode:
                if (content.isEmpty()) {
                    Toast.makeText(this, "代码为空，无需复制", Toast.LENGTH_SHORT).show();
                } else {
                    copyData(content);//复制代码
                    Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void autoRunCode() {
        switch(this.language){
            case JavaScript:
                AppCompatToast.makeText(EditActivity.this, "已自动保存项目", 0, 1);

                Intent intent = new Intent(this, CideRunJavaScriptActivity.class);
                intent.putExtra("code", content_edit.getText().toString());
                intent.putExtra("project_name", this.intent.getStringExtra("project_name"));
                intent.putExtra("project_file", this.intent.getStringExtra("project_file"));
                startActivity(intent);
                break;
            case AndJS:
                AppCompatToast.makeText(EditActivity.this, "已自动保存项目", 0, 1);

                Intent intent2 = new Intent(this, CideRunAndJSActivity.class);
                intent2.putExtra("code", content_edit.getText().toString());
                intent2.putExtra("project_name", this.intent.getStringExtra("project_name"));
                intent2.putExtra("project_file", this.intent.getStringExtra("project_file"));
                startActivity(intent2);
                break;
		    case HTML:
				AppCompatToast.makeText(EditActivity.this, "已自动保存项目", 0, 1);

                Intent intent3 = new Intent(this, CideRunHtmlActivity.class);
                intent3.putExtra("code", htmlEditor.getText().toString());
                intent3.putExtra("project_name", this.intent.getStringExtra("project_name"));
                intent3.putExtra("project_file", this.intent.getStringExtra("project_file"));
                startActivity(intent3);
			    break;
            default:
                AppCompatToast.makeText(this, "暂不支持执行此类型代码, 文件已保存", 1, 0);
                break;
        }
    }



    //复制到剪切板
    private void copyData(String str) {
        //获取剪切板管理器
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //创建普通字符型ClipData
        ClipData cd = ClipData.newPlainText("title", str);
        //将ClipData内容复制到剪切板
        cm.setPrimaryClip(cd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: Implement this method
        getMenuInflater().inflate(R.menu.menu_editcode_action, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}


