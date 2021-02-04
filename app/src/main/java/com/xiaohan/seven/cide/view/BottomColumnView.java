package com.xiaohan.seven.cide.view;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.View;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView.Adapter;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.LinearLayoutCompat;
import android.graphics.Color;
import java.util.Random;
import android.view.Gravity;
import android.widget.PopupWindow;
import android.support.v7.app.AppCompatActivity;
import android.graphics.drawable.ColorDrawable;
import com.xiaohan.seven.cide.tools.ApplicationUtils;

public class BottomColumnView extends RecyclerView {
    
	private Context context;
	
	private List<String> words = new ArrayList<String>();
	
	private OnTextClickListener mOnTextClick = new OnTextClickListener(){

		@Override
		public void onTextClick(View p1, String p2) {
			
		}
		
	};
	
    public BottomColumnView(@NonNull Context context, @NonNull String[] chars) {
		super(context);
		this.context = context;
		setHasFixedSize(true);
		LinearLayoutManager manager = new LinearLayoutManager(context);
		manager.setOrientation(OrientationHelper.HORIZONTAL);
		setLayoutManager(manager);
		for(String chs : chars){
			words.add(chs);
		}
	}
	
	public void setAdapter() {
		Adapter adapter = new Adapter(context, words);
		adapter.setOnItemClickListener(new Adapter.OnItemClickListener(){

				@Override
				public void onItemClick(View view, String text) {
					mOnTextClick.onTextClick(view, text);
				}
				
		});
		setAdapter(adapter);
		final BottomColumnView mThis = this;
		post(new Runnable(){

				@Override
				public void run() {
					View view = ((AppCompatActivity)context).getWindow().getDecorView();
					((ViewGroup)view).addView(mThis);
				}

		});
	}
	
	public void setOnTextClickListener(@NonNull OnTextClickListener onTextClick) {
		this.mOnTextClick = onTextClick;
	}
	
	public static interface OnTextClickListener {
		public void onTextClick(View p1, String p2);
	}
	
	private static class Adapter extends RecyclerView.Adapter {
		
		private Context context;
		
		private List<String> objects = new ArrayList<String>();
		
		private int id = 0xfcff5;
		
		private OnItemClickListener mOnItemClick = new OnItemClickListener(){

			@Override
			public void onItemClick(View view, String text) {
				
			}
			
		};
		
		public Adapter(Context context, List<String> objects) {
			this.context = context;
			this.objects = objects;
		}
		
		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {
			LinearLayoutCompat linearlayout = new LinearLayoutCompat(context);
			linearlayout.setOrientation(1);
			linearlayout.setLayoutParams(new ViewGroup.LayoutParams(50, -2));
			
			LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(-2, -2);
			params.gravity = Gravity.CENTER_HORIZONTAL;
			params.setMargins(5, 5, 5, 5);
			
			TextView textView = new TextView(context);
			textView.setId(id);
			textView.setLayoutParams(params);
			textView.setTextColor(Color.parseColor("#000000"));
			linearlayout.addView(textView);
			return new ViewHolder(linearlayout);
		}

		@Override
		public void onBindViewHolder(final RecyclerView.ViewHolder p1, final int p2) {
			ViewHolder holder = (ViewHolder)p1;
			holder.textView.setText(objects.get(p2));
			holder.view.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1) {
						mOnItemClick.onItemClick(p1, objects.get(p2));
					}
					
			});
		}

		@Override
		public int getItemCount() {
			return objects.size();
		}
		
		public void setOnItemClickListener(@NonNull OnItemClickListener onItemClick) {
			this.mOnItemClick = onItemClick;
		}
		
		public static interface OnItemClickListener {
			
			public void onItemClick(View view, String text);
			
		}
		
		public static class ViewHolder extends RecyclerView.ViewHolder {
			
			public View view;
			
			public TextView textView;
			
			public ViewHolder(View view) {
				super(view);
				this.view = view;
				this.textView = view.findViewById(0xfcff5);
			}
			
		}
		
	}
    
}
