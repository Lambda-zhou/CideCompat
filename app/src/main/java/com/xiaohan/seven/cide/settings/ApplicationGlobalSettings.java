package com.xiaohan.seven.cide.settings;

import com.xiaohan.seven.cide.engine.JavaScriptEngine;
import java.io.File;
import com.xiaohan.seven.cide.MainActivity;
import android.content.SharedPreferences;
import android.graphics.Color;

public abstract class ApplicationGlobalSettings {
    
    private ApplicationGlobalSettings(){}
    
    private static JavaScriptEngine ScriptEngine = JavaScriptEngine.Rhino;
    
    private static Boolean phone_horizonal = false;
    
    public static Boolean app_theme = false;
    
    public static Boolean isUseLocalhost = false;
    
    private static MainActivity context;
    
    public static File storagePath = new File(android.os.Environment.getExternalStorageDirectory().getAbsoluteFile().toString() + "/" + "CideCompat" + "/" + "projects");
    
    public static void setEngine(JavaScriptEngine engine) {
        ScriptEngine = engine;
    }
    
    public static JavaScriptEngine getEngine() {
        return ScriptEngine;
    }
    
    public static void setOrientation(Boolean orientation) {
        phone_horizonal = orientation;
    }
    
    public static Boolean getOrientation() {
        return phone_horizonal;
    }
    
    public static void setContext(MainActivity _this) {
        context = _this;
    }
    
    public static void saveData(String name, String data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, data);
        editor.commit();
    }
    
    public static String getData(String name) {
        return context.getSharedPreferences().getString(name, "");
    }
    
    public static void saveData(String name, Boolean data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, data);
        editor.commit();
    }
    
    public static Boolean getData(String name, Boolean defaultValue) {
        return MainActivity._this.getSharedPreferences().getBoolean(name, defaultValue);
    }
    
}
