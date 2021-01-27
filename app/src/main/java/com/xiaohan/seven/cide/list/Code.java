package com.xiaohan.seven.cide.list;

public class Code {

    private String title;
    private String message;
    private String code;
    private String language;

    public Code(String title, String message, String code, String language) {
        this.title = title;
        this.message = message;
        this.code = code;
        this.language = language;
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

}
