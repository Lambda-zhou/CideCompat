package com.xiaohan.seven.cide.adapter;
import android.widget.ArrayAdapter;
import com.xiaohan.seven.cide.list.Study;
import java.util.List;
import android.content.Context;
import org.jetbrains.annotations.NotNull;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.xiaohan.seven.cide.R;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.graphics.Typeface;

public class OtherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    private Context context;
	
    private int resourceId;
    
	private List<Study> objects;
    
    private LayoutInflater inflater;
    
    private OnItemClickListener onItemClick = new OnItemClickListener(){

        @Override
        public void onItemClick(Study p1, View p2, int p3) {
            
        }
        
    };
    
    private OnClickListener mOnClick = new OnClickListener(){

        @Override
        public void onClick(View p1) {
            
        }
        
    };
    
    public OtherAdapter(@NotNull Context context,@NotNull int resourceId,@NotNull List<Study> objects) {
		this.context = context;
        this.resourceId = resourceId;
		this.objects = objects;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p1, final int position) {
        View view = inflater.inflate(R.layout.nav_study_card, null);
        return new StudyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder p1, final int p2) {
        StudyViewHolder holder1 = (StudyViewHolder) p1;
        final Study itemEntity = objects.get(p2);
		holder1.view.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					mOnClick.onClick(p1);
					onItemClick.onItemClick(itemEntity, p1, p2);
				}
				
		});
        if(itemEntity.getImage() != null){
        holder1.imageView.setImageBitmap(itemEntity.getImage());
        }
        holder1.titleView.setText(itemEntity.getTitle());
		holder1.titleView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/product.ttf"));
        holder1.messageView.setText(itemEntity.getMessage());
        holder1.typeView.setText(itemEntity.getLanguage());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
    
    public void setOnItemClickListener(@NotNull OnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }
    
    public void setOnClickListener(@NotNull OnClickListener onClick) {
        this.mOnClick = onClick;
    }
    
    public interface OnItemClickListener {
        public void onItemClick(Study p1, View p2, int p3);
    }
    
    private class StudyViewHolder extends RecyclerView.ViewHolder{
        
        public ImageView imageView;
        
        public TextView titleView;
        
        public TextView messageView;
        
        public TextView typeView;
		
		public View view;
        
        public StudyViewHolder(View itemView) {
            super(itemView);
			this.view = itemView;
            imageView = itemView.findViewById(R.id.nav_study_image);
            titleView = itemView.findViewById(R.id.nav_study_title);
            messageView = itemView.findViewById(R.id.nav_study_message);
            typeView = itemView.findViewById(R.id.nav_study_type);
        }
    }
	
}
