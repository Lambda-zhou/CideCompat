package com.xiaohan.seven.cide.dialog;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import com.xiaohan.seven.cide.interfaces.OnCreateProjectListener;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;
import android.support.v7.widget.AppCompatEditText;
import android.content.DialogInterface;
import com.xiaohan.seven.cide.tools.NewFileUtils;

public class NewHtmlProjectDialog {
    
    private Activity context;
    
    private String path;
    
    private AlertDialog ad;
    
    private OnCreateProjectListener onCreate = new OnCreateProjectListener(){

        @Override
        public void onCreate(String path) {
            
        }
        
    };
    
    public NewHtmlProjectDialog(Activity context) {
        this.context = context;
    }
    
    public void show(final String path) {
        this.path = path;
		
		final AppCompatEditText editor = new AppCompatEditText(context);
		editor.setHint("项目名称");
        
        ad = new AlertDialog.Builder(context)
            .setTitle("新建HTML5项目")
            .setView(editor)
			.setNegativeButton("确定", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2) {
					String result = NewFileUtils.newHtmlProject(context, editor.getText().toString(),  path);
                    onCreate.onCreate(result);
                    ad.dismiss();
				}
				
			})
            .show();
        ad.setCanceledOnTouchOutside(true);
        
    }
    
    public void setOnCreateProjectListener(OnCreateProjectListener onCreate) {
        this.onCreate = onCreate;
    }
    
    
}
