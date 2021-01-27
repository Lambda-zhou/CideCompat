package com.xiaohan.seven.cide.dialog;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class AppCompatDialog{
    
    private Activity context;
	
	private android.support.v7.app.AppCompatDialog dialog;
	
	private CardView window;
	
	private LinearLayoutCompat layout;
    
    private CardView toolLayout;
    
    private TextView titleLayout;
    
    private LinearLayoutCompat toolLay;
    
    private TextView message;
    
    private LinearLayoutCompat bottomLay;
    
    private TextView right,
    cancel;
    
    private Boolean isSetFirstButton = false,
    isSetSecondButton = false;
    
    private AppCompatDialog me;
    
    private int h;
    
    private int w;
    
    private int h2 = 100;
    
    private int toolH;
    
    public static final int POSTIVE_BUTTON = 0;
    
    public static final int NEGATIVE_BUTTON = 1;
    
    public AppCompatDialog(Activity context) {
        this.context = context;
        android.support.v7.app.AppCompatDialog dialog = new android.support.v7.app.AppCompatDialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        me = this;
		window = new CardView(context);
        w = (int)(getWidth() * 0.88);
        h = (int)(getHeight() * 0.25);
        toolH = (int)(getHeight() * 0.05);
        
        window.setCardBackgroundColor(Color.parseColor("#ffffff"));
        window.setRadius(28);
        window.setCardElevation(5);
        
        layout = new LinearLayoutCompat(context);
        layout.setOrientation(1);
        window.addView(layout);
        
        titleLayout = new TextView(context);
        titleLayout.setText("");
        titleLayout.setTextColor(Color.parseColor("#000000"));
        titleLayout.setGravity(Gravity.CENTER);
        titleLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        titleLayout.setLayoutParams(parseIntLayoutParams(-1, 100, true));
        layout.addView(titleLayout);
        
        toolLayout = new CardView(context);
        toolLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        toolLayout.setPadding(15, 15, 15, 15);
        
        layout.addView(toolLayout);
        
        NestedScrollView scrollview = new NestedScrollView(context);
        scrollview.setLayoutParams(parseIntLayoutParams(-1, -1, true));
        toolLayout.addView(scrollview);
        
        toolLay = new LinearLayoutCompat(context);
        toolLay.setOrientation(1);
        toolLay.setLayoutParams(parseIntLayoutParams(-1, -2));
        scrollview.addView(toolLay);
        
        //toolLay布局内部
        message = new TextView(context);
        message.setLayoutParams(parseIntLayoutParams(-1, -1));
        message.setText("");
        toolLay.addView(message);
        
        CardView bottomLayout = new CardView(context);
        bottomLayout.setCardBackgroundColor(Color.parseColor("#ffffff"));
        bottomLayout.setLayoutParams(parseIntLayoutParams(-1, toolH));
        layout.addView(bottomLayout);
        
        bottomLay = new LinearLayoutCompat(context);
        bottomLay.setOrientation(0);
        bottomLayout.addView(bottomLay);
        
		dialog.setContentView(window);
        this.dialog = dialog;
    }
	
	public int getWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return  metrics.widthPixels;
    }
    
    protected ViewGroup.LayoutParams parseIntLayoutParams(double params, double params2) {
        return new ViewGroup.LayoutParams((int)params, (int)params2);
    }
    
    protected LinearLayoutCompat.LayoutParams parseIntLayoutParams(double params, double params2, Boolean isUse) {
        return new LinearLayoutCompat.LayoutParams((int)params, (int)params2);
    }
	
	public int getHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return  metrics.heightPixels;
    }
    
    public AppCompatDialog setWidth(int width) {
        w = width;
        return me;
    }
    
    public AppCompatDialog setHeight(int height) {
        h = height;
        return me;
    }
    
    public AppCompatDialog setCancelable(boolean canCancel) {
        dialog.setCancelable(canCancel);
        return me;
    }

    public CardView getWindow() {
		return window;
	}
	
	public AppCompatDialog setBackgroundColor(int color) {
		window.setCardBackgroundColor(color);
        return me;
	}
	
	public int getBackgroundColor() {
		return window.getCardBackgroundColor().getDefaultColor();
	}
	
	public void show() {
        create();
		dialog.show();
	}
	
	public AppCompatDialog create() {
        
		dialog.create();
        ViewGroup.LayoutParams params = window.getLayoutParams();
        params.width = w;
        params.height = h;
        
        ViewGroup.LayoutParams params2 = toolLayout.getLayoutParams();
        params2.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params2.height = h - h2 - toolH;
        
        ViewGroup.LayoutParams params3 = bottomLay.getLayoutParams();
        params3.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params3.height = ViewGroup.LayoutParams.MATCH_PARENT;
        
        return me;
	}
	
	public AppCompatDialog setTitle(String title) {
        
		this.titleLayout.setText(title);
        return me;
	}
	
	public String getTitle() {
		return this.titleLayout.getText().toString();
	}
	
	public AppCompatDialog setTitleColor(int color) {
		titleLayout.setTextColor(color);
        return me;
	}
	
	public int getTitleColor() {
		return titleLayout.getTextColors().getDefaultColor();
	}
	
	public AppCompatDialog setTitleSize(float size) {
		titleLayout.setTextSize(size);
        return me;
	}
	
	public float getTitleSize() {
		return titleLayout.getTextSize();
	}
	
	public AppCompatDialog setMessage(String message) {
		if(this.message != null){
			this.message.setText(message);
		}
        return me;
	}
	
	public CharSequence getMessage() {
		if(this.message != null){
			return this.message.getText();
		}
		
		return null;
	}
    
    public AppCompatDialog setSize(float size) {
        this.message.setTextSize(size);
        return me;
    }
    
    public float getSize() {
        return this.message.getTextSize();
    }
    
    public AppCompatDialog setPostiveButton(String text, OnClickListener onClick) {
        
        
            if(isSetFirstButton == true && isSetSecondButton == true){
                ((TextView)bottomLay.getChildAt(0)).setText(text);
                ((TextView)bottomLay.getChildAt(0)).setOnClickListener(onClick);
                right = ((TextView)bottomLay.getChildAt(0));
                isSetFirstButton = true;
                return me;
            }
            if(isSetFirstButton == false && isSetSecondButton == true){
            TextView v1 = ((TextView)bottomLay.getChildAt(0));
            v1.setLayoutParams(parseIntLayoutParams(getWidth() * 0.44 - 5, -1, true));
            bottomLay.removeView(v1);
            
            //bottomLayout内部
            TextView right = new TextView(context);
            right.setText(text);
            right.setTextColor(Color.parseColor("#000000"));
            if(onClick != null){
                right.setOnClickListener(onClick);
            }
            right.setGravity(Gravity.CENTER);
            right.setLayoutParams(parseIntLayoutParams(getWidth() * 0.44 - 5, -1, true));
            bottomLay.addView(right);
            
            TextView split = new TextView(context);
            split.setLayoutParams(parseIntLayoutParams(5, -1, true));
            split.setText("|");
            split.setTextSize(10);
            split.setTextColor(Color.parseColor("#000000"));
            split.setGravity(Gravity.CENTER);
            bottomLay.addView(split);
            
            bottomLay.addView(v1);
            
            this.right = right;
            isSetFirstButton = true;
            return me;
        }
        
        TextView right = new TextView(context);
        right.setText(text);
        right.setTextColor(Color.parseColor("#000000"));
        if(onClick != null){
            right.setOnClickListener(onClick);
        }
        right.setGravity(Gravity.CENTER);
        right.setLayoutParams(parseIntLayoutParams(-1, -1, true));
        bottomLay.addView(right);
        this.right = right;
        isSetFirstButton = true;
        return me;
        
    }
    
    public AppCompatDialog setNegativeButton(String text, OnClickListener onClick) {
        
        
            if(isSetSecondButton == true && isSetFirstButton == true){
                ((TextView)bottomLay.getChildAt(1)).setText(text);
                ((TextView)bottomLay.getChildAt(1)).setOnClickListener(onClick);
                cancel = ((TextView)bottomLay.getChildAt(1));
                isSetSecondButton = true;
                return me;
            }else if(isSetFirstButton == true && isSetSecondButton == false){
                TextView v1 = ((TextView)bottomLay.getChildAt(0));
                v1.setLayoutParams(parseIntLayoutParams(getWidth() * 0.44 - 5, -1, true));
                
                TextView split = new TextView(context);
                split.setLayoutParams(parseIntLayoutParams(5, -1, true));
                split.setText("|");
                split.setTextSize(10);
                split.setTextColor(Color.parseColor("#000000"));
                split.setGravity(Gravity.CENTER);
                bottomLay.addView(split);
                
                TextView cancel = new TextView(context);
                cancel.setText("取消");
                cancel.setGravity(Gravity.CENTER);
                cancel.setLayoutParams(parseIntLayoutParams(getWidth() * 0.44 - 5, -1, true));
                if(onClick != null){
                    cancel.setOnClickListener(onClick);
                }
                bottomLay.addView(cancel);
                this.cancel = cancel;
                isSetSecondButton = true;
                return me;
                
            }
        
        
        TextView cancel = new TextView(context);
        cancel.setText(text);
        cancel.setGravity(Gravity.CENTER);
        cancel.setLayoutParams(parseIntLayoutParams(-1, -1, true));
        if(onClick != null){
            cancel.setOnClickListener(onClick);
        }
        bottomLay.addView(cancel);
        this.cancel = cancel;
        isSetSecondButton = true;
        return me;
    }
    
    public AppCompatDialog setContentView(View view) {
        if(view != null) {
            toolLay.addView(view);
        }
        return me;
    }
    
    public AppCompatDialog setViewMode(Boolean mode) {
        if(mode) {
            window.setLayoutParams(parseIntLayoutParams(getWidth() * 0.88, getHeight() * 0.45));
        }
        
        return me;
    }
    
    public AppCompatDialog setViewMode(Boolean mode, int width, int height) {
        if(mode) {
            window.setLayoutParams(parseIntLayoutParams(width, height));
            h = height;
        }
        return me;
    }
    
    public TextView[] getButtons() {
        return new TextView[]{
            this.right,
            this.cancel//flush
        };
    }
    
    public void dismiss() {
        if(dialog != null){
        dialog.dismiss();
        }
    }
    
}
