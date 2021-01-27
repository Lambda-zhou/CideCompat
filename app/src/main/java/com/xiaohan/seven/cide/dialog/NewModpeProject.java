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


/**
 * Created by Administrator on 2016/12/21.
 */

public class NewModpeProject implements CompoundButton.OnCheckedChangeListener {
    Activity context;
    AlertDialog ad;
    EditText name;
    CheckBox ctx,hook,cide,request;
    boolean ctxb = true,hookb = false, cideb = true, requestb = true;
    private OnCreateProjectListener onCreate = new OnCreateProjectListener(){

        @Override
        public void onCreate(String path) {

        }

    };

    public NewModpeProject(Activity context){
        this.context = context;
    }
    public void show(final String path){
        ad = new AlertDialog.Builder(context)
            .setTitle("新建Modpe文件")
            .setIcon(R.mipmap.ic_launcher)
            .setView(R.layout.new_modpe)
            .setNegativeButton("取消",null)
            .setPositiveButton("新建", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String result = NewFileUtils.newModpeProject(context, name.getText().toString(),  path, ctxb);
                    onCreate.onCreate(result);
                    ad.dismiss();

                }
            })
            .show();
        this.name = ad.getWindow().findViewById(R.id.new_mc_js_name2);
        this.ctx = ad.getWindow().findViewById(R.id.new_mc_js_cb12);
        this.ctx.setOnCheckedChangeListener(this);
        ad.setCanceledOnTouchOutside(false);
    }

    public void setOnCreateProjectListener(OnCreateProjectListener onCreate) {
        this.onCreate = onCreate;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ctxb = ctx.isChecked()?true:false;
    }
}
