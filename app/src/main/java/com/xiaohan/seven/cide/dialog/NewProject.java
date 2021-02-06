package com.xiaohan.seven.cide.dialog;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ExpandableListView;
import com.xiaohan.seven.cide.R;
import com.xiaohan.seven.cide.adapter.NewProjectListViewAdapter;
import com.xiaohan.seven.cide.interfaces.OnCreateProjectListener;

/**
 * Created by Administrator on 2016/12/20.
 */

public class NewProject implements ExpandableListView.OnChildClickListener {
    Activity context;
    public static AlertDialog ad;
    ExpandableListView exlv ;
    NewProjectListViewAdapter npal;
    static String[] firsti = {
        
        "Android - 类",
        "编程语言 - 类",
        "MinecraftMod - 类"
        
        };
    static String[][] secandi={
        
        {
            "AndJS",
            "Anml"
        },
        
        {
            
           "HTML"
        },
        
        {
            /*"Java",
            "JavaScript",
            "PHP",
            "Objective - C",
            "C++",
            "C",
            "VB.NET",
            "SQL",
            "Go",
            "Swift",
            "R"*/
            "modpe"
        }
        
        };
    String path;
    private OnCreateProjectListener onCreate = new OnCreateProjectListener(){

        @Override
        public void onCreate(String path) {
            
        }
    };
    
    public NewProject(Activity context){
        this.context = context;
        npal = new NewProjectListViewAdapter(context);
    }
    public static void setData(String[] first,String[][] secand){
        firsti = first;
        secandi = secand;
    }
    public void show(String path) {
        this.path = path;
        ad = new AlertDialog.Builder(context)
                .setTitle("新建工程类型")
                .setView(R.layout.newproject)
                .show();
        ad.setCanceledOnTouchOutside(true);
        exlv = ad.getWindow().findViewById(R.id.newprojectlistview);
        exlv.setAdapter(npal);
        npal.setData(firsti,secandi);
        exlv.deferNotifyDataSetChanged();
        exlv.setOnChildClickListener(this);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if(groupPosition == 0){
            switch (childPosition){
                case 0:
                NewAndJSProject project = new NewAndJSProject(context);
                project.setOnCreateProjectListener(this.onCreate);
                project.show(path);
                break;
                
                case 1:
                NewAnmlProject anml = new NewAnmlProject(context);
                anml.setOnCreateProjectListener(this.onCreate);
                anml.show(path);
                break;
            }
        }else if(groupPosition == 1){
            switch (childPosition){
                case 0:
                NewHtmlProjectDialog project = new NewHtmlProjectDialog(context);
				project.setOnCreateProjectListener(this.onCreate);
				project.show(path);
                
                break;
                
                case 1:
                
                
                break;
            }
            
        }
       else if(groupPosition == 2){
            switch (childPosition){
                case 0:
                NewModpeProject project = new NewModpeProject(context);
                project.setOnCreateProjectListener(this.onCreate);
                project.show(path);
                    
                break;
                
                case 1:
                
                
                break;
            }
        }
        ad.dismiss();
        return false;
    }
    
    public void setOnCreateProjectListener(OnCreateProjectListener onCreate) {
       this.onCreate = onCreate;
    }
    
}
