package com.xiaohan.seven.cide.dialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.xiaohan.seven.cide.R;
import com.xiaohan.seven.cide.interfaces.OnCreateProjectListener;
import com.xiaohan.seven.cide.tools.NewFileUtils;
import com.xiaohan.seven.cide.view.AppCompatToast;


/**
 * Created by Administrator on 2016/12/21.
 */

public class NewAndJSProject implements CompoundButton.OnCheckedChangeListener {
    Activity context;
    AlertDialog ad;
    EditText name, packageName;
    CheckBox hook,cide,request;
    boolean hookb = false, cideb = true, requestb = true;
    private OnCreateProjectListener onCreate = new OnCreateProjectListener(){

        @Override
        public void onCreate(String path) {
            
        }
        
    };
    
    public NewAndJSProject(Activity context){
        this.context = context;
    }
    public void show(final String path){
        ad = new AlertDialog.Builder(context)
                .setTitle("新建AndJS文件")
                .setIcon(R.mipmap.ic_launcher)
                .setView(R.layout.new_andjs)
                .setNegativeButton("取消",null)
                .setPositiveButton("新建", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            if(name.getText().toString().equals("")||packageName.getText().toString().equals("")){
                            AppCompatToast.makeText(context, "请检查名称和包名是否填写!", 1, 1);
                            return;
                            }
                            String result = NewFileUtils.newAndJSProject(context, name.getText().toString(), path , packageName.getText().toString(), hookb, cideb, requestb);
                            onCreate.onCreate(result);
                            ad.dismiss();
                        
                    }
                })
                .show();
                this.name = ad.getWindow().findViewById(R.id.new_mc_js_name);
                this.packageName = ad.getWindow().findViewById(R.id.new_mc_package_name);
                this.hook = ad.getWindow().findViewById(R.id.new_mc_js_cb2);
                this.cide = ad.getWindow().findViewById(R.id.new_mc_js_cb3);
                this.request = ad.getWindow().findViewById(R.id.new_mc_js_cb4);
                this.hook.setOnCheckedChangeListener(this);
                this.cide.setOnCheckedChangeListener(this);
                this.request.setOnCheckedChangeListener(this);
        ad.setCanceledOnTouchOutside(false);
    }
    
    public void setOnCreateProjectListener(OnCreateProjectListener onCreate) {
        this.onCreate = onCreate;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              hookb = hook.isChecked()?true:false;
              cideb = cide.isChecked()?true:false;
              requestb = request.isChecked()?true:false;
    }
}
