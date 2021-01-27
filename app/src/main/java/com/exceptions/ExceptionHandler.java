package com.exceptions;
import android.content.Context;
import android.content.Intent;
import com.xiaohan.seven.cide.ExceptionActivity;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler defaultHandler;
    public static ExceptionHandler exceptionHandler;
    private  boolean inited = false;
    private Context context;
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StringWriter sw= new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        e.printStackTrace(pw);
		String errlog= sw.toString();
        Intent i = new Intent(context,ExceptionActivity.class);
		String err = "[Error-Message]" + e.getMessage() + "\n" + 
		"[Exception-Class]" + e.getClass().getSimpleName() + "\n" +
		"[Error-LocalMessage]" + e.getLocalizedMessage() + "\n" +
		"[Error-State]" + e.getStackTrace()[0].toString() + "\n" +
        "[Error-hashCode]" + e.hashCode() + "\n" +
		"[Error-Thread]" + t.currentThread().getName() + "\n" + 
		"[Error-PID]" + android.os.Process.myPid()  + "\n" +
		"[Error-Thread-Class]" + t.currentThread().getClass().getSimpleName() + "\n" + 
        "[SDK]" + android.os.Build.VERSION.SDK_INT + "\n";
		i.putExtra("ERR", err);
        i.putExtra("ERROR",errlog);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    public ExceptionHandler(){}
    public static ExceptionHandler getInstance(){
        if(exceptionHandler==null){
            exceptionHandler=new ExceptionHandler();
        }
        return  exceptionHandler;
    }

    public void init(Context context){
        if(inited)return;
        this.context=context;
        defaultHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
