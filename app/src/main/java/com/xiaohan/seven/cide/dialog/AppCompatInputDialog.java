package com.xiaohan.seven.cide.dialog;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import org.jetbrains.annotations.NotNull;

public class AppCompatInputDialog {
    
    private Activity context;
    
    private AppCompatInputDialog me;
    
    private AppCompatDialog dialog;
    
    private LinearLayoutCompat layout;
    
    public AppCompatInputDialog(Activity context) {
        me = this;
        this.context = context;
        this.dialog = new AppCompatDialog(context);
        dialog.setHeight((int)(dialog.getHeight() * 0.85));
        layout = new LinearLayoutCompat(context);
        layout.setOrientation(1);
        dialog.setContentView(layout);
        dialog.setPostiveButton("确定", new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    dialog.dismiss();
                }
                
        });
    }
    
    public AppCompatInputDialog addView(View view) {
        return me;
    }
    
    public AppCompatInputDialog addInputView() {
        layout.addView(new AppCompatInputView(context).create());
        return me;
    }
    
    public AppCompatInputDialog addInputView(@NotNull String hint) {
        AppCompatInputView inputView = new AppCompatInputView(context, hint);
        layout.addView(inputView.create());
        return me;
    }
    
    public int getWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return  metrics.widthPixels;
   }

    public int getHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return  metrics.heightPixels;
    }
    
    public AppCompatInputDialog setTitle(String title) {
        dialog.setTitle(title);
        return me;
    }
    
    public AppCompatInputDialog setMessage(String message) {
        dialog.setMessage(message);
        return me;
    }
    
    public void show() {
        dialog.show();
    }
    
}
