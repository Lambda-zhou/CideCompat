package com.applications;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.exceptions.ExceptionHandler;
import io.github.kbiakov.codeview.classifier.CodeProcessor;
import com.tencent.smtt.sdk.QbSdk;

public class ApplicationUtils extends MultiDexApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        ExceptionHandler.getInstance().init(context);
        CodeProcessor.init(context);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback(){

            @Override
            public void onCoreInitFinished() {
                
            }

            @Override
            public void onViewInitFinished(boolean p1) {
                
            }
       };
       QbSdk.setDownloadWithoutWifi(true);
       QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    @Override
    protected final void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return context;
    }

}
