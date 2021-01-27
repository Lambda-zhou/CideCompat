package com.xiaohan.seven.cide.tools;
import android.app.Activity;
import android.support.annotation.NonNull;
import java.util.Stack;

public abstract class ActivitiesManager {
    public static Stack<Activity> activities = new Stack<Activity>();
    
    public static void addActivity(@NonNull Activity activity) {
        activities.add(activity);
    }
    
    public static void removeActivity(@NonNull Activity activity) {
        activities.remove(activity);
    }
    
    public static void addActivities(@NonNull Activity[] a1) {
        for(Activity activity : a1){
            activities.add(activity);
        }
    }
    
    public static void removeActivities(@NonNull Activity[] a1) {
        for(Activity activity : a1){
            activities.remove(activity);
            activity.finish();
        }
    }
    
    public static void finishActivities() {
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                removeActivity(activity);
            }
        }
    }
    
}
