package com.xiaohan.seven.cide.api;
import com.xiaohan.seven.cide.CideRunJavaScriptActivity;
import android.widget.TextView;
import android.graphics.Color;

public class CideOpenApi {
    
    public static void print(String tag, String msg) {
        TextView tv = CideRunJavaScriptActivity.getLog();
        tv.setTextColor(Color.BLACK);
        tv.append("<" + tag + ">" + " " + msg + "\n");
	}
    
}
