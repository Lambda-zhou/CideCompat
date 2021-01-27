package com.xiaohan.seven.cide.view;
import android.content.Context;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebViewClient;

public class AppCompatWebView {

    public static class Builder {
        
        private static Context mContext;
        
        public Builder(Context context) {
            mContext = context;
        }
        
        public WebView newInstance() {
            
            WebView webView = new WebView(mContext);
            webView.setWebChromeClient(new WebChromeClient(){});
            webView.setWebViewClient(new WebViewClient(){});
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);                    //支持Javascript 与js交互
            settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
            settings.setAllowFileAccess(true);                      //设置可以访问文件
            settings.setSupportZoom(false);                          //支持缩放
            settings.setBuiltInZoomControls(false);                  //设置内置的缩放控件
            settings.setUseWideViewPort(true);                      //自适应屏幕
            settings.setSupportMultipleWindows(true);               //多窗口
            settings.setDefaultTextEncodingName("UTF-8");            //设置编码格式
            settings.setAppCacheEnabled(true);
            settings.setDisplayZoomControls(false);
            settings.setDomStorageEnabled(true);
            settings.setAppCacheMaxSize(Long.MAX_VALUE);
            settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            
            return webView;
        }
        
    }

}
