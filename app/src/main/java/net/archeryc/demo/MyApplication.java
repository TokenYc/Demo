package net.archeryc.demo;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24706 on 2016/4/19.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private List<Activity> activities;
    @Override
    public void onCreate() {
        super.onCreate();
        activities = new ArrayList<>();
        instance=this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }
    public List<Activity> getActivities(){
        return activities;
    }
}
