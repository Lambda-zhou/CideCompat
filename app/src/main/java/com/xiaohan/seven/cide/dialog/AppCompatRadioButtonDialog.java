package com.xiaohan.seven.cide.dialog;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.CompoundButton;
import java.util.ArrayList;
import java.util.List;
import com.xiaohan.seven.cide.interfaces.OnItemCheckListener;
import org.jetbrains.annotations.NotNull;

public class AppCompatRadioButtonDialog {
    
    private Activity context;
    
    private String[] items;
    
    private RadioGroup group;
    
    private List<RadioButton> radioButtons = new ArrayList<RadioButton>();
    
    private RadioButton thisRadioButton;
    
    private AppCompatDialog dialog;
    
    private OnItemCheckListener onItemCheck = new OnItemCheckListener(){

        @Override
        public void onItemCheck(RadioButton radioButton) {
            dialog.dismiss();
        }
        
    };
    
    private AppCompatRadioButtonDialog me;
    
    public AppCompatRadioButtonDialog(Activity context) {
        me = this;
        dialog = new AppCompatDialog(context);
        dialog.setHeight((int)(dialog.getHeight() * 0.85));
        this.context = context;
        dialog.setPostiveButton("确定", new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    onItemCheck.onItemCheck(thisRadioButton);
                    dialog.dismiss();
                }
                
        });
    }
    
    public AppCompatRadioButtonDialog setItems(String[] items) {
        this.items = items;
        return me;
    }
    
    public AppCompatRadioButtonDialog setTitle(@NotNull String title) {
        dialog.setTitle(title);
        return me;
    }
    
    public AppCompatRadioButtonDialog setMessage(@NotNull String message) {
        dialog.setMessage(message);
        return me;
    }

    public void show() {
        
        final LinearLayoutCompat linearlayout = new LinearLayoutCompat(context);
        linearlayout.setOrientation(1);
        group = new RadioGroup(context);
        linearlayout.addView(group);
        dialog.setContentView(linearlayout);
        dialog.show();
        new Thread(new Runnable(){

                @Override
                public void run() {
                    for(int i = 0;i < items.length;i++){
                        final int thisValue = i;
                        context.runOnUiThread(new Runnable(){

                                @Override
                                public void run() {
                                    RadioButton radioButton = new RadioButton(context);
                                    radioButton.setText(items[thisValue]);
                                    radioButton.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener(){

                                            @Override
                                            public void onCheckedChanged(CompoundButton p1, boolean p2) {
                                                thisRadioButton = (RadioButton)p1;
                                            }
                                            
                                    });
                                    group.addView(radioButton);
                                    radioButtons.add(radioButton);
                                }
                                
                        });
                    }
                }
                
        }).start();
    }
    
    
    public AppCompatRadioButtonDialog setOnItemCheckListener(@NotNull OnItemCheckListener onItemCheck) {
        this.onItemCheck = onItemCheck;
        return me;
    }
    
    public List<RadioButton> getRadioButtons() {
        return this.radioButtons;
    }
    
    public RadioGroup getRadioGroup() {
        return this.group;
    }
    
}
