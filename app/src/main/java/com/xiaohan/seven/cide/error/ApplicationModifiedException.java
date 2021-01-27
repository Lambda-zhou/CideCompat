package com.xiaohan.seven.cide.error;

public class ApplicationModifiedException extends RuntimeException {
    
    public ApplicationModifiedException(String message) {
        super(message);
    }
    
    public ApplicationModifiedException() {
        super("App is modifed, you must repair it.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
