package com.xiaohan.seven.cide.list;
import android.graphics.Bitmap;

public class Study {
    
    private String title;
    private String message;
    private String code;
    private String language;
    private Bitmap image;
    
    public Study(String title, String message, Bitmap image, String language, String code) {
        this.title = title;
        this.message = message;
        this.code = code;
        this.language = language;
        this.image = image;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public Bitmap getImage() {
        return this.image;
    }
    
}
