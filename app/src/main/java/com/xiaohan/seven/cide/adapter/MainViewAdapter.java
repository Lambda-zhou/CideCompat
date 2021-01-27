package com.xiaohan.seven.cide.adapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class MainViewAdapter extends PagerAdapter {
    
    private ArrayList<View> listView;
    private ArrayList<String> listTitle;
    
    public MainViewAdapter(ArrayList<View> listView, ArrayList<String> listTitle) {
        super();
        this.listView = listView;
        this.listTitle = listTitle;
    }
    
    @Override
    public int getCount() {
        return listView.size();
    }

    @Override
    public boolean isViewFromObject(View p1, Object p2) {
        return p1 == p2;
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(listView.get(position));
        return listView.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listView.get(position));
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
    
}
