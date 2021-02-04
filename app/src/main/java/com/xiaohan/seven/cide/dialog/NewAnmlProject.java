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

public class NewAnmlProject implements CompoundButton.OnCheckedChangeListener {
    
    Activity context;
    AlertDialog ad;
    EditText name, packageName, versionCode, versionName;
    CheckBox jquary, vue, mdui;
    boolean jqueryb = false, vueb = false, mduib = true;
    private OnCreateProjectListener onCreate = new OnCreateProjectListener(){

        @Override
        public void onCreate(String path) {

        }

    };

    public NewAnmlProject(Activity context){
        this.context = context;
    }
    public void show(final String path){
        ad = new AlertDialog.Builder(context)
            .setTitle("新建Anml文件")
            .setIcon(R.mipmap.ic_launcher)
            .setView(R.layout.new_anml)
            .setNegativeButton("取消",null)
            .setPositiveButton("新建", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(name.getText().toString().equals("")||packageName.getText().toString().equals("")){
                        AppCompatToast.makeText(context, "请检查名称和包名是否填写!", 1, 1);
                        return;
                    }
                    String result = NewFileUtils.newAndJSProject(context, name.getText().toString(), path , packageName.getText().toString(), versionCode.getText().toString(), versionName.getText().toString(), hookb, cideb, requestb);
                    onCreate.onCreate(result);
                    ad.dismiss();

                }
            })
            .show();
        this.name = ad.getWindow().findViewById(R.id.new_mc_js_name);
        this.packageName = ad.getWindow().findViewById(R.id.new_mc_package_name);
        this.versionCode = ad.getWindow().findViewById(R.id.new_mc_project_version);
        this.versionName = ad.getWindow().findViewById(R.id.new_mc_project_version_name);
        this.jquary = ad.getWindow().findViewById(R.id.jquary);
        this.vue = ad.getWindow().findViewById(R.id.vuejs);
        this.mdui = ad.getWindow().findViewById(R.id.mdui);
        ad.setCanceledOnTouchOutside(false);
    }

    public void setOnCreateProjectListener(OnCreateProjectListener onCreate) {
        this.onCreate = onCreate;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        jqueryb = jquary.isChecked()?true:false;
        vueb = vue.isChecked()?true:false;
        mduib = mdui.isChecked()?true:false;
    }
    
}
