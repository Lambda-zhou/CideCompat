package com.xiaohan.seven.cide.dialog;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.xiaohan.seven.cide.R;
import org.jetbrains.annotations.NotNull;
import android.widget.LinearLayout;
import android.view.View;
import android.support.v7.widget.LinearLayoutCompat.LayoutParams;

public class AppCompatInputView {
    
    private Activity context;
    
    private LinearLayoutCompat layout;
    
    public AppCompatInputView(Activity context) {
        this.context = context;
        layout = new LinearLayoutCompat(context);
        layout.setOrientation(1);
        layout.setLayoutParams(new LinearLayoutCompat.LayoutParams(-1, (int)(getHeight() * 0.05)));
        
        CardView card = new CardView(context);
        card.setCardBackgroundColor(Color.parseColor("#FFF7F7F7"));
        card.setRadius(28);
        card.setCardElevation(0);
        LinearLayoutCompat.LayoutParams params = parseIntLayoutParams(getWidth() * 0.85, getHeight() * 0.045, true);
        params.gravity = Gravity.CENTER;
        card.setLayoutParams(params);
        layout.addView(card);
        
        AppCompatEditText editor = new AppCompatEditText(context);
        editor.setText("");
        editor.setSingleLine(true);
        editor.setBackgroundDrawable(new ColorDrawable(0));
        CardView.LayoutParams params2 = new CardView.LayoutParams((int)(getWidth() * 0.82), (int)(getHeight() * 0.42));
        params2.gravity = Gravity.CENTER;
        editor.setTextColor(Color.parseColor("#000000"));
        editor.setLayoutParams(params2);
        card.addView(editor);
    }
    
    public AppCompatInputView(Activity context, String hint) {
        this.context = context;
        layout = new LinearLayoutCompat(context);
        layout.setOrientation(1);
        layout.setLayoutParams(new LinearLayoutCompat.LayoutParams(-1, (int)(getHeight() * 0.05)));

        CardView card = new CardView(context);
        card.setCardBackgroundColor(Color.parseColor("#FFF7F7F7"));
        card.setRadius(28);
        card.setCardElevation(0);
        LinearLayoutCompat.LayoutParams params = parseIntLayoutParams(getWidth() * 0.85, getHeight() * 0.045, true);
        params.gravity = Gravity.CENTER;
        card.setLayoutParams(params);
        layout.addView(card);

        AppCompatEditText editor = new AppCompatEditText(context);
        editor.setSingleLine(true);
        editor.setText("");
        editor.setHint(hint);
        editor.setBackgroundDrawable(new ColorDrawable(0));
        CardView.LayoutParams params2 = new CardView.LayoutParams((int)(getWidth() * 0.82), (int)(getHeight() * 0.42));
        params2.gravity = Gravity.CENTER;
        editor.setTextColor(Color.parseColor("#000000"));
        editor.setLayoutParams(params2);
        card.addView(editor);
    }
    
    public LinearLayoutCompat create() {
        return layout;
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
    
}
