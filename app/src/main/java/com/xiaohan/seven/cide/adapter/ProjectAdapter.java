package com.xiaohan.seven.cide.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaohan.seven.cide.R;
import com.xiaohan.seven.cide.list.Projects;
import java.util.List;

public class ProjectAdapter extends ArrayAdapter<Projects> {
    
    private int resourceId;
    
    public ProjectAdapter(Context context, int textViewResource, List<Projects> objects) {
        super(context, textViewResource, objects);
        resourceId = textViewResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Projects project = getItem(position);
        
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView icon = view.findViewById(R.id.nav_projectsImageView);
        TextView name = view.findViewById(R.id.nav_projectsTextView);
        icon.setImageResource(project.getImageId());
        name.setText(project.getName());
        return view;
    }
    
}
