package com.xiaohan.seven.cide.interfaces;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.PopupWindow;
import com.xiaohan.seven.cide.dialog.AppCompatDialog;

public class WebViewJavaScriptInterface {
    
    private Context context;
    
    private AppCompatDialog alert;
    
    public WebViewJavaScriptInterface(Context context, AppCompatDialog alert) {
        this.context = context;
        this.alert = alert;
    }
    
    @JavascriptInterface
    public Context getContext() {
        return this.context;
    }
    
    @JavascriptInterface
    public AppCompatDialog getAlert() {
        return this.alert;
    }
    
}
