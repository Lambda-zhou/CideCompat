package com.xiaohan.seven.cide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.tencent.smtt.sdk.WebView;
import com.xiaohan.seven.cide.view.AppCompatWebView;
import com.xiaohan.seven.cide.view.TextEditor;
import com.myopicmobile.textwarrior.common.Language;
import com.myopicmobile.textwarrior.common.LanguageNonProg;

public class TestActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextEditor editor = new TextEditor(this);
        editor.setLanguage(LanguageNonProg.getInstance());
        editor.addUserWord("DOCTYPE html");
        setContentView(editor);
    }
    
}
