package com.xiaohan.seven.cide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.xiaohan.seven.cide.R;

/**
 * Created by Administrator on 2016/12/20.
 */

public class NewProjectListViewAdapter extends BaseExpandableListAdapter {
    Context context;
    String[] first ;
    String[][] secand;
    LayoutInflater li;
    public NewProjectListViewAdapter(Context context){
        this.context = context;
        li = LayoutInflater.from(context);
    }

    public void setData(String[] first,String[][] secand){
        this.first = first;
        this.secand =secand;
    }

    @Override
    public int getGroupCount() {
        return first==null?0:first.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return secand==null?0:secand[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return first==null?0:first[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return secand==null?0:secand[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = li.inflate(R.layout.newproject_list_1,null);
        }else{

        }
        TextView item_1 = convertView.findViewById(R.id.newprojectlistview_new_list_1);
        item_1.setText(first[groupPosition]);
        item_1.setTextColor(Color.parseColor("#000000"));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = li.inflate(R.layout.newproject_list_2,null);
        }else{

        }
        TextView item_1 = convertView.findViewById(R.id.newprojectlistview_new_list_2);
        item_1.setText(secand[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
