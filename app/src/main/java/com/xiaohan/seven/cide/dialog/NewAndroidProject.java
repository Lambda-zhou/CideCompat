package com.xiaohan.seven.cide.dialog;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;
import com.xiaohan.seven.cide.interfaces.OnCreateProjectListener;
import com.xiaohan.seven.cide.list.AndroidProject;
import java.util.ArrayList;
import java.util.List;

public class NewAndroidProject {
    
    private Activity context;
    
    private String path;
    
    private AlertDialog ad;
    
    private OnCreateProjectListener onCreate = new OnCreateProjectListener(){

        @Override
        public void onCreate(String path) {
            
        }
        
    };
    
    public NewAndroidProject(Activity context) {
        this.context = context;
    }
    
    public void show(String path) {
        this.path = path;
        final ListView listItems = new ListView(context);
        List<AndroidProject> items = new ArrayList<AndroidProject>();
        
        
        
        ad = new AlertDialog.Builder(context)
            .setTitle("新建Android项目")
            .setView(listItems)
            .show();
        ad.setCanceledOnTouchOutside(true);
        
    }
    
    public void setOnCreateProjectListener(OnCreateProjectListener onCreate) {
        this.onCreate = onCreate;
    }
    
}
