package com.xiaohan.seven.cide.view;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.xiaohan.seven.cide.R;
import android.support.v7.app.AppCompatActivity;

public class AppCompatToast {
	
	public static void makeText(final Context context,final String str,final int time,final int type){
		((AppCompatActivity)context).runOnUiThread(new Runnable(){

				@Override
				public void run() {
					LayoutInflater inflater = LayoutInflater.from(context);
					View layout = inflater.inflate(R.layout.toast,null);
					CardView cv = layout.findViewById(R.id.toastcardview);
					if(type==0){
                        
					}else{
						cv.setCardBackgroundColor(Color.RED);
					}

					TextView toast = layout.findViewById(R.id.toast);
					toast.setText(str);
					toast.setTextColor(Color.WHITE);
					android.widget.Toast toast1 = new android.widget.Toast(context);
					toast1.setView(layout);
					toast1.setGravity(Gravity.RIGHT | Gravity.TOP,10,60);
					toast1.setDuration(time);
					toast1.show();
					
				}
		});
        
    }
	
	public static void makeText(Context context,String str) {
		makeText(context, str, 0, 0);
	}
	
	public static void makeText(Context context, String str, int time) {
		makeText(context, str, time, 0);
	}
    
}
