package com.xl.codeedit;

import android.app.Activity;
import android.os.Bundle;
import com.luoye.simpleC.view.TextEditor;
import com.xiaohan.seven.cide.R;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        
		TextEditor edit = new TextEditor(this);
		setContentView(edit);
		
		
    }
}
