package com.xiaohan.seven.cide.tools;
import android.os.Environment;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtils {

    private static String storage = Environment.getExternalStorageDirectory().getAbsoluteFile().toString();
    
    private static Boolean open = true;
    
    public static String getReport(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
    
    public static void reportSaveToSD(Throwable e) {
        String report = getReport(e);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(storage + "/log.crash"));
            bw.write(report);
            bw.flush();
            bw.close();
        } catch (IOException e2) {
            
        }
    }
    
}
