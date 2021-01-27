package com.xiaohan.seven.cide.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaohan.seven.cide.R;
import com.xiaohan.seven.cide.list.MainProject;
import java.util.List;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.graphics.Typeface;

public class MainProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    

    private List<MainProject> list;
    private Context context;
    private LayoutInflater inflater;
	private OnClickListener onClick = new OnClickListener(){

		@Override
		public void onClick(View p1) {
			
		}
		
	};
    private OnLongClickListener onLongClick = new OnLongClickListener(){

        @Override
        public boolean onLongClick(View p1) {
            return false;
        }
    };
    
    public MainProjectAdapter(Context context, List<MainProject> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p1, int p2) {

        View view = inflater.inflate(R.layout.nav_project_card, null);
		view.setOnClickListener(onClick);
        view.setOnLongClickListener(onLongClick);
        return new MainProjectViewHolder(view);
        
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder p1, int p2) {
        MainProjectViewHolder holder1 = (MainProjectViewHolder) p1;
        MainProject itemEntity = list.get(p2);
        
        holder1.imageView.setImageResource(itemEntity.getImageId());
        holder1.titleTextView.setText(itemEntity.getTitle());
		holder1.titleTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/product.ttf"));
        holder1.sizeTextView.setText(itemEntity.getSize());
        holder1.timeTextView.setText(itemEntity.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
	
	public void setOnClickListener(OnClickListener onClick){
		this.onClick = onClick;
	}
    
    public void setOnLongClickListener(OnLongClickListener onLongClick) {
        this.onLongClick = onLongClick;
    }
    
    private class MainProjectViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        
        private TextView titleTextView;
        
        private TextView sizeTextView;
        
        private TextView timeTextView;
        
        public MainProjectViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nav_project_image);
            titleTextView = itemView.findViewById(R.id.nav_project_title);
            sizeTextView = itemView.findViewById(R.id.nav_project_size);
            timeTextView = itemView.findViewById(R.id.nav_poject_time);
        }
    }
    
    
}
